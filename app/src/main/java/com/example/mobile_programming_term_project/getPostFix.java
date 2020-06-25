package com.example.mobile_programming_term_project;

import android.util.Log;

public class getPostFix implements makePostfixStack {
    // private는 getter/setter 의 존재 이유
    private int top;
    private int stackSize;
    private char[] stackArr;
    private char[] resultChar;

    // 생성자 호출
    getPostFix(int stackSize) {
        top = -1;
        this.stackSize = stackSize;
        stackArr = new char[this.stackSize];
    }

    private void error(String message) {
        Log.d("ERROR : ", message);
    }

    @Override      // 빈 Stack인지 확인 함
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public boolean isFull() {
        return (top == stackSize - 1);
    }

    @Override
    public void push(char item) {
        if (isFull()) {
            Log.e("ERROR : ", "Stack Over Flow");
            System.exit(-1);
        } else {
            stackArr[++top] = item;
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
            // System.exit(-1);
        }
        Log.i("Peek : ", "");
        return stackArr[top];

    }

    @Override
    public void clear() {

    }

    private void init_stack(getPostFix stack) {
        stack.top = -1;
    }

    // 우선순위 반환
    private int returnOfPriority(char op) {
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
    char[] infixToPostfix(char[] exp) {
        int i = 0;
        char ch, top_op;
        int len = exp.length;
        getPostFix stack = new getPostFix(stackSize);
        StringBuilder result = new StringBuilder("");

        init_stack(stack);                           // 스택 초기화
        for (i = 0; i < len; i++) {
            ch = exp[i];
            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!isEmpty() && (returnOfPriority(ch) <=
                            returnOfPriority(peek()))) {
                        result.append(pop());
                        result.append(' ');
                    }
                    push(ch);
                    break;
                case '(':                           // 왼쪽 괄호
                    push(ch);
                    break;
                case ')':                           // 오른쪽 괄호
                    top_op = pop();
                    // 왼쪽 괄호를 만날때 까지 출력
                    while (top_op != '(') {
                        result.append(top_op);
                        result.append(' ');
                        top_op = pop();
                    }
                    break;
                default:                            // 피연산자 라면?
                    result.append(ch);
                    if (i == exp.length - 1) {
                        break;
                    }
                    if (exp[i + 1] == '+' || exp[i + 1] == '-' ||
                            exp[i + 1] == '*' || exp[i + 1] == '/') {
                        result.append(' ');
                    }
                    break;
            }
        }
        while (!isEmpty()) {
            result.append(' ');
            result.append(pop());
            Log.i("Result : ", String.valueOf(result));
            resultChar = String.valueOf(result).toCharArray();
        }
        return resultChar;
    }

}
