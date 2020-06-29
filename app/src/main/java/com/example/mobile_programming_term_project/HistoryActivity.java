package com.example.mobile_programming_term_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class HistoryActivity extends AppCompatActivity {
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tx = findViewById(R.id.text);
        try {
            BufferedReader historyReader = new BufferedReader(
                    new FileReader(getFilesDir() + "data.txt"));
            String b = MainActivity.resultQueue.peek();
            // TODO : 파일 읽어와서 큐에 저장한 후에 출력시켜야 함
            tx.setText(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}