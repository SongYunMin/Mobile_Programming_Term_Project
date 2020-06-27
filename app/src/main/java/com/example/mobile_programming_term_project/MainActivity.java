package com.example.mobile_programming_term_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final int STACK_MAX_SIZE = 100;
    private EditText editText;
    public Button[] buttons = null;
    public Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Status Bar 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttons = new Button[16];
        int[] btn_id = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_multi, R.id.btn_division, R.id.btn_eq, R.id.btn_mod};

        editText = findViewById(R.id.edit);
        // 버튼들의 ID 받아옴
        // IDE 에서 for- each 추천해서 쓰긴 쓴다만,,,
        for (int value : btn_id) {
            findViewById(value).setOnClickListener(mClickListener);
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
        // 중위표기 -> 후위표기식 객체 선언
        final getPostFix infixToPostFix = new getPostFix(STACK_MAX_SIZE);
        // 후위표기식 계산 기능 객체 선언
        final getCalculationResult calculationResult = new getCalculationResult(STACK_MAX_SIZE);

        // 계산식 지우기
        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
            }
        });

        // result
        btn_eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float result;
                String infix = editText.getText().toString();       // EditText에서 얻음
                String str;
                StringBuilder buf = new StringBuilder();
                // 여기 몬가 이상한디..
                char[] POSTFIX;
                POSTFIX = infixToPostFix.infixToPostfix(infix.toCharArray());
                buf.append(POSTFIX);
                buf.insert(0, ' ');
                str = String.valueOf(buf);
                POSTFIX = str.toCharArray();
                result = calculationResult.Calculation(POSTFIX);
                editText.setText(String.valueOf(result));
            }
        });


        // 비트 연산자들의 Click Listener
        btn_XOR.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '^');
            }
        });

        btn_AND.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '&');
            }
        });

        btn_OR.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '|');
            }
        });

        btn_NOT.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '~');
            }
        });
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
