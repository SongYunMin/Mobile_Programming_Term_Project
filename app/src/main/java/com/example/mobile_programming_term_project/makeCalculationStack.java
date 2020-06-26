package com.example.mobile_programming_term_project;

public interface makeCalculationStack {
    boolean isEmpty();
    boolean isFull();
    void push(float item);
    float pop();
    float peek();
}
