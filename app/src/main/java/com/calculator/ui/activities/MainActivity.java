package com.calculator.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calculator.R;
import com.calculator.math.AddOperation;
import com.calculator.math.DivOperation;
import com.calculator.math.MultOperation;
import com.calculator.math.Operation;
import com.calculator.math.SubOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {


    private static final String ANY_OPERATION_PATTERN = "[\\+\\-\\*/]";
    private static Pattern INVALID_INPUT_PATTERN = Pattern.compile("(.*\\..*\\.)|(^0\\d)");
    private static Pattern INVALID_OPERATION_PATTERN = Pattern.compile(
            "(.*" + ANY_OPERATION_PATTERN + ANY_OPERATION_PATTERN + ".*)"
                    + "|(^" + ANY_OPERATION_PATTERN + ")" +
                    "|(.*\\." + ANY_OPERATION_PATTERN + ")" + "|(.*" + ANY_OPERATION_PATTERN + "\\.)");


    private static final String SAVED_DISP = "SAVED_STATE";
    private static final String SAVED_ACC = "SAVED_ACC";
    private static final String SAVED_INIT = "SAVED_INIT";
    private static final String OPERATION_BUNDLE_KEY = "OPERATION";
    private static final int OPERATION_NONE = 0;
    private static final int OPERATION_ADD = 1;
    private static final int OPERATION_SUB = 2;
    private static final int OPERATION_MUL = 3;
    private static final int OPERATION_DIV = 4;

    private static final String TAG = "MainActivity";
    String input1;
    private TextView mDisp;
    float mAcc;
    Operation mLastOperation;
    boolean mInitialized;
    private ArrayList<Operation> mOperations;
    private ArrayList<Float> mOperators;

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
        Button btnClear = (Button) findViewById(R.id.mBtnClear);
        Button btnEqual = (Button) findViewById(R.id.mBtnEqual);

        View.OnClickListener operationButtonsOneClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true) {
                    input1 = mDisp.getText().toString();
                    switch (view.getId()) {
                        case R.id.mBtnClear:
                            mAcc = 0;
                            mInitialized = false;
                            mDisp.setText("");
                            break;
                        case R.id.mBtnEqual:
                            parseInput(input1);
                            float result = evaluateOperations(mOperators, mOperations);
                            mDisp.setText(result + "");
                            break;
                        default:
                            mDisp.append(((Button) view).getText());
                    }
                }
            }

        };

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
        Button btnAdd = (Button) findViewById(R.id.mBtnAdd);
        Button btnSub = (Button) findViewById(R.id.mBtnSub);
        Button btnMult = (Button) findViewById(R.id.mBtnMult);
        Button btnDiv = (Button) findViewById(R.id.mBtnDiv);


        View.OnClickListener digiButtonsClickLisetener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = mDisp.getText().toString() + ((Button) view).getText();
                if (newValue.equals(".")) {
                    mDisp.setText("0.");
                } else if (mValueIsValid(newValue)) {
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
        btnAdd.setOnClickListener(digiButtonsClickLisetener);
        btnSub.setOnClickListener(digiButtonsClickLisetener);
        btnMult.setOnClickListener(digiButtonsClickLisetener);
        btnDiv.setOnClickListener(digiButtonsClickLisetener);

    }

    private boolean mValueIsValid(String text) {
        Matcher matcher = INVALID_OPERATION_PATTERN.matcher(text);
        if (matcher.matches()) {
            Log.d(TAG, "detected invalid pattern");
            return false;
        }
        String[] operatorsStrings = text.split(ANY_OPERATION_PATTERN);
        for (String part : operatorsStrings) {
            matcher = INVALID_INPUT_PATTERN.matcher(part);
            if (part.isEmpty() || matcher.matches()) {
                Log.d(TAG, "detected invalid pattern");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String stateToSave = mDisp.getText().toString();
        Boolean initToSave = mInitialized;
        Float accToSave = mAcc;
        outState.putString(SAVED_DISP, stateToSave);
        outState.putFloat(SAVED_ACC, accToSave);
        outState.putBoolean(SAVED_INIT, initToSave);

        if (mLastOperation == null) {
            outState.putInt(OPERATION_BUNDLE_KEY, OPERATION_NONE);
        } else if (mLastOperation instanceof AddOperation) {
            outState.putInt(OPERATION_BUNDLE_KEY, OPERATION_ADD);
        } else if (mLastOperation instanceof SubOperation) {
            outState.putInt(OPERATION_BUNDLE_KEY, OPERATION_SUB);
        } else if (mLastOperation instanceof MultOperation) {
            outState.putInt(OPERATION_BUNDLE_KEY, OPERATION_MUL);
        } else if (mLastOperation instanceof DivOperation) {
            outState.putInt(OPERATION_BUNDLE_KEY, OPERATION_DIV);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedState = savedInstanceState.getString(SAVED_DISP);
        Float savedAcc = savedInstanceState.getFloat(SAVED_ACC);
        Boolean savedInit = savedInstanceState.getBoolean(SAVED_INIT);
        mAcc = savedAcc;
        mDisp.setText(savedState);
        mInitialized = savedInit;
        int operation = savedInstanceState.getInt(OPERATION_BUNDLE_KEY);
        switch (operation) {
            case OPERATION_NONE:
                mLastOperation = null;
                break;
            case OPERATION_ADD:
                mLastOperation = new AddOperation();
                break;
            case OPERATION_SUB:
                mLastOperation = new SubOperation();
                break;
            case OPERATION_MUL:
                mLastOperation = new MultOperation();
                break;
            case OPERATION_DIV:
                mLastOperation = new DivOperation();
        }
    }

    private void parseInput(String input) {
        int mLastSign = -1;
        int mStartPos = 0;
        int mCurPos;
        char[] charArray = input.toCharArray();
        int n = charArray.length;
        mOperators = new ArrayList<>();
        mOperations = new ArrayList<>();
        List<Character> signs = new ArrayList<>();
        signs.add('+');
        signs.add('-');
        signs.add('*');
        signs.add('/');
        for (mCurPos = 0; mCurPos < n; mCurPos++) {
            if (signs.contains(charArray[mCurPos])) {
                mStartPos = mLastSign + 1;
                mLastSign = mCurPos;
                String substring = input.substring(mStartPos, mCurPos);
                Float value = Float.parseFloat(substring);
                mOperators.add(value);
                Operation operation = null;
                switch (charArray[mCurPos]) {
                    case '+':
                        operation = new AddOperation();
                        break;
                    case '-':
                        operation = new SubOperation();
                        break;
                    case '*':
                        operation = new MultOperation();
                        break;
                    case '/':
                        operation = new DivOperation();
                        break;
                }
                mOperations.add(operation);
            }
        }
        mStartPos = mLastSign + 1;
        String substring = input.substring(mStartPos, mCurPos);
        Float value = Float.parseFloat(substring);
        mOperators.add(value);
    }

    private float evaluateOperations(List<Float> operators, List<Operation> operations) {
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (operation instanceof MultOperation || operation instanceof DivOperation) {
                float result = operation.performOperation(operators.get(i), operators.get(i + 1));
                operators.remove(i);
                operators.remove(i);
                operators.add(i, result);
                operations.remove(i);
                i--;
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            float result = operation.performOperation(operators.get(i), operators.get(i + 1));
            operators.remove(i);
            operators.remove(i);
            operators.add(i, result);
            operations.remove(i);
            i--;
        }
        return operators.get(0);
    }
}





