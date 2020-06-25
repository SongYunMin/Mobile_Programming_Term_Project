package com.example.mobile_programming_term_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final int STACK_MAX_SIZE = 100;
    private EditText editText;
    public Button[] btn0_9 = null;
    public Button btn_XOR, btn_AND, btn_OR, btn_NOT, btn_eq, btn_C;

    public String operator = null;
    private String fValue = "";
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0_9 = new Button[14];
        int[] btn_id = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_multi, R.id.btn_division, R.id.btn_XOR, R.id.btn_AND, R.id.btn_OR,
                R.id.btn_NOT, R.id.btn_eq};

        // TODO : 아.. 나머지연산 왜 까먹었지..?

        editText = findViewById(R.id.edit);
        // 버튼들의 ID 받아옴
        for (int i = 0; i < btn_id.length; i++) {
            findViewById(btn_id[i]).setOnClickListener(mClickListener);
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
                String infix = editText.getText().toString();       // EditText에서 얻음
                char[] POSTFIX;
                POSTFIX = infixToPostFix.infixToPostfix(infix.toCharArray());
                editText.setText(String.valueOf(POSTFIX));
                calculationResult.Calculation(POSTFIX);
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
