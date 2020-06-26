package com.example.mobile_programming_term_project;

import android.util.Log;

public class getCalculationResult implements makeCalculationStack {
    private int stackSize;
    private int top;
    private float[] stackArr;

    // 생성자는 String 인자로 받아서 초기화 함
    getCalculationResult(int stackSize) {
        top = -1;
        this.stackSize = stackSize;
        stackArr = new float[this.stackSize];
    }

//    // 후위 수식을 배열로 만드는 함수
//    public char[] getStringToCharArray(String postFix) {
//        return postFix.toCharArray();
//    }

    private void error(String message) {
        Log.d("ERROR : ", message);
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public boolean isFull() {
        return (top == stackSize - 1);
    }

    @Override
    public void push(float item) {
        if (isFull()) {
            error("Stack is Full!!");
            System.exit(-1);
        } else {
            stackArr[++top] = item;
        }
    }

    @Override
    public float pop() {
        if (isEmpty()) {
            error("Stack is Empty!!");
            System.exit(-1);
        }
        return stackArr[top--];
    }


    @Override
    public float peek() {
        if (isEmpty()) {
            error("Stack is Empty!!");
            //System.exit(-1);
        }
        return stackArr[top];
    }

    public float Calculation(char[] postFixArray) {
        int postFixLength = postFixArray.length, value;
        char ch;
        String opBuffer1, opBuffer2;
        float Operation1, Operation2;
        // int로 파싱가능 (아래 Calculation 주석 참조)
        StringBuilder op1 = new StringBuilder();
        StringBuilder op2 = new StringBuilder();
        // TODO '(' ')' 처리문제
        for (int i = 0; i < postFixLength; i++) {
            ch = postFixArray[i];
            if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                value = ch-'0';
                if(ch == ' '){
                    push(ch);
                }else {
                    push(value);
                }
            } else {           // 연산자이면 피연산자를 스택에서 제거
                if (peek() == ' ') {
                    pop();              // 공백이 POP 됨
                    while (peek() != ' ') {
                        op2.insert(0, pop());
                    }
                    pop();              // 공백이 POP 됨
                    // peek 값이 공백이 아니거나 스택이 비어있지 않다면
                    while (peek() != ' ') {
                        op1.insert(0, pop());
                    }
                }
                opBuffer1 = String.valueOf(op1);
                opBuffer2 = String.valueOf(op2);
                Operation1 = Float.parseFloat(opBuffer1);
                Operation2 = Float.parseFloat(opBuffer2);
                switch (ch) {
                    case '+':
                        push(Operation1 + Operation2);
                        break;
                    case '-':
                        push(Operation1 - Operation2);
                        break;
                    case '*':
                        push(Operation1 * Operation2);
                        break;
                    case '/':
                        push(Operation1 / Operation2);
                        break;
                        // TODO : Layout Button 나머지(%) 추가해라..
                    case '%':
                        push(Operation1 % Operation2);
                        break;
                }
                op2.delete(0,op2.length());
                op1.delete(0,op1.length());
            }
        }
        return pop();
    }
}

//    char Calculation(char[] resultChar) {
//        int Operation1, Operation2, emptyCount = 0;
//        String opBuffer1,opBuffer2;
//        int resultCharLen = resultChar.length;
//        int a = 0;
//        char ch;
//        // TODO : StringBUilder를 이용하여 두자리수 이상 후위표기 연산기능 구현
//        StringBuilder op1 = new StringBuilder("");
//        StringBuilder op2 = new StringBuilder("");
//        // 연산을 진행하는 Loop
//        for (int i = 0; i < resultCharLen; i++) {
//            ch = resultChar[i];
//            if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
//                // TODO : 공백을 이용하여 두자리수 이상 연산자를 붙여야 함
//                push(ch);
//            } else {           // 연산자이면 피연산자를 스택에서 제거
//                if(peek() == ' '){
//                    pop();              // 공백이 POP 됨
//                    while(peek() != ' ') {
//                        op2.insert(0, pop());
//                    }
//                    pop();
//                    // peek 값이 공백이 아니거나 스택이 비어있지 않다면
//                    // TODO : ArrayIndexOutOfBoundsException
//                    while(peek() != ' '){
//                        op1.insert(0, pop());
//                    }
//
//                }
////                Operation2 = pop();             // Pop 된 연산자
////                Operation1 = pop();             // Pop 된 연산자
//                // TODO : 연산 진행 시 Char -> Int 로 Parsing 필요
//                opBuffer1 = String.valueOf(op2);
//                opBuffer2 = String.valueOf(op1);
//                Operation1 = Integer.parseInt(opBuffer1);
//                Operation2 = Integer.parseInt(opBuffer2);
////                switch (ch) {
////                    case '+':
////                        push((char) (Operation1 + Operation2));
////                        break;
////                    case '-':
////                        push((char) (Operation1 - Operation2));
////                        break;
////                    case '*':
////                        push((char) (Opera tion1 * Operation2));
////                        break;
////                    case '/':
////                        push((char) (Operation1 / Operation2));
////                        break;
////                }
//            }
//        }
//        return pop();
//    }