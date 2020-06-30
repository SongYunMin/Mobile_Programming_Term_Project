package com.example.mobile_programming_term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HistoryActivity extends AppCompatActivity {
    private ArrayList<String> arrayData;
    static public LinkedList<String> historyList = new LinkedList<String>();
    public static Context mContext;
    public static ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mContext = getApplicationContext();
        mListView = (ListView) findViewById(R.id.list_data);

        // 초기 List 불러오는 분기
        // Main 에서 새로운 수식이 입력되지 않았다면 실행하지 않음
        if (MainActivity.newData == 0) {
            try {
                historyList.clear();
                BufferedReader historyReader = new BufferedReader(
                        new FileReader(getFilesDir() + "data.txt"));
                String buf = null;
                while ((buf = historyReader.readLine()) != null) {
                    historyList.add(buf);
                }
                // TODO : 파일 읽어와서 리스트에 저장한 후에 출력시켜야 함
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 출력되어 초기화가 되었다고 Main에 알림
            MainActivity.newData++;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        // 기능들 id 불러움
        Button btnReset = findViewById(R.id.reset);
        Button btnInsert = findViewById(R.id.insert);
        Button btnDelete = findViewById(R.id.delete);
        final LinearLayout root = findViewById(R.id.root);
        isListAdapter mListAdapter = new isListAdapter(mContext, historyList);
        mListView.setAdapter(mListAdapter);

        // 파일 초기화
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedWriter fileWriter = new BufferedWriter(
                            new FileWriter(getFilesDir() + "data.txt", false)
                    );
                    fileWriter.close();
                    root.removeAllViews();
                    setContentView(R.layout.activity_history);
                    Toast.makeText(HistoryActivity.this,
                            "성공적으로 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}


