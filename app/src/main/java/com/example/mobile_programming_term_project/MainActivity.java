package com.example.mobile_programming_term_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    final int STACK_MAX_SIZE = 100;
    private EditText editText;
    public Button[] buttons = null;
    public Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Status Bar 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        final StringBuilder fileResult = new StringBuilder("");     // 파일 입출력 될 변수
        buttons = new Button[16];
        int[] btn_id = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_multi, R.id.btn_division, R.id.btn_eq, R.id.btn_mod};

        editText = findViewById(R.id.edit);
        // 버튼들의 ID 받아옴
        for (int value : btn_id) findViewById(value).setOnClickListener(mClickListener);

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
        btn_C.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
            }
        });

        // result
        btn_eq.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String infix = editText.getText().toString();       // EditText에서 얻음
                fileResult.append(editText.getText().toString());   // 먼저 계산식을 붙임
                StringBuilder buf = new StringBuilder();            // 버퍼 필요

                // 후위표기 메소드 return 값 받음
                char[] POSTFIX = infixToPostFix.infixToPostfix(infix.toCharArray());
                buf.append(POSTFIX).insert(0, ' ');     // 끝을 알리기 위한 공백 추가
                String str = String.valueOf(buf);                    // 버퍼를 스트링으로 변환
                POSTFIX = str.toCharArray();                         // 배열로 변환
                // 변환된 값을 후위표기 계산 메소드로 전달 후 return 값 받음
                float result = calculationResult.Calculation(POSTFIX);
                editText.setText(String.valueOf(result));           // 결과 editText 로 출력
                // 계산결과를 파일로 출력할 변수에 이어 붙임
                fileResult.append(" = ").append(String.valueOf(result + '\n'));
                // 파일 입출력 ex) 12*36/12 = 36.0
                try {
                    BufferedWriter fileWriter = new BufferedWriter(
                            new FileWriter(getFilesDir() + "data.txt", true));
                    fileWriter.write(String.valueOf(fileResult));
                    fileResult.delete(0, fileResult.length());
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // 비트 연산자들의 Click Listener
        btn_XOR.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '^');
            }
        });

        btn_AND.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '&');
            }
        });

        btn_OR.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '|');
            }
        });

        btn_NOT.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + '~');
            }
        });
    }

    // 버튼들의 Click Listener
    OnClickListener mClickListener = new OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            Button a = (Button) v;
            editText.setText(editText.getText().toString() + a.getText().toString());
        }
    };
}
