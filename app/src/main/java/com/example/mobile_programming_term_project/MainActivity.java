package com.example.mobile_programming_term_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final int STACK_MAX_SIZE = 100;
    private EditText editText;
    public Button btn0_9[] = null;
    public Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C;
    public String operator = null;
    private String fValue = "";
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0_9 = new Button[14];
        int[] btn_id = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_multi, R.id.btn_division, R.id.btn_XOR, R.id.btn_AND, R.id.btn_OR,
                R.id.btn_NOT, R.id.btn_eq};

        editText = findViewById(R.id.edit);
        // 버튼들의 ID 받아옴
        for (int i = 0; i < btn_id.length; i++) {
            findViewById(btn_id[i]).setOnClickListener(mClickListener);
        }
        // 비트 연산자
        btn_XOR = (Button) findViewById(R.id.btn_XOR);
        btn_AND = (Button) findViewById(R.id.btn_AND);
        btn_OR = (Button) findViewById(R.id.btn_OR);
        btn_NOT = (Button) findViewById(R.id.btn_NOT);
        btn_eq = (Button) findViewById(R.id.btn_eq);
        btn_C = (Button) findViewById(R.id.btn_c);
        String buf = "Test String Code";
        // Final 선언을 해주어야 inner Class 접근가능
        final StackDataType result = new StackDataType(STACK_MAX_SIZE);

        // 계산식 지우기
        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
            }
        });

        // Todo 1. '=' 문자 Click 시 결과 String toCharArray 메소드 이용, Char 배열 생성
        // Todo 2. StackDataType Object 생성, infix -> Postfix
        // result
        btn_eq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String infix = editText.getText().toString();       // EditText에서 얻음
                char[] PostFix = infix.toCharArray();               // String -> char[]
                char[] buf;
                char buf_2;
                buf = result.infixToPostfix(PostFix);
                buf_2 = result.Calculation(buf);
            }

        });

//        // result
//        btn_eq.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View v) {
//                Button button = (Button) v;
//                String ClickValue = button.getText().toString();
//                switch (ClickValue) {
//                    case "+":
//                    case "-":
//                    case "*":
//                    case "/":
//                    case "=":
//                        if ("".equals(fValue)) {
//                            fValue = editText.getText().toString();
//                            editText.setText("");
//                        } else {
//                            if (!"".equals(operator)) {
//                                String sValue = editText.getText().toString();
//                                Integer cal = 0;
//                                switch (operator) {
//                                    case "+":
//                                        cal = Integer.parseInt(fValue) +
//                                                Integer.parseInt(sValue);
//                                        break;
//                                    case "-":
//                                        cal = Integer.parseInt(fValue) -
//                                                Integer.parseInt(sValue);
//                                        break;
//                                    case "*":
//                                        cal = Integer.parseInt(fValue) *
//                                                Integer.parseInt(sValue);
//                                        break;
//                                    case "/":
//                                        cal = Integer.parseInt(fValue) /
//                                                Integer.parseInt(sValue);
//                                        break;
//                                }
//                                editText.setText(cal.toString());
//                                fValue = "";
//                                isInit = true;
//
//                                if ("=".equals(ClickValue)) {
//                                    operator = "";
//                                    return;
//                                }
//                                fValue = cal.toString();
//                            }
//                            operator = ClickValue;
//                            break;
//
//                        }
//                    default:
//                        if (isInit) {
//                            isInit = false;
//                            editText.setText(ClickValue);
//                        } else {
//                            editText.setText(editText.getText().toString() + ClickValue);
//                        }
//                }
//            }
//
//        });
    }

        Button.OnClickListener mClickListener = new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Button a = (Button) v;
                editText.setText(editText.getText().toString() + a.getText().toString());
            }
        };
    }
