package com.example.bt3cau4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener buttonListener;
    EditText text;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btResult,
            btReset, btAddition, btMinus, btMultiply, btDivision;
    Integer lastValue=0, currentValue=0;
    Integer firstNum = 0, secondNum = 0;
    String operation = "";
    String expression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.text);
        bt0= (Button) findViewById(R.id.number0);
        bt1 = (Button) findViewById(R.id.number1);
        bt2 = (Button) findViewById(R.id.number2);
        bt3 = (Button) findViewById(R.id.number3);
        bt4 = (Button) findViewById(R.id.number4);
        bt5 = (Button) findViewById(R.id.number5);
        bt6 = (Button) findViewById(R.id.number6);
        bt7 = (Button) findViewById(R.id.number7);
        bt8 = (Button) findViewById(R.id.number8);
        bt9 = (Button) findViewById(R.id.number9);
        btResult = (Button) findViewById(R.id.result);
        btReset = (Button) findViewById(R.id.reset);
        btAddition = (Button) findViewById(R.id.addition);
        btMinus = (Button) findViewById(R.id.minus);
        btMultiply = (Button) findViewById(R.id.multiply);
        btDivision = (Button) findViewById(R.id.division);

        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button bt = (Button) view;
                switch (bt.getId()) {
                    case R.id.number0:
                        setValue(text, "0");
                        break;
                    case R.id.number1:
                        setValue(text, "1");
                        break;
                    case R.id.number2:
                        setValue(text, "2");
                        break;
                    case R.id.number3:
                        setValue(text, "3");
                        break;
                    case R.id.number4:
                        setValue(text, "4");
                        break;
                    case R.id.number5:
                        setValue(text, "5");
                        break;
                    case R.id.number6:
                        setValue(text, "6");
                        break;
                    case R.id.number7:
                        setValue(text, "7");
                        break;
                    case R.id.number8:
                        setValue(text, "8");
                        break;
                    case R.id.number9:
                        setValue(text, "9");
                        break;
                    case R.id.addition:
                        operation = "+";
                        setValue(text,"");
                        if (secondNum > 0) {
                            currentValue = process(firstNum, secondNum);
                            firstNum = currentValue;
                            secondNum = 0;
                            resetValue();
//                            System.out.println("Current: " + currentValue);
                            text.setText(currentValue.toString());
                        }
                        break;
                    case R.id.minus:
                        operation = "-";
                        setValue(text,"");
                        if (secondNum > 0) {
                            currentValue = process(firstNum, secondNum);
                            firstNum = currentValue;
                            secondNum = 0;
                            resetValue();
//                            System.out.println("Current: " + currentValue);
                            text.setText(currentValue.toString());
                        }
                        break;
                    case R.id.multiply:
                        operation = "*";
                        setValue(text,"");
                        if (secondNum > 0) {
                            currentValue = process(firstNum, secondNum);
                            firstNum = currentValue;
                            secondNum = 0;
                            resetValue();
//                            System.out.println("Current: " + currentValue);
                            text.setText(currentValue.toString());
                        }
                        break;
                    case R.id.division:
                        operation = "/";
                        setValue(text,"");
                        if (secondNum > 0) {
                            currentValue = process(firstNum, secondNum);
                            firstNum = currentValue;
                            secondNum = 0;
                            resetValue();
//                            System.out.println("Current: " + currentValue);
                            text.setText(currentValue.toString());
                        }
                        break;
                    case R.id.reset:
                        resetValue();
                        break;
                    case R.id.result:
                        if (secondNum > 0) {
                            currentValue = process(firstNum, secondNum);
                            firstNum = currentValue;
                            secondNum = 0;
                            resetValue();
//                            System.out.println("Current: " + currentValue);
                            text.setText(currentValue.toString());
                        }
                        break;
                }
            }
        };
        bt0.setOnClickListener(buttonListener);
        bt1.setOnClickListener(buttonListener);
        bt2.setOnClickListener(buttonListener);
        bt3.setOnClickListener(buttonListener);
        bt4.setOnClickListener(buttonListener);
        bt5.setOnClickListener(buttonListener);
        bt6.setOnClickListener(buttonListener);
        bt7.setOnClickListener(buttonListener);
        bt8.setOnClickListener(buttonListener);
        btAddition.setOnClickListener(buttonListener);
        btMinus.setOnClickListener(buttonListener);
        btMultiply.setOnClickListener(buttonListener);
        btDivision.setOnClickListener(buttonListener);
        btResult.setOnClickListener(buttonListener);
        btReset.setOnClickListener(buttonListener);
    }

    public void setValue(EditText a, String b) {
        if (!b.equals("")) {
            String last = a.getText().toString();
            if(!last.equals("0")) {
                last +=b;
                b=last;
            }
        }
        String content = "";
        if (firstNum == 0 || operation.equals("")) {
            firstNum = Integer.parseInt(b);
            content += firstNum.toString();
        }
        else {
            if (!b.equals("")) {
                String temp = b.replace("+", "").
                        replace("-", "").
                        replace("*", "").replace("/", "");
                secondNum = Integer.parseInt(temp.replaceFirst(firstNum.toString(), ""));
            }
            content = firstNum.toString() + operation + secondNum.toString();
        }
        a.setText(content);
    }

    public void resetValue() {
        text.setText("0");
    }

    public Integer process(Integer a, Integer b) {
        Integer presult=0;
        if (operation == "+") {
            presult = a + b;
            text.setText(presult.toString());
        }
        if (operation == "-") {
            presult = a - b;
            text.setText(presult.toString());
        }
        if (operation == "*") {
            presult = a * b;
            text.setText(presult.toString());
        }
        if (operation == "/") {
            presult = a / b;
            text.setText(presult.toString());
        }
        return presult;
    }

}