package com.example.mobile_programming_term_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button btn0_9[] = null;
    private Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C;
    private Button btn_plus, btn_minus, btn_mul, btn_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0_9 = new Button[10];
        int btn_id[] = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};
        editText = findViewById(R.id.edit);
        // 0번부터 9번까지의 버튼 ID 받아옴
            for (int i = 0; i < btn_id.length; i++) {
            this.btn0_9[i] = (Button) findViewById(btn_id[i]);
        }
        for(int i=0;i<btn_id.length;i++){
            this.btn0_9[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 10; i++) {
                        if(v.getId() == btn0_9[i].getId()){
                            Toast.makeText(MainActivity.this, i+" 클릭됨",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}