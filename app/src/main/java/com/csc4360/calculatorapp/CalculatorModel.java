package com.csc4360.calculatorapp;

import java.util.Stack;

public class CalculatorModel implements ICalculator{

    boolean isOperation;
    private Stack<String> dataStack = new Stack<>();

    public CalculatorModel() {

    }

    @Override
    public void pushData(String item) {
        dataStack.push(item);
    }

    @Override
    public String calculate() {

        try {
            System.out.println("Peek : " + Double.valueOf(dataStack.peek()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        double result = Double.valueOf(dataStack.pop());

        if (dataStack.empty()) {
            isOperation = false;
            return String.valueOf(result);
        }

        String data = dataStack.pop();

        if (data.equals("+") && isOperation) {
            result += Double.valueOf(dataStack.pop());
            clearEverything();
            dataStack.push(String.valueOf(result));
        } else if (data.equals("-")) {
            result = Double.valueOf(dataStack.pop()) - result;
            clearEverything();
            dataStack.push(String.valueOf(result));
        } else if (data.equals("*")) {
            result *= Double.valueOf(dataStack.pop());
            clearEverything();
            dataStack.push(String.valueOf(result));
        } else if (data.equals("/")) {
            String temp = dataStack.pop();
            if (result == 0) {
                return "Error";
            }
            result = Double.valueOf(temp) / result;
            clearEverything();
            dataStack.push(String.valueOf(result));
        }
        isOperation = false;
        return String.valueOf(result);
    }

    @Override
    public String clearEverything() {
        dataStack.removeAllElements();
        return "0";
    }

    @Override
    public String percent() {
        double result = Double.valueOf(dataStack.pop());
        result /= 100.0;
        return String.valueOf(result);
    }

    @Override
    public String negate() {
        double result = Double.valueOf(dataStack.pop());
        result -= (2 * result);
        return String.valueOf(result);
    }

    @Override
    public void printStack() {
        while (!dataStack.isEmpty()) {
            System.out.println(dataStack.pop());
        }
    }

    // FOR TESTING CALCULATOR MODEL
    public static void main(String[] args) {

        // Testing main()
        CalculatorModel test = new CalculatorModel();

        test.pushData("12");
        test.pushData("+");
        test.pushData("10");

        String result = test.calculate();
        System.out.println(result);

        result = test.percent();
        System.out.println(result);

        result = test.negate();
        System.out.println(result);

        result = test.clearEverything();
        System.out.println(result);



    }

}
