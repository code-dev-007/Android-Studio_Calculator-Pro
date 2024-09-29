package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    Button btnOn, btnOff, btnAc, btnDel, btnDiv, btnMul, btnSub, btnAdd, btnEquals;
    Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnDot;

    private String input = "";
    private String operator = "";
    private double num1 = 0; // Changed to double
    private boolean isOperatorPressed = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the UI components
        screen = findViewById(R.id.screen);

        btnOn = findViewById(R.id.On);
        btnOff = findViewById(R.id.off);
        btnAc = findViewById(R.id.ac);
        btnDel = findViewById(R.id.del);
        btnDiv = findViewById(R.id.div);
        btnMul = findViewById(R.id.mul);
        btnSub = findViewById(R.id.sub);
        btnAdd = findViewById(R.id.add);
        btnEquals = findViewById(R.id.equals);

        btnNum0 = findViewById(R.id.num0);
        btnNum1 = findViewById(R.id.num1);
        btnNum2 = findViewById(R.id.num2);
        btnNum3 = findViewById(R.id.num3);
        btnNum4 = findViewById(R.id.num4);
        btnNum5 = findViewById(R.id.num5);
        btnNum6 = findViewById(R.id.num6);
        btnNum7 = findViewById(R.id.num7);
        btnNum8 = findViewById(R.id.num8);
        btnNum9 = findViewById(R.id.num9);
        btnDot = findViewById(R.id.dot);

        // Handling button clicks
        setUpNumberButtonClicks();
        setUpOperatorButtonClicks();
    }

    // Handling number button clicks
    private void setUpNumberButtonClicks() {
        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            input += button.getText().toString();
            screen.setText(input);
        };

        btnNum0.setOnClickListener(numberClickListener);
        btnNum1.setOnClickListener(numberClickListener);
        btnNum2.setOnClickListener(numberClickListener);
        btnNum3.setOnClickListener(numberClickListener);
        btnNum4.setOnClickListener(numberClickListener);
        btnNum5.setOnClickListener(numberClickListener);
        btnNum6.setOnClickListener(numberClickListener);
        btnNum7.setOnClickListener(numberClickListener);
        btnNum8.setOnClickListener(numberClickListener);
        btnNum9.setOnClickListener(numberClickListener);
        btnDot.setOnClickListener(numberClickListener);
    }

    // Handling operator button clicks
    private void setUpOperatorButtonClicks() {
        btnAdd.setOnClickListener(v -> handleOperator("+"));
        btnSub.setOnClickListener(v -> handleOperator("-"));
        btnMul.setOnClickListener(v -> handleOperator("X"));
        btnDiv.setOnClickListener(v -> handleOperator("/"));

        btnEquals.setOnClickListener(v -> handleEquals());

        btnAc.setOnClickListener(v -> {
            input = "";
            num1 = 0;
            operator = "";
            isOperatorPressed = false;
            screen.setText("0");
        });

        btnDel.setOnClickListener(v -> {
            if (input.length() > 0) {
                input = input.substring(0, input.length() - 1);
                screen.setText(input.isEmpty() ? "0" : input);
            }
        });

        btnOn.setOnClickListener(v -> screen.setText("0"));
        btnOff.setOnClickListener(v -> screen.setText(""));
    }

    // Handling operator selection
    private void handleOperator(String op) {
        if (!isOperatorPressed) {
            num1 = Double.parseDouble(input); // Changed to Double.parseDouble
            input = "";
            operator = op;
            isOperatorPressed = true;
            screen.setText(num1 + " " + operator); // Show operator on the screen
        }
    }

    // Handling equals button click
    private void handleEquals() {
        double num2 = Double.parseDouble(input); // Changed to double
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "X":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    screen.setText("Error");
                    return;
                }
                break;
        }

        // Convert result to int if it is a whole number
        if (result == (int) result) {
            screen.setText(num1 + " " + operator + " " + num2 + " = " + (int) result); // Cast to int for whole number results
        } else {
            screen.setText(num1 + " " + operator + " " + num2 + " = " + result); // Show result as double if it has decimals
        }

        input = "";
        operator = "";
        isOperatorPressed = false;
    }
}
