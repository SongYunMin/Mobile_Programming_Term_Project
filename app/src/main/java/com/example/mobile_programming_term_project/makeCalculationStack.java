package com.example.mobile_programming_term_project;

public interface makeCalculationStack {
    boolean isEmpty();
    boolean isFull();
    void push(int item);
    int pop();
    int peek();
    float Calculation(char[] postFixArray);
}
