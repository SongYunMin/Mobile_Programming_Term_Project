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
//
//    @Override
//    public void push(String item) {
//        char[] item_arr = item.toCharArray();
//        if (isFull()) {
//            Log.e("ERROR : ", "Stack Over Flow");
//            System.exit(-1);
//        } else {
//            stackArr[++top] = item_arr;
//        }
//    }

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

//    char[] infixToPostfix(char[] exp) {
//        int i = 0;
//        char ch, top_op;
//        int len = exp.length;
//        StackDataType stack = new StackDataType(stackSize);
//        StringBuilder result = new StringBuilder("");
//
//        init_stack(stack);

//        return result;
//    }

    // 중위수식 -> 후위수식 변환
    char[] infixToPostfix(char[] exp) {
        int i = 0;
        char ch, top_op;
        int len = exp.length;
        StackDataType stack = new StackDataType(stackSize);
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
                        // Todo 3. 출력문 필요
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
                        // Todo 3. 출력문 필요
                        result.append(top_op);
                        result.append(' ');
                        top_op = pop();
                    }
                    break;
                default:                            // 피연산자 라면?
                    result.append(ch);
                    // 아스키 코드 이용
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

    char Calculation(char[] resultChar) {
        int Operation1, Operation2;
        int Value, i = 0;
        int resultCharLen = resultChar.length;
        int a = 0;
        char ch;
        // TODO : StringBUilder를 이용하여 두자리수 이상 후위표기 연산기능 구현
        StringBuilder op1 = new StringBuilder("");
        StringBuilder op2 = new StringBuilder("");

        for (i = 0; i < resultCharLen; i++) {
            ch = resultChar[i];
            while (resultChar[i] != ' ') {          // i+1 index가 공백이 아닐 때 까지
                // 입력이 연산자가 아니라면?
                if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                    push(ch);
                }
            }
        }

        for (i = 0; i < resultCharLen; i++) {
            ch = resultChar[i];
            if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                Value = ch - '0';       // 입력이 피연산자 이면
                push((char) Value);     // Stack Push

            } else {           // 연산자이면 피연산자를 스택에서 제거
                Operation2 = pop();             // Pop 된 연산자
                Operation1 = pop();             // Pop 된 연산자
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
