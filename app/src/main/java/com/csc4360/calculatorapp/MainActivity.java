// Calculator App by Frankie Sanchez, 900942699
// fsanchez4@student.gsu.edu
package com.csc4360.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Fields used throughout MainActivity file to extract and interact with values from
    // displayContent string
    private boolean isOperation = false;
    private double firstValue = 0.0;
    private int indexOfSecondValue = 0;
    private char currentOp;
    private String displayContent;
    private EditText calculatorDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Establishing variables for widget ID's in R Class
        calculatorDisplay = findViewById(R.id.calculatorDisplay);
        final Button number0 = findViewById(R.id.number0);
        final Button number1 = findViewById(R.id.number1);
        final Button number2 = findViewById(R.id.number2);
        final Button number3 = findViewById(R.id.number3);
        final Button number4 = findViewById(R.id.number4);
        final Button number5 = findViewById(R.id.number5);
        final Button number6 = findViewById(R.id.number6);
        final Button number7 = findViewById(R.id.number7);
        final Button number8 = findViewById(R.id.number8);
        final Button number9 = findViewById(R.id.number9);
        final Button dot = findViewById(R.id.dot);
        final Button equals = findViewById(R.id.equals);
        final Button addition = findViewById(R.id.addition);
        final Button subtraction = findViewById(R.id.subtraction);
        final Button multiplication = findViewById(R.id.multiplication);
        final Button division = findViewById(R.id.division);

        final Button clearEverything = findViewById(R.id.clearEverything);
        final Button delete = findViewById(R.id.delete);
        final Button percent = findViewById(R.id.percent);
        final Button sign = findViewById(R.id.sign);

        // Creating new OnClickListener for numbers and arithmetic operations
        final View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtaining id's for switch-case statement
                final int id = view.getId();
                displayContent = calculatorDisplay.getText().toString();

                switch (id) {
                    case R.id.number0:
                        adjustDisplayContent("0");
                        break;
                    case R.id.number1:
                        adjustDisplayContent("1");
                        break;
                    case R.id.number2:
                        adjustDisplayContent("2");
                        break;
                    case R.id.number3:
                        adjustDisplayContent("3");
                        break;
                    case R.id.number4:
                        adjustDisplayContent("4");
                        break;
                    case R.id.number5:
                        adjustDisplayContent("5");
                        break;
                    case R.id.number6:
                        adjustDisplayContent("6");
                        break;
                    case R.id.number7:
                        adjustDisplayContent("7");
                        break;
                    case R.id.number8:
                        adjustDisplayContent("8");
                        break;
                    case R.id.number9:
                        adjustDisplayContent("9");
                        break;
                    case R.id.dot:
                        if (displayContent.endsWith(".")) {
                            break;
                        } else {
                            calculatorDisplay.append(".");
                        }
                        break;
                    case R.id.equals:
                        performEquals();
                        break;
                    case R.id.addition:
                        add();
                        break;
                    case R.id.subtraction:
                        subtract();
                        break;
                    case R.id.multiplication:
                        multiply();
                        break;
                    case R.id.division:
                        divide();
                        break;
                }
            }
        };

        // Setting OnClickListeners for numeric values
        number0.setOnClickListener(calculatorListener);
        number1.setOnClickListener(calculatorListener);
        number2.setOnClickListener(calculatorListener);
        number3.setOnClickListener(calculatorListener);
        number4.setOnClickListener(calculatorListener);
        number5.setOnClickListener(calculatorListener);
        number6.setOnClickListener(calculatorListener);
        number7.setOnClickListener(calculatorListener);
        number8.setOnClickListener(calculatorListener);
        number9.setOnClickListener(calculatorListener);
        dot.setOnClickListener(calculatorListener);

        // Setting OnClickListeners for arithmetic operations
        equals.setOnClickListener(calculatorListener);
        addition.setOnClickListener(calculatorListener);
        subtraction.setOnClickListener(calculatorListener);
        multiplication.setOnClickListener(calculatorListener);
        division.setOnClickListener(calculatorListener);

        // Creating new OnClickListeners for special operations
        // Delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayedElements = calculatorDisplay.getText().toString();
                int length = displayedElements.length();
                if (length > 0) {
                    displayedElements = displayedElements.substring(0, length - 1);
                    calculatorDisplay.setText(displayedElements);
                }
            }
        });

        // Clear all
        clearEverything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorDisplay.setText("0");
            }
        });

        // Calculate percentage
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(calculatorDisplay.getText().toString());
                value = value / 100.0;
                calculatorDisplay.setText(String.valueOf(value));

            }
        });

        // Calculate negative value
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(calculatorDisplay.getText().toString());
                value = value - (2 * value);
                if (value == Math.floor(value)) {
                    calculatorDisplay.setText(String.valueOf((int) value));
                } else {
                    calculatorDisplay.setText(String.valueOf(value));
                }
            }
        });
    }

    // Method for ensuring up-to-date calculator display
    public void adjustDisplayContent(String number) {
        if (displayContent.equals("0") || displayContent.equals("Error")) {
            calculatorDisplay.setText(number);
        } else {
            calculatorDisplay.append(number);
        }
    }

    // Add method; first value is captured for operation, display updated, and op set
    public void add() {
        if (displayContent.matches(".*\\+.*") || displayContent.matches(".*-.*") ||
                displayContent.matches(".*\\*.*") || displayContent.matches(".*/.*")) {
            performEquals();
        } else {
            firstValue = Double.parseDouble(displayContent);
            indexOfSecondValue = displayContent.length() + 1;
            calculatorDisplay.append("+");
            isOperation = true;
            currentOp = '+';
        }
    }

    // Subtract method; first value is captured for operation, display updated, and op set
    public void subtract() {
        if (displayContent.matches(".*\\+.*") || displayContent.matches(".*-.*") ||
                displayContent.matches(".*\\*.*") || displayContent.matches(".*/.*")) {
            performEquals();

        } else {
            firstValue = Double.parseDouble(displayContent);
            indexOfSecondValue = displayContent.length() + 1;
            calculatorDisplay.append("-");
            isOperation = true;
            currentOp = '-';
        }
    }

    // Multiply method; first value is captured for operation, display updated, and current op is set
    public void multiply() {
        if (displayContent.matches(".*\\+.*") || displayContent.matches(".*-.*") ||
                displayContent.matches(".*\\*.*") || displayContent.matches(".*/.*")) {
            performEquals();
        } else {
            firstValue = Double.parseDouble(displayContent);
            indexOfSecondValue = displayContent.length() + 1;
            calculatorDisplay.append("*");
            isOperation = true;
            currentOp = '*';
        }
    }

    // Divide method; first value is captured for operation, display updated and current op is set
    public void divide() {
        if (displayContent.matches(".*\\+.*") || displayContent.matches(".*-.*") ||
                displayContent.matches(".*\\*.*") || displayContent.matches(".*/.*")) {
            performEquals();
        } else {
            firstValue = Double.parseDouble(displayContent);
            indexOfSecondValue = displayContent.length() + 1;
            calculatorDisplay.append("/");
            isOperation = true;
            currentOp = '/';
        }
    }

    // Perform operation (equals) method; captures second value using substring, updates display as
    // int if value = floor value and as double if value != floor value
    public void performEquals() {
        try {
            if (isOperation) {
                double secondValue;
                double resultValue;
                if (currentOp == '+') {
                    secondValue = Double.parseDouble(displayContent.substring(indexOfSecondValue));
                    resultValue = firstValue + secondValue;
                    if (resultValue == Math.floor(resultValue)) {
                        calculatorDisplay.setText(String.valueOf((int) resultValue));
                    } else {
                        calculatorDisplay.setText(String.valueOf(resultValue));
                    }
                } else if (currentOp == '-') {
                    secondValue = Double.parseDouble(displayContent.substring(indexOfSecondValue));
                    resultValue = firstValue - secondValue;
                    if (resultValue == Math.floor(resultValue)) {
                        calculatorDisplay.setText(String.valueOf((int) resultValue));
                    } else {
                        calculatorDisplay.setText(String.valueOf(resultValue));
                    }
                } else if (currentOp == '*') {
                    secondValue = Double.parseDouble(displayContent.substring(indexOfSecondValue));
                    resultValue = firstValue * secondValue;
                    if (resultValue == Math.floor(resultValue)) {
                        calculatorDisplay.setText(String.valueOf((int) resultValue));
                    } else {
                        calculatorDisplay.setText(String.valueOf(resultValue));
                    }
                } else if (currentOp == '/') {
                    secondValue = Double.parseDouble(displayContent.substring(indexOfSecondValue));
                    if (secondValue == 0) {
                        calculatorDisplay.setText("Error");
                    } else {
                        resultValue = firstValue / secondValue;
                        if (resultValue == Math.floor(resultValue)) {
                            calculatorDisplay.setText(String.valueOf((int) resultValue));
                        } else {
                            calculatorDisplay.setText(String.valueOf(resultValue));
                        }
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
