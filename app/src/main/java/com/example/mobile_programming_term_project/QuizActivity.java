package com.example.mobile_programming_term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuizActivity extends AppCompatActivity {
    public String[] example;
    public TextView workBook;
    public EditText answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        workBook = findViewById(R.id.example);
        answer = findViewById(R.id.result);
        example = new String[]{" 10+5-3", " 13+23*13", " 51+87*2", " 10/5*3", " 45+65-3", " 98*5/13",
                " 23+51-3", " 10-5*23", " 82+5*61", " 104-52*13", " 41*21+23"};
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter(getFilesDir() + "quiz.txt", true));
            for (int i = 0; i < example.length; i++) {
                fileWriter.write(example[i]);
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test Code
//        String test = " 10+5-3";
//        int testint;
//        char[] test_;
//        getPostFix infixToPostfix = new getPostFix(100);
//        getCalculationResult result = new getCalculationResult(100);
//
//        test_ = infixToPostfix.infixToPostfix(test.toCharArray());
//        testint = (int) result.Calculation(test_);
//
//        System.out.println(testint);

    }
    @Override
    protected void onResume() {
        super.onResume();
        String buf;

        // TODO : edit 로 입력받은값을 workBook 을 통해 출력된 text 를 객체로 보내 계산후 결과 비교
        workBook.setText(example[(int)((Math.random()*10000)%10)]);

        System.out.println("test");


    }

}