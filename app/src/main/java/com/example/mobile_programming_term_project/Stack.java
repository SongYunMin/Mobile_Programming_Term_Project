package com.example.mobile_programming_term_project;

public interface Stack {
    boolean isEmpty();
    boolean isFull();
    void push (char item);
    char pop();
    void clear();
    char peek();
}
