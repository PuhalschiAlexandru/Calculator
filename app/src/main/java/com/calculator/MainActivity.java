package com.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, equal, clear;

    EditText disp;
    int op1;
    int op2;
    String optr;
    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;
    KeyListener mKeyListener;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the elements
        disp = (EditText) findViewById(R.id.display);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnDot = (Button) findViewById(R.id.btnDot);
        equal = (Button) findViewById(R.id.equal);
        clear = (Button) findViewById(R.id.clear);

        // set a listener
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        clear.setOnClickListener(this);
        equal.setOnClickListener(this);
        KeyListener mKeyListener = disp.getKeyListener();
    }


    public void operation() {
        disp.setKeyListener(mKeyListener);
        if (optr.equals("+")) {
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 + op2;
            disp.setText(Integer.toString(op1));
        } else if (optr.equals("-")) {
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 - op2;
            disp.setText(Integer.toString(op1));
        } else if (optr.equals("*")) {
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 * op2;
            disp.setText(Integer.toString(op1));
        } else if (optr.equals("/")) {
            op2 = Integer.parseInt(disp.getText().toString());
            if (op2 == 0) {
                op2 = 999;
            }
            disp.setText("");
            op1 = op1 / op2;
            disp.setText(Integer.toString(op1));
        }
    }

    @Override
    public void onClick(View arg0) {
        Editable str = disp.getText();
        switch (arg0.getId()) {
            case R.id.btn1:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn1.getText());
                disp.setText(str);
                break;
            case R.id.btn2:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn2.getText());
                disp.setText(str);
                break;
            case R.id.btn3:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn3.getText());
                disp.setText(str);
                break;
            case R.id.btn4:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn4.getText());
                disp.setText(str);
                break;
            case R.id.btn5:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn5.getText());
                disp.setText(str);
                break;
            case R.id.btn6:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn6.getText());
                disp.setText(str);
                break;
            case R.id.btn7:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn7.getText());
                disp.setText(str);
                break;
            case R.id.btn8:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn8.getText());
                disp.setText(str);
                break;
            case R.id.btn9:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn9.getText());
                disp.setText(str);
                break;
            case R.id.btn0:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(btn0.getText());
                disp.setText(str);
                break;
            case R.id.clear:
                op1 = 0;
                op2 = 0;
                disp.setText("");
                break;
            case R.id.btnAdd:
                optr = "+";
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                    op1 = op1 + op2;
                    disp.setText(Integer.toString(op1));
                }
                break;
            case R.id.btnSub:
                optr = "-";
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                    op1 = op1 - op2;
                    disp.setText(Integer.toString(op1));
                }
                break;
            case R.id.btnMult:
                optr = "*";
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                    op1 = op1 * op2;
                    disp.setText(Integer.toString(op1));
                }
                break;
            case R.id.btnDiv:
                optr = "/";
                op2 = 1;
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString().trim());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString().trim());
                    if (op2 == 0) {
                        disp.setText("");
                        disp.setText("Erroare); ");
                    }
                    disp.setText(""); /*op1 = op1 / op2;*/
                    disp.setText(Integer.toString(op1));
                }

                break;
            case R.id.equal:
                if (optr != null) {
                    disp.setKeyListener(null);
                    if (op2 != 0) {
                        if (optr.equals("+")) {
                            disp.setText("");
                            op1 = op1 + op2;
                            disp.setText(Integer.toString(op1));
                        } else if (optr.equals("-")) {
                            disp.setText(Integer.toString(op1));
                        } else if (optr.equals("*")) {
                            disp.setText("");/* op1 = op1 * op2;*/
                            disp.setText(Integer.toString(op1));
                        } else if (optr.equals("/") && op2 != 0) {
                            disp.setText("");/* op1 = op1 / op2;*/
                            disp.setText(Integer.toString(op1));
                        } else if (optr.equals("/") && op2 == 0) {
                            disp.setText("Eroare");
                        }
                    } else {
                        operation();
                    }
                }

                break;
        }
    }
}




