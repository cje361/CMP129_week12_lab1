import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private TextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font(28));
        display.setPrefHeight(60);

        String[][] labels = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"C", "0", "=", "+"}
        };

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);

        for (int row = 0; row < labels.length; row++) {
            for (int col = 0; col < labels[row].length; col++) {
                String text = labels[row][col];
                Button button = new Button(text);
                button.setPrefSize(70, 70);
                button.setFont(Font.font(22));
                button.setOnAction(event -> handleButton(text));
                grid.add(button, col, row);
            }
        }

        VBox root = new VBox(12, display, grid);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 350, 430);
        stage.setTitle("CMP 128 Honors JavaFX Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void handleButton(String text) {
        if (text.equals("C")) {
            clear();
        } else if (text.equals("=")) {
            calculate();
        } else if (text.equals("+") || text.equals("-")
                || text.equals("*") || text.equals("/")) {
            chooseOperator(text);
        } else {
            appendDigit(text);
        }
    }

    private void appendDigit(String digit) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    private void chooseOperator(String op) {
        if (display.getText().startsWith("Error")) {
            return;
        }
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        startNewNumber = true;
    }

    private void calculate() {
        if (operator.isEmpty() || display.getText().startsWith("Error")) {
            return;
        }

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        if (operator.equals("/") && secondNumber == 0) {
            display.setText("Error: divide by 0");
            operator = "";
            startNewNumber = true;
            return;
        }

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }

        showResult(result);
        operator = "";
        startNewNumber = true;
    }

    private void showResult(double result) {
        if (result == Math.floor(result)) {
            display.setText(String.valueOf((long) result));
        } else {
            display.setText(String.valueOf(result));
        }
    }

    private void clear() {
        display.setText("0");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }
}