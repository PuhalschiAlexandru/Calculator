package com.calculator.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calculator.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {

    private static Pattern INVALID_INPUT_PATTERN = Pattern.compile("(.*\\..*\\.)|(^0\\d) ");
    private static Pattern INVALID_OPERATION = Pattern.compile("");
    private static final String TAG = "MainActivity";

    private TextView mDisp;
    float mAcc;
    Operation mLastOperation;
    boolean mInitialized;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSetUpDigitbuttons();
        mSetUpOperationbuttons();

        mDisp = (TextView) findViewById(R.id.mDisplay);

        mInitialized = false;
    }

    private void mSetUpOperationbuttons() {
        Button btnAdd = (Button) findViewById(R.id.mBtnAdd);
        Button btnSub = (Button) findViewById(R.id.mBtnSub);
        Button btnMult = (Button) findViewById(R.id.mBtnMult);
        Button btnDiv = (Button) findViewById(R.id.mBtnDiv);
        Button btnClear = (Button) findViewById(R.id.mBtnClear);
        Button btnEqual = (Button) findViewById(R.id.mBtnEqual);

        View.OnClickListener operationButtonsOneClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOperationIsValid(mDisp.getText().toString())) {
                    float input1 = Float.parseFloat(mDisp.getText().toString());
                    mDisp.setText("");
                    switch (view.getId()) {
                        case R.id.mBtnClear:
                            mAcc = 0;
                            mInitialized = false;
                            mDisp.setText("");
                            break;
                        case R.id.mBtnAdd:
                            mPerformCalculus(new AddOperation(), input1);
                            mDisp.setText("");
//
                            break;
                        case R.id.mBtnSub:
                            mPerformCalculus(new SubOperation(), input1);
                            mDisp.setText("");
                            break;
                        case R.id.mBtnMult:
                            mPerformCalculus(new MultOperation(), input1);
                            mDisp.setText("");
                            break;
                        case R.id.mBtnDiv:
                            mPerformCalculus(new DivOperation(), input1);
                            mDisp.setText("");
                            break;
                        case R.id.mBtnEqual:
                            mPerformCalculus(new Equal(), input1);


                            break;
                        default:
                            mDisp.append(((Button) view).getText());
                    }
                }
            }

        };

        btnAdd.setOnClickListener(operationButtonsOneClickListener);
        btnSub.setOnClickListener(operationButtonsOneClickListener);
        btnMult.setOnClickListener(operationButtonsOneClickListener);
        btnDiv.setOnClickListener(operationButtonsOneClickListener);
        btnClear.setOnClickListener(operationButtonsOneClickListener);
        btnEqual.setOnClickListener(operationButtonsOneClickListener);
    }

    private void mSetUpDigitbuttons() {
        Button btn1 = (Button) findViewById(R.id.mBtn1);
        Button btn2 = (Button) findViewById(R.id.mBtn2);
        Button btn3 = (Button) findViewById(R.id.mBtn3);
        Button btn4 = (Button) findViewById(R.id.mBtn4);
        Button btn5 = (Button) findViewById(R.id.mBtn5);
        Button btn6 = (Button) findViewById(R.id.mBtn6);
        Button btn7 = (Button) findViewById(R.id.mBtn7);
        Button btn8 = (Button) findViewById(R.id.mBtn8);
        Button btn9 = (Button) findViewById(R.id.mBtn9);
        Button btn0 = (Button) findViewById(R.id.mBtn0);
        Button btnDot = (Button) findViewById(R.id.mBtnDot);

        View.OnClickListener digiButtonsClickLisetener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = mDisp.getText().toString() + ((Button) view).getText();
                if(newValue.equals(".")){
                    mDisp.setText("0.");
                }
                else if (mValueIsValid(newValue)) {
                    mDisp.setText(newValue);
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

    private boolean mValueIsValid(String text) {
        Matcher matcher = INVALID_INPUT_PATTERN.matcher(text);
        if (matcher.matches()) {
            Log.d(TAG, "detected invalid pattern");
            return false;
        }
        return true;
    }

    private boolean mOperationIsValid(String text) {
        Matcher matcher = INVALID_OPERATION.matcher(text);
        if (matcher.matches()) {
            Log.d(TAG, "detected invalid pattern");
            return false;
        }
        return true;
    }


    private void mPerformCalculus(Operation newOperation, float input) {
        if (!mInitialized) {
            mAcc = input;
            mInitialized = true;
        } else {
            mAcc = mLastOperation.performOperation(mAcc, input);
            mDisp.setText(Float.toString(mAcc).trim());

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
            return mAcc;
        }
    }
}




