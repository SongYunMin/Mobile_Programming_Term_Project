package com.example.mobile_programming_term_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    final int STACK_MAX_SIZE = 100;
    int equalClickNumber = 0;
    public static EditText editText;
    public static int newData;
    public Button[] buttons = null;
    public Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C, btn_fileIn, btn_fileOut;
    public Button btn_storage, btn_history, btn_quiz;

    static Queue<String> resultQueue = new LinkedList<>(); // 연결리스트 이용 큐 생성
    static LinkedList<String> historyList = new LinkedList<String>(); // 히스토리 기능 리스트 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 히스토리 기능을 위한 연결리스트 생성
        int status = 0;
        // Status Bar 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // 중위표기 -> 후위표기식 객체 선언
        final getPostFix infixToPostFix = new getPostFix(STACK_MAX_SIZE);
        // 후위표기식 계산 기능 객체 선언
        final getCalculationResult calculationResult = new getCalculationResult(STACK_MAX_SIZE);

        buttons = new Button[16];
        int[] btn_id = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_multi, R.id.btn_division, R.id.btn_eq, R.id.btn_mod};

        editText = findViewById(R.id.edit);                 // 결과 출력 editText
        btn_history = findViewById(R.id.history);               // 히스토리 기능 버튼
        btn_storage = findViewById(R.id.memory_btn);            // 파일 수식 입력 버튼
        btn_quiz = findViewById(R.id.quiz);
        btn_fileIn = findViewById(R.id.file_input);         // 파일 입력 버튼
        btn_fileOut = findViewById(R.id.file_output);       // 파일 출력 버튼
        btn_XOR = (Button) findViewById(R.id.btn_XOR);      // XOR 연산 버튼
        btn_AND = (Button) findViewById(R.id.btn_AND);      // AND 연산 버튼
        btn_OR = (Button) findViewById(R.id.btn_OR);        // OR 연산 버튼
        btn_NOT = (Button) findViewById(R.id.btn_NOT);      // NOT 연산 버튼
        btn_eq = (Button) findViewById(R.id.btn_eq);        // '=' 버튼
        btn_C = (Button) findViewById(R.id.btn_c);          // 입력 초기화 버튼
        // 버튼들의 ID 받아옴
        for (int value : btn_id) findViewById(value).setOnClickListener(mClickListener);
        // 계산식 지우기
        btn_C.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
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


        btn_quiz.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        // 파일 출력 기능
        btn_fileOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String buf;
                BufferedWriter fileWriter = null;                       // 파일출력 변수 초기화
                try {
                    // 파일을 출력 시킬
                    fileWriter = new BufferedWriter(
                            new FileWriter(getFilesDir() + "file.txt", false));
                    buf = editText.getText().toString();
                    fileWriter.write(buf);
                    fileWriter.newLine();
                    editText.setText(null);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // 파일 입력 기능
        btn_fileIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String buf;
                BufferedReader fileReader = null;
                try {
                    fileReader = new BufferedReader(
                            new FileReader(getFilesDir() + "file.txt"));
                    buf = fileReader.readLine();
                    editText.setText(buf);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // '=' 를 누를때 발생하는 이벤트
        btn_eq.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                equalClickNumber++;
                final StringBuilder fileResult = new StringBuilder("");     // 파일 입출력 될 변수
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
                fileResult.append(" = ").append(String.valueOf(result));
                // 파일 입출력 ex) 12*36/12 = 36.0
                try {
                    BufferedWriter fileWriter = new BufferedWriter(
                            new FileWriter(getFilesDir() + "data.txt", true));
                    fileWriter.write(String.valueOf(fileResult));       // 계산결과 파일 출력
                    resultQueue.offer(String.valueOf(fileResult));      // 계산결과 Queue 삽입
                    historyList.add(String.valueOf(fileResult));        // 계산결과 list 삽입
                    // 계산 결과 변수 초기화
                    fileResult.delete(0, fileResult.length());          // String 초기화
                    fileWriter.newLine();                               // 개행
                    fileWriter.close();                                 // 파일 닫음
                } catch (IOException e) {                               // 예외처리 catch
                    Log.e("ERROR : ", "File I/O Error");    // Log 출력
                    e.printStackTrace();
                }
                newData = 0;
            }
        });

        // 내장메모리 입력
        btn_storage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.i("내장메모리 : ", "테스트입니다.");
                try {
                    // Queue 에 데이터가 없다면
                    if (resultQueue.peek().equals(null)) {
                        Toast.makeText(MainActivity.this, "입력된 수식이 없습니다",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String buf;                                         // 버퍼 생성
                        // 파일 Read 변수 초기화
                        BufferedReader fileReader = new BufferedReader(
                                new FileReader(getFilesDir() + "data.txt"));
                        buf = fileReader.readLine();                        // 파일을 읽음
                        editText.setText(resultQueue.poll());               // edit에 출력
                    }
                } catch (FileNotFoundException e) {                         // 파일 예외처리
                    e.printStackTrace();
                } catch (IOException e) {                                   // 입출력 예외처리
                    e.printStackTrace();
                }
            }
        });

        // TODO : 히스토리 기능 구현
        btn_history.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // History Activity 로 이동
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
                // Log.i("HISTORY : ", "테스트입니다.");
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
