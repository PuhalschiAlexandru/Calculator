package com.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {

    private static Pattern INVALID_INPUT_PATTERN = Pattern.compile("(.*\\..*\\.)|(^0\\d) ");
    private static Pattern INVALID_OPERATION = Pattern.compile("");
    private static final String TAG = "MainActivity";
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, equal, clear;

    TextView disp;
    float acc;
    float input1;
    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button mBtnDiv;
    Operation mLastOperation;
    boolean mInitialized;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDigitbuttons();
        setUpOperationbuttons();

        disp = (TextView) findViewById(R.id.display);

        mInitialized = false;
    }

    private void setUpOperationbuttons() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        mBtnDiv = (Button) findViewById(R.id.btnDiv);
        clear = (Button) findViewById(R.id.clear);
        equal = (Button) findViewById(R.id.equal);

        View.OnClickListener operationButtonsOneClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operationIsValid(disp.getText().toString())) {
                    input1 = Float.parseFloat(disp.getText().toString());
                    disp.setText("");
                    switch (view.getId()) {
                        case R.id.clear:
                            acc = 0;
                            input1 = 0;
                            mInitialized = false;
                            disp.setText("");
                            break;
                        case R.id.btnAdd:
                            performCalculus(new AddOperation(), input1);
                            disp.setText("");
//
                            break;
                        case R.id.btnSub:
                            performCalculus(new SubOperation(), input1);
                            disp.setText("");
                            break;
                        case R.id.btnMult:
                            performCalculus(new MultOperation(), input1);
                            disp.setText("");
                            break;
                        case R.id.btnDiv:
                            performCalculus(new DivOperation(), input1);
                            disp.setText("");
                            break;
                        case R.id.equal:
                            performCalculus(new Equal(), input1);


                            break;
                        default:
                            disp.append(((Button) view).getText());
                    }
                }
            }

        };

        btnAdd.setOnClickListener(operationButtonsOneClickListener);
        btnSub.setOnClickListener(operationButtonsOneClickListener);
        btnMult.setOnClickListener(operationButtonsOneClickListener);
        mBtnDiv.setOnClickListener(operationButtonsOneClickListener);
        clear.setOnClickListener(operationButtonsOneClickListener);
        equal.setOnClickListener(operationButtonsOneClickListener);
    }

    private void setUpDigitbuttons() {
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


        View.OnClickListener digiButtonsClickLisetener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = disp.getText().toString() + ((Button) view).getText();
                if (valueIsValid(newValue)) {
                    disp.setText(newValue);
                }
            }
        };


        btn1.setOnClickListener(digiButtonsClickLisetener);
        btn2.setOnClickListener(digiButtonsClickLisetener);
        btn3.setOnClickListener(digiButtonsClickLisetener);
        btn4.setOnClickListener(digiButtonsClickLisetener);
        btn5.setOnClickListener(digiButtonsClickLisetener);
        btn6.setOnClickListener(digiButtonsClickLisetener);
        btn7.setOnClickListener(digiButtonsClickLisetener);
        btn8.setOnClickListener(digiButtonsClickLisetener);
        btn9.setOnClickListener(digiButtonsClickLisetener);
        btn0.setOnClickListener(digiButtonsClickLisetener);
        btnDot.setOnClickListener(digiButtonsClickLisetener);

    }

    private boolean valueIsValid(String text) {
        Matcher matcher = INVALID_INPUT_PATTERN.matcher(text);
        if (matcher.matches()) {
            Log.d(TAG, "detected invalid pattern");
            return false;
        }
        return true;
    }

    private boolean operationIsValid(String text) {
        Matcher matcher = INVALID_OPERATION.matcher(text);
        if (matcher.matches()) {
            Log.d(TAG, "detected invalid pattern");
            return false;
        }
        return true;
    }


    private void performCalculus(Operation newOperation, float input) {
        if (!mInitialized) {
            acc = input;
            mInitialized = true;
        } else {
            acc = mLastOperation.performOperation(acc, input);
            disp.setText(Float.toString(acc).trim());

        }
        mLastOperation = newOperation;
    }

    public interface Operation {
        float performOperation(float input1, float input2);
    }

    class AddOperation implements Operation {
        @Override
        public float performOperation(float input1, float input2) {
            return input1 + input2;
        }
    }

    class SubOperation implements Operation {
        @Override
        public float performOperation(float input1, float input2) {
            return input1 - input2;
        }
    }

    class MultOperation implements Operation {
        @Override
        public float performOperation(float input1, float input2) {
            return input1 * input2;
        }
    }

    class DivOperation implements Operation {
        @Override
        public float performOperation(float input1, float input2) {
            return input1 / input2;
        }
    }

    class Equal implements Operation {
        @Override
        public float performOperation(float input1, float input2) {
            return acc;
        }
    }
}




