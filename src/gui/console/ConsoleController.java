package gui.console;

/**
 * @author Arthur Kupriyanov
 */
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.TreeMap;

public class ConsoleController {
    private boolean historyUpdated = true;
    private boolean isLastOperationDown = false;
    private boolean isLastOperationUp = false;
    private boolean lastCommand = false;
    private int lastIndex = -1;
    private int nowIndex = 0;

    private TreeMap<Integer, String> history = new TreeMap<>();

    @FXML
    private TextArea consoleText;

    @FXML
    private TextField commandPromt;

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER){
            if (!commandPromt.textProperty().isEmpty().get()){
                consoleText.appendText(">> " + commandPromt.textProperty().get() + "\n");
                historyUpdated = true;
                if (commandPromt.textProperty().get().equals("clear")){
                    clearHistory();
                } else {
                    addCommandToHistory(commandPromt.textProperty().get());
                }
                commandPromt.clear();
            }
        }
            if (event.getCode() == KeyCode.UP) {
                if (historyUpdated) {
                    updateHistory();
                }
                if (lastIndex == -1){
                    return;
                }
                commandPromt.clear();
                if (nowIndex == 0) {
                    nowIndex = lastIndex + 1;
                    lastCommand = true;
                } else {
                    lastCommand = false;
                }
                if (isLastOperationDown) {
                    commandPromt.appendText(history.get(--nowIndex));
                    isLastOperationDown = false;
                } else {
                    commandPromt.appendText(history.get(--nowIndex));
                }
                isLastOperationUp = true;

            }
            if (event.getCode() == KeyCode.DOWN) {
                if (lastIndex == -1){
                    return;
                }
                if (!historyUpdated) {
                    if (nowIndex < lastIndex) {
                        commandPromt.clear();
                        System.out.println(nowIndex + " " + lastIndex);
                        commandPromt.appendText(history.get(++nowIndex));
                        isLastOperationDown = true;
                    } else if (nowIndex == lastIndex) {
                        commandPromt.clear();
                        if (lastCommand){
                            if (lastIndex != 0) {
                                commandPromt.appendText(history.get(1));
                            }
                            lastCommand = false;
                        }
                    }
                }
            }
    }

    private void addCommandToHistory(String cmd) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("src/gui/console/command-history.txt")));
        String line;
        StringBuilder oldHistory = new StringBuilder();
        while ((line = br.readLine()) != null) {
            oldHistory.append(line).append("\n");
        }
        oldHistory.append(cmd);
        br.close();
        System.out.println(oldHistory);
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/gui/console/command-history.txt")));
        bw.write(oldHistory.toString());
        bw.close();
        historyUpdated = true;
    }

    private void updateHistory() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("src/gui/console/command-history.txt")));
        String line;
        int index = 0;
        while ((line = br.readLine()) != null) {
            if (index > lastIndex) {
                history.put(index, line);
            }
            index++;
        }
        if (index == 0){
            return;
        }
        index--;
        lastIndex = index;
        nowIndex = lastIndex;
        historyUpdated = false;
        br.close();
    }

    private void clearHistory() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/gui/console/command-history.txt")));
        bw.write("");
        historyUpdated = true;
        isLastOperationDown = false;
        isLastOperationUp = false;
        lastIndex = -1;
        nowIndex = 0;

    }

}
