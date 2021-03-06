package com.example.mobile_programming_term_project;

import android.util.Log;

public class getPostFix implements makePostfixStack {
    // private 는 getter/setter 의 존재 이유
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

    // Stack Push Method
    @Override
    public void push(char item) {
        if (isFull()) {
            Log.e("ERROR : ", "Stack Over Flow");
            System.exit(-1);
        } else {
            stackArr[++top] = item;
        }
    }

    // Stack Pop Method
    @Override
    public char pop() {
        if (isEmpty()) {
            error("Stack is Empty!!");
            System.exit(-1);
        }
        Log.i("Pop : ", "");

        return stackArr[top--];
    }

    // Stack Peek Method
    @Override
    public char peek() {
        if (isEmpty()) {
            error("Stack is Empty!!!");
            // System.exit(-1);
        }
        Log.i("Peek : ", "");
        return stackArr[top];

    }

    // 스택 초기화 메소드
    @Override
    public void init_stack(getPostFix stack) {
        stack.top = -1;
    }

    // 우선순위 반환 메소드
    @Override
    public int returnOfPriority(char op) {
        switch (op) {
            case '(':
            case ')':
                return 0;
            case '|':
                return 1;
            case '^':
                return 2;
            case '&':
                return 3;
            case '+':
            case '-':
                return 4;
            case '*':
            case '/':
            case '%':
                return 5;
            case '~':
                return 6;
        }
        return -1;
    }

    // 중위수식 -> 후위수식 변환 메소드
    @Override
    public char[] infixToPostfix(char[] exp) {
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
                case '%':                           // 연산자라면?
                case '^':
                case '&':
                case '|':
                case '~':
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
                    result.append(' ');
                    // 왼쪽 괄호를 만날때 까지 출력
                    while (top_op != '(') {
                        result.append(top_op);
                        top_op = pop();
                        result.append(' ');
                    }
                    break;
                default:                            // 피연산자라면?
                    result.append(ch);
                    if (i == exp.length - 1) {
                        break;
                    }
                    if (exp[i + 1] == '+' || exp[i + 1] == '-' || exp[i + 1] == '*'
                            || exp[i + 1] == '/' || exp[i + 1] == '%' || exp[i + 1] == '^'
                            || exp[i + 1] == '&' || exp[i + 1] == '|' || exp[i + 1] == '~' ) {
                        result.append(' ');
                    }
                    break;
            }
        }
        while (!isEmpty()) {
            if(exp[i - 1] != ')') {
                result.append(' ');
            }
            result.append(pop());
            Log.i("Result : ", String.valueOf(result));
            resultChar = String.valueOf(result).toCharArray();
        }
        return resultChar;
    }

}
