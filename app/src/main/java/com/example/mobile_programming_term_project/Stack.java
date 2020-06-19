package com.example.mobile_programming_term_project;

public interface Stack {
    boolean isEmpty(StackDataType Stack);
    boolean isFull(StackDataType Stack);
    void push (StackDataType Stack,char item);
    char pop(StackDataType Stack);
    char peek(StackDataType Stack);
    void clear(StackDataType Stack);
}
