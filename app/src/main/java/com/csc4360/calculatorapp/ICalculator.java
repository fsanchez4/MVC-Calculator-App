package com.csc4360.calculatorapp;

public interface ICalculator {

    void pushData(String item);
    String calculate();
    String negate();
    String percent();
    String clearEverything();
    void printStack();

}
