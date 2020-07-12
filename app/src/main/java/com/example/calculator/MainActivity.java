package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView currentView, previousView;
    private Boolean hasPreviousResult;
    private Boolean hasPoint;

    private double operandOne = Double.NaN;
    private double operandTwo = Double.NaN;
    private String operator = null;

    private DecimalFormat decimalFormatter = new DecimalFormat("#.########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentView = findViewById(R.id.current);
        previousView = findViewById(R.id.previous);

        hasPoint = false;
        hasPreviousResult = false;
    }

    @SuppressLint("SetTextI18n")
    public void addOperand(View view) {

        Button btn = (Button) view;
        String text = btn.getText().toString();

        currentView.setText(currentView.getText() + text);
    }

    @SuppressLint("SetTextI18n")
    public void addPoint(View view) {

        if (!hasPoint) {
            if (currentView.getText().equals("")) currentView.setText("0.");
            else currentView.setText(currentView.getText() + ".");

            hasPoint = true;
        }
    }


    @SuppressLint("SetTextI18n")
    public void addOperator(View view) {

        if (!currentView.getText().equals("")) {

            equals(view);

            Button btn = (Button) view;

            switch (btn.getId()) {

                case R.id.operator_add: operator = "+";
                    break;

                case R.id.operator_subtract: operator = "-";
                    break;

                case R.id.operator_multiply: operator = "*";
                    break;

                case R.id.operator_divide: operator = "/";
                    break;

            }

            operandOne = Double.parseDouble(currentView.getText().toString());
            previousView.setText(currentView.getText().toString() + operator);
            currentView.setText(null);
            hasPoint = false;
        }
    }


    @SuppressLint("SetTextI18n")
    public void equals(View view) {

        if (!currentView.getText().equals("")) {

            operandTwo = Double.parseDouble(currentView.getText().toString());

            if (operator != null) {

                switch (operator) {

                    case "+": operandOne += operandTwo;
                        break;

                    case "-": operandOne -= operandTwo;
                        break;

                    case "*": operandOne *= operandTwo;
                        break;

                    case "/": operandOne /= operandTwo;
                        break;
                }

                previousView.setText(previousView.getText().toString() + currentView.getText().toString());
                currentView.setText(decimalFormatter.format(operandOne));
                operandTwo = Double.NaN;
                operator = null;
                hasPreviousResult = true;
                hasPoint = false;
            }
        }
    }


    public void clearAll(View view) {

        currentView.setText(null);
        previousView.setText(null);
        operator = null;
        operandOne = Double.NaN;
        operandTwo = Double.NaN;
        hasPreviousResult = false;
        hasPoint = false;
    }


    public void clear(View view) {

        if (hasPreviousResult) clearAll(view);

        else {

            if (currentView.getText().equals("")) currentView.setText(null);

            else {
                int len = currentView.getText().length();
                String str = currentView.getText().toString();

                if (str.charAt(len - 1) == '.') hasPoint = false;

                currentView.setText(currentView.getText().subSequence(0, currentView.getText().length() - 1));
            }
        }
    }

}