package com.example.mobile_programming_term_project;

import android.util.Log;

public class StackDataType implements Stack {
    final public int MAX_STACK_SIZE = 100;
    // private는 getter/setter의 존재 이유
    private int top;
    private int stackSize;
    private String stackArr[];
    private String result;

    // 생성자 호출
    public StackDataType(int stackSize) {
        top = -1;
        this.stackSize = stackSize;
        stackArr = new String[this.stackSize];
    }

    String error(String message){
        Log.d("ERROR : ",message);
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
        if(isFull()){
            error("Stack is Full!!");
        }else{
            stackArr[++top] = item;
            Log.i("Inserted : ",item);
        }
    }

    @Override
    public String pop() {
        if(isEmpty()){
            error("Stack is Empty!!");
        }else{
            Log.i("Pop : ", stackArr[top]);
            return stackArr[top--];
        }
        return " ";
    }

    @Override
    public String peek() {
        if(isEmpty()){
            error("Stack is Empty!!!");
        }else{
            return stackArr[top];
        }
        return " ";
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
    }
}
