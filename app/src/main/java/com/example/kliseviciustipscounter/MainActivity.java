package com.example.kliseviciustipscounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private TextView percent;
    private TextView tipCount;
    private TextView resultSum;
    private TextView printResult;
    private SeekBar seek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.inputNum);
        percent = findViewById(R.id.percent);
        tipCount = findViewById(R.id.tipCount);
        resultSum = findViewById(R.id.resultSum);
        printResult = findViewById(R.id.printResult);
        seek = findViewById(R.id.seek);
        input.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});



        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percent.setText("" + progress + "%");
                try {
                    double num = Double.parseDouble(input.getText().toString());
                    double value = seekBar.getProgress();
                    double ats = percentTip(num, value);
                    tipCount.setText(String.format("\u20ac%.2f", ats));
                    double results = resultSum(num, ats);
                    resultSum.setText(String.format("\u20ac%.2f", results));
                    printResult.setText(String.format("Input Sum: \u20ac%.2f \nTips: \u20ac%.2f \nTotal: \u20ac%.2f", num,ats,results));
                }catch (NumberFormatException ignored){ }
            }

            

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    double num = Double.parseDouble(input.getText().toString());
                    double value = seekBar.getProgress();
                    double ats = percentTip(num, value);
                    tipCount.setText(String.format("\u20ac%.2f", ats));
                    double results = resultSum(num, ats);
                    resultSum.setText(String.format("\u20ac%.2f", results));
                    printResult.setText(String.format("Input Sum: \u20ac%.2f \nTips: \u20ac%.2f \nTotal: \u20ac%.2f", num,ats,results));
                }catch (NumberFormatException ignored){ }
                }
        }
        );



    }
    public double percentTip(double num,double percent) {
        return ((percent / 100) * num);
    }
    public double resultSum(double num,double num1) {
        return num + num1;
    }
    public class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
            mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }



    }
}








