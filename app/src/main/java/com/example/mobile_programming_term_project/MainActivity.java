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
    public Button btn0_9[] = null;
    public Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C;
    public Button btn_add, btn_sub, btn_mul, btn_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0_9 = new Button[14];
        int[] btn_id = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,R.id.btn_add,R.id.btn_sub,
                R.id.btn_multi,R.id.btn_division};

        editText = findViewById(R.id.edit);
        // 버튼들의 ID 받아옴
        for (int i = 0; i < btn_id.length; i++) {
            this.btn0_9[i] = (Button) findViewById(btn_id[i]);
        }
        // 비트 연산자
        btn_XOR = (Button) findViewById(R.id.btn_XOR);
        btn_AND = (Button) findViewById(R.id.btn_AND);
        btn_OR = (Button) findViewById(R.id.btn_OR);
        btn_NOT = (Button) findViewById(R.id.btn_NOT);
        btn_eq = (Button) findViewById(R.id.btn_eq);
        btn_C = (Button) findViewById(R.id.btn_c);

        // Click Listener 붙임
        for (int i = 0; i < btn_id.length; i++) {
            this.btn0_9[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 14; i++) {
                        if (v.getId() == btn0_9[i].getId()) {
                            // Click 구현부
                            Button a = (Button) v;
                            editText.append(a.getText());
                        }
                    }
                }
            });
        }
        // 계산식 지우기
        btn_C.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editText.setText(null);
            }
        });
        // result
        btn_eq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button button = (Button) v;
                String ClickValue = button.getText().toString();

                switch(ClickValue){
                    case "+":
                    case "-":
                    case "*":
                    case "/":

                }
            }

        });




    }
}