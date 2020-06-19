package com.example.mobile_programming_term_project;

import android.util.Log;

public class StackDataType implements Stack {
    final public int MAX_STACK_SIZE = 100;
    // private는 getter/setter의 존재 이유
    private int top;
    private int stackSize;
    private char stackArr[];

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
    public boolean isEmpty(StackDataType Stack) {
        return (top == -1);
    }

    @Override
    public boolean isFull(StackDataType Stack) {
        return (top == this.stackSize - 1);
    }

    @Override
    public void push(StackDataType Stack, char item) {

    }

    @Override
    public char pop(StackDataType Stack) {
        if (isEmpty(Stack)) {
            error("Stack is Empty!!");
            System.exit(-1);
        }
        Log.i("Pop : ", "");
        return stackArr[top--];
    }

    @Override
    public char peek(StackDataType Stack) {
        if (isEmpty(Stack)) {
            error("Stack is Empty!!!");
            System.exit(-1);
        }
        Log.i("Peek : ", "");
        return stackArr[top];

    }

    @Override
    public void clear(StackDataType Stack) {

    }

    void init_stack(StackDataType stack) {
        stack.top = -1;
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
    void infixToPostfix(char[] exp) {
        int i = 0;
        char ch, top_op;
        int len = exp.length;
        StackDataType stack = new StackDataType(stackSize);

        init_stack(stack);                  // 스택 초기화
        for (i = 0; i < len; i++) {
            ch = exp[i];
        }
    }
}
