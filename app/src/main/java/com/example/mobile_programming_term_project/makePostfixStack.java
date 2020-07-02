package com.example.mobile_programming_term_project;

public interface makePostfixStack {
    boolean isEmpty();
    boolean isFull();
    void push (char item);
    char pop();
    char peek();
    int returnOfPriority(char op);
    char[] infixToPostfix(char[] exp);
    void init_stack(getPostFix stack);
}
