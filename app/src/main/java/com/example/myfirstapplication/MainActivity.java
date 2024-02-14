package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String num1 = "";
    String num2 = "";
    String operation = "";
    TextView result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.Calc);
    }

    private void handleArithmeticOperation(String operator) {
        num1 = result.getText().toString().trim();
        operation = operator;
        result.setText("");
    }

    private void calculateResult() {
        num2 = result.getText().toString().trim();
        if (!num1.isEmpty() && !num2.isEmpty() && !operation.isEmpty()) {
            try {
                double i = Double.parseDouble(num1);
                double y = Double.parseDouble(num2);
                double ans = 0;

                switch (operation) {
                    case "+":
                        ans = i + y;
                        break;
                    case "-":
                        ans = i - y;
                        break;
                    case "X":
                        ans = i * y;
                        break;
                    case "/":
                        if (y != 0) {
                            ans = i / y;
                        } else {
                            result.setText("Error: Division by zero");
                            return; // Exit the function to avoid setting the result twice
                        }
                        break;
                }

                if (ans % 1 == 0) {
                    result.setText(String.valueOf((int) ans));
                } else {
                    result.setText(String.format("%.5f", ans));
                }
            } catch (NumberFormatException e) {
                result.setText("Error");
            }
        }
    }

    private void handleDecimalPoint(String str) {
        String currentText = result.getText().toString();
        if (!currentText.contains(".")) {
            result.append(str);
        }
    }

    private void handleNegation() {
        String currentResult = result.getText().toString();
        if (!currentResult.isEmpty()) {
            double currentValue = Double.parseDouble(currentResult);
            result.setText(String.valueOf(-currentValue));
        }
    }

    private void handlePercentage() {
        num1 = result.getText().toString().trim();
        if (!num1.isEmpty()) {
            double value = Double.parseDouble(num1);
            result.setText(String.valueOf(value / 100));
        }
    }

    public void funcNumbers(View view) {
        Button button = (Button) view;
        String str = button.getText().toString();

        switch (str) {
            case "+":
            case "-":
            case "X":
            case "/":
                handleArithmeticOperation(str);
                break;
            case "=":
                calculateResult();
                break;
            case "AC":
                result.setText("");
                break;
            case ".":
                handleDecimalPoint(str);
                break;
            case "+/-":
                handleNegation();
                break;
            case "%":
                handlePercentage();
                break;
            default:
                result.append(str);
                break;
        }
    }
}
