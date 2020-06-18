package com.example.mobile_programming_term_project;

public interface Stack {
    boolean isEmpty();
    boolean isFull();
    void push (String item);
    String pop();
    String peek();
    void clear();
}
