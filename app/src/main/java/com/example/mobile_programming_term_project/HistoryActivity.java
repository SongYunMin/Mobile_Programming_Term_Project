package com.example.mobile_programming_term_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        final Context context = this;
        final EditText listNumber = new EditText(context);
        final EditText resultText = new EditText(context);

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
                    setContentView(R.layout.activity_history);       // setContentView 이용 재배치
                    Toast.makeText(HistoryActivity.this,
                            "성공적으로 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //리스트 삭제 연산
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("계산식 삭제");
                // AlertDialog Setting
                alertDialogBuilder
                        .setMessage("삭제할 Index(자리) 를 입력해 주십시오")
                        .setCancelable(false)
                        .setView(listNumber)
                        .setPositiveButton("삭제",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final String indexBuf = listNumber.getText().toString();
                                        final int indexToInt = Integer.parseInt(indexBuf);
                                        historyList.remove(indexToInt);
                                        try {
                                            BufferedWriter fileWriter = new BufferedWriter(
                                                    new FileWriter(getFilesDir() + "data.txt", false)
                                            );
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            BufferedWriter fileWriter = new BufferedWriter(
                                                    new FileWriter(getFilesDir() + "data.txt", true)
                                            );
                                            for (int i = 0; i < historyList.size(); i++) {
                                                String buf = historyList.get(i);
                                                fileWriter.write(buf);
                                                fileWriter.newLine();
                                            }
                                            fileWriter.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(context, indexBuf + "번 데이터가 삭제 되었습니다", Toast.LENGTH_SHORT).show();
                                        finish();
                                        root.removeAllViews();
                                        setContentView(R.layout.activity_history);
                                        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        // 리스트 삽입 연산
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 활성화 메소드 Context 가 필요함
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                final AlertDialog.Builder alertDialogBuilder_ = new AlertDialog.Builder(context);
                // 제목 세팅
                alertDialogBuilder.setTitle("계산식 삽입");
                // AlertDialog Setting
                alertDialogBuilder
                        .setMessage("삽입할 Index(자리) 를 입력해 주십시오")
                        .setCancelable(false)
                        .setView(listNumber)
                        .setPositiveButton("다음",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final String indexBuf = listNumber.getText().toString();
                                        final int indexToInt = Integer.parseInt(indexBuf);
                                        alertDialogBuilder_.setTitle("계산식 삽입");
                                        alertDialogBuilder_
                                                .setMessage("삽입할 수식을 입력해 주십시오")
                                                .setCancelable(false)
                                                .setView(resultText)
                                                .setPositiveButton("삽입",
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                String resultBuf = resultText.getText().toString();
                                                                historyList.add(indexToInt, resultBuf);
                                                                try {
                                                                    BufferedWriter fileWriter = new BufferedWriter(
                                                                            new FileWriter(getFilesDir() + "data.txt", false)
                                                                    );
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }

                                                                try {
                                                                    BufferedWriter fileWriter = new BufferedWriter(
                                                                            new FileWriter(getFilesDir() + "data.txt", true)
                                                                    );
                                                                    for (int i = 0; i < historyList.size(); i++) {
                                                                        String buf = historyList.get(i);
                                                                        fileWriter.write(buf);
                                                                        fileWriter.newLine();
                                                                    }
                                                                    fileWriter.close();
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                Toast.makeText(HistoryActivity.this, indexBuf + "번 자리에 정상 삽입 되었습니다",
                                                                        Toast.LENGTH_SHORT).show();
                                                                finish();
                                                                root.removeAllViews();
                                                                setContentView(R.layout.activity_history);
                                                                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        })
                                                .setNegativeButton("취소",
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                        AlertDialog alertDialog = alertDialogBuilder_.create();
                                        alertDialog.show();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

}


