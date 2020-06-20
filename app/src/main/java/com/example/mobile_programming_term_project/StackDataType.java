package com.example.mobile_programming_term_project;

import android.util.Log;

public class StackDataType implements Stack {
    final public int MAX_STACK_SIZE = 100;
    // private는 getter/setter의 존재 이유
    private int top;
    private int stackSize;
    private char[] stackArr;
    public char[] resultChar;

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


//    @Override
//    public int pop(int a)
//    {
//        if (isEmpty()) {
//            error("Stack is Empty!!");
//            System.exit(-1);
//        }
//        Log.i("Pop : ", "");
//        return (int)stackArr[top--];
//    }


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
    char[] infixToPostfix(char[] exp) {
        int i = 0;
        char ch, top_op;
        int len = exp.length;
        StackDataType stack = new StackDataType(stackSize);
        StringBuilder result = new StringBuilder("");

        init_stack(stack);                  // 스택 초기화
        for (i = 0; i < len; i++) {
            ch = exp[i];
            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!isEmpty() && (returnOfPriority(ch) <=
                            returnOfPriority(peek()))) {
                        // Todo 3. 출력문 필요
                        result.append(pop());
                    }
                    push(ch);
                    break;
                case '(':        // 왼 쪽 괄호
                    push(ch);
                    break;
                case ')':        // 오른쪽 괄호
                    top_op = pop();
                    // 왼쪽 괄호를 만날때 까지 출력
                    while (top_op != '(') {
                        // Todo 3. 출력문 필요
                        result.append(top_op);
                        top_op = pop();
                    }
                    break;
                default:
                    // Todo 3. 출력문 필요
                    result.append(ch);
                    break;
            }
        }
        while (!isEmpty()) {
            result.append(pop());
            Log.i("Result : ", String.valueOf(result));
            resultChar = String.valueOf(result).toCharArray();
        }
        return resultChar;
    }

    char Calculation(char[] resultChar) {
        int Operation1, Operation2, Value, i = 0;
        int FormualLen = resultChar.length;
        int a=0;
        char ch;

        for (i = 0; i < FormualLen; i++) {
            ch = resultChar[i];
            if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                Value = ch - '0';       // 입력이 피연산자 이면
                push((char) Value);
            } else {           // 연산자이면 피연산자를 스택에서 제거
                Operation2 = pop();
                Operation1 = pop();
                switch (ch) {
                    case '+':
                        push((char) (Operation1 + Operation2));
                        break;
                    case '-':
                        push((char) (Operation1 - Operation2));
                        break;
                    case '*':
                        push((char) (Operation1 * Operation2));
                        break;
                    case '/':
                        push((char) (Operation1 / Operation2));
                        break;
                }
            }
        }
        return pop();
    }
}
