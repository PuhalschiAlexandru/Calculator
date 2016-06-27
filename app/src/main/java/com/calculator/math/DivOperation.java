package com.calculator.math;

public class DivOperation implements Operation {
    @Override
    public float performOperation(float input1, float input2) {
        return input1 / input2;
    }
}