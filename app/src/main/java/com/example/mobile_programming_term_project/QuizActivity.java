package com.example.mobile_programming_term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuizActivity extends AppCompatActivity {
    public String[] example;
    public TextView workBook, result, truenum;
    public Button submit;
    public EditText answer;
    public int trueCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        workBook = findViewById(R.id.example);
        answer = findViewById(R.id.edit);
        result = findViewById(R.id.trueorfalse);
        submit = findViewById(R.id.submit);
        truenum = findViewById(R.id.truenum);

        example = new String[]{" 10+5-3", " 13+23*13", " 51+87*2", " 10/5*3", " 45+65-3", " 98*5/13",
                " 23+51-3", " 10-5*23", " 82+5*61", " 104-52*13", " 41*21+23"};
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter(getFilesDir() + "quiz.txt", true));
            // 계산식을 전부 읽어들임
            for (int i = 0; i < example.length; i++) {
                fileWriter.write(example[i]);
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final String buf_1;
        // TODO : edit 로 입력받은값을 workBook 을 통해 출력된 text 를 객체로 보내 계산후 결과 비교
        workBook.setText(example[(int) ((Math.random() * 10000) % 10)]);

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String buf_1, buf_2;
                char[] buf_3;
                float equal_op1;
                int equal_op2;
                buf_1 = workBook.getText().toString();            // buf_1 :  문제
                buf_2 = answer.getText().toString();           // buf_2 : 제출 답
                equal_op2 = Integer.parseInt(buf_2);
                getPostFix postFix = new getPostFix(100);
                getCalculationResult calculation = new getCalculationResult(100);
                buf_3 = postFix.infixToPostfix(buf_1.toCharArray());
                equal_op1 = (int) calculation.Calculation(buf_3);
                // 문제의 답과 제출 답이 같다면
                if (equal_op1 == equal_op2) {
                    result.setText("정답입니다!");
                    result.setTextColor(Color.parseColor("#00FFFF"));
                    workBook.setText(example[(int) ((Math.random() * 10000) % 10)]);
                    answer.setText(null);
                    trueCount++;
                    buf_2 = String.valueOf(trueCount);
                    truenum.setText("맞춘개수 : " + buf_2);
                } else {
                    result.setText("오답입니다!");
                    result.setTextColor(Color.parseColor("#FF0000"));
                }


            }
        });


    }

}