package nesty.anzhy.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    TextView previousCalculation;
    EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);

        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userExp = display.getText().toString();

                userExp = userExp.replaceAll(getResources().getString(R.string.divideText), "/");
                userExp = userExp.replaceAll(getResources().getString(R.string.multiplyText), "*");
                Expression exp = new Expression(userExp);
                String res = String.valueOf(exp.calculate());

                double result = Double.parseDouble(res);
                if(result%1==0){
                    String newStr = String.valueOf((int)result);
                    display.setText(newStr);
                    display.setSelection(newStr.length());
                }
                else {
                    display.setText(res);
                    display.setSelection(res.length());
                }
            }
        });

        findViewById(R.id.btn0).setOnClickListener(view -> updateText(getResources().getString(R.string.zeroText)));
        findViewById(R.id.btn1).setOnClickListener(view -> updateText(getResources().getString(R.string.oneText)));
        findViewById(R.id.btn2).setOnClickListener(view -> updateText(getResources().getString(R.string.twoText)));
        findViewById(R.id.btn3).setOnClickListener(view -> updateText(getResources().getString(R.string.threeText)));
        findViewById(R.id.btn4).setOnClickListener(view -> updateText(getResources().getString(R.string.fourText)));
        findViewById(R.id.btn5).setOnClickListener(view -> updateText(getResources().getString(R.string.fiveText)));
        findViewById(R.id.btn6).setOnClickListener(view -> updateText(getResources().getString(R.string.sixText)));
        findViewById(R.id.btn7).setOnClickListener(view -> updateText(getResources().getString(R.string.sevenText)));
        findViewById(R.id.btn8).setOnClickListener(view -> updateText(getResources().getString(R.string.eightText)));
        findViewById(R.id.btn9).setOnClickListener(view -> updateText(getResources().getString(R.string.nineText)));

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText("");
            }
        });

        findViewById(R.id.btnSubstract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.substractText));
            }
        });

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.addText));

            }
        });

        findViewById(R.id.btnMult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.multiplyText));
            }
        });

        findViewById(R.id.btnDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.divideText));
            }
        });

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.decimalText));
            }
        });


        findViewById(R.id.btnParenthesesOpenText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.parenthesesOpenText));
            }
        });

        findViewById(R.id.btnParenthesesCloseText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(getResources().getString(R.string.parenthesesCloseText));
            }
        });

        findViewById(R.id.btnDeleteLast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPos = display.getSelectionStart();
                int textLen = display.getText().length();

                if (cursorPos != 0 && textLen != 0) {
                    SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
                    selection.replace(cursorPos - 1, cursorPos, "");
                    display.setText(selection);
                    display.setSelection(cursorPos - 1);
                }

            }
        });
    }


    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();

        int cursorPos = display.getSelectionStart();

        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }


}