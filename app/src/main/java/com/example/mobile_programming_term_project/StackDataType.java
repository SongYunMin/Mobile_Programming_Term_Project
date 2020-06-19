package com.example.mobile_programming_term_project;

import android.util.Log;

public class StackDataType implements Stack {
    final public int MAX_STACK_SIZE = 100;
    // private는 getter/setter의 존재 이유
    private int top;
    private int stackSize;
    private char stackArr[];
    private String result;

    // 생성자 호출
    public StackDataType(int stackSize) {
        top = -1;
        this.stackSize = stackSize;
        stackArr = new char[this.stackSize];
    }
//
//    char error(char c){
//        return c;
//    }

    String error(String message) {
        Log.d("ERROR : ", message);
        return message;
    }

    @Override      // 빈 Stack인지 확인 함
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public boolean isFull() {
        return (top == this.stackSize - 1);
    }

    @Override
    public void push(String item) {

    }

    public void push(char item) {
        if (isFull()) {
            error("Stack is Full!!");
        } else {
            stackArr[++top] = item;
            Log.i("Inserted : ", "");
        }
    }

    @Override
    public char pop() {
        if (isEmpty()) {
            error("Stack is Empty!!");
            System.exit(-1);
        }
        Log.i("Pop : ", "");
        return stackArr[top--];
    }

    @Override
    public char peek() {
        if (isEmpty()) {
            error("Stack is Empty!!!");
            System.exit(-1);
        }
        Log.i("Peek : ", "");
        return stackArr[top];

    }

    @Override
    public void clear() {

    }

    // Getter Method
    String getResult() {
        return result;
    }

    // Setter Method
    public void setResult(String result) {
        this.result = result;
    }

    void init_stack() {

    }

    // 우선순위 반환
    int returnOfPriority(char op) {
        switch (op) {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    // 중위수식 -> 후위수식 변환
    void infixToPostfix(String exp) {
        int i = 0;
        char ch, top_op;
        int len = exp.length();
        StackDataType s;

    }
}
