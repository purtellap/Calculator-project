package com.austin.calculatorpurtell2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button div;
    Button mul;
    Button sub;
    Button add;
    Button period;
    Button plusminus;
    Button ce;
    Button c;
    Button equals;

    TextView input;
    TextView output;

    Button[] buttons;

    String inputText = "";
    String currentNum = "";

    float numFirst = 0;
    float numSecond = 0;
    int cmd = -1;
    float answer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
    }

    @Override
    public void onClick(View v) {

        int i = getIndex(v);
        // Log.i("Clicked", i + "");

        if(i > -1){
            Entry(i);
        }

        input.setText(inputText);
    }

    private void Entry(int i){

        // Text
        if(i == 11){ // +/-
            // panic

            //inputText += "-";
            if(currentNum.charAt(0) == '-'){
                currentNum = currentNum.substring(1);
            }
            else{
                currentNum = "-" + currentNum;
            }
        }
        else if(i < 16) { // Numbers and Calculations
            inputText += buttons[i].getText().toString();

            if(i > 11){ // Calculations
                cmd = i;
                currentNum = "";
            }
            else{ // Numbers
                currentNum +=  buttons[i].getText().toString();
            }
        }
        else if(i == 16){ // C
            if(inputText.length()>0){
            inputText = inputText.substring(0, inputText.length()-1);
            }
            if(currentNum.length()>0){
                currentNum = currentNum.substring(0, currentNum.length()-1);
            }
        }
        else if (i == 17){ // CE
            inputText = "";
            output.setText("");
            currentNum = "";
            answer = 0f;
            numFirst = 0f;
            numSecond = 0f;
            cmd = -1;
        }
        else if (i == 18){ // E
            calculate();
            currentNum = "";
        }

        if(!currentNum.equals("") && cmd != -1){ // current number active and on second num
            numSecond = Float.parseFloat(currentNum);
        }
        else if(!currentNum.equals("") && cmd == -1){ // current number active and on first num
            numFirst = Float.parseFloat(currentNum);
        }

        Log.i("current",currentNum);
        Log.i("first",String.valueOf(numFirst));
        Log.i("second",String.valueOf(numSecond));
        Log.i("answer",String.valueOf(answer));
    }

    private void calculate(){

        answer = numFirst;

        try {
            if (cmd == 12) {
                answer = numFirst / numSecond;
            } else if (cmd == 13) {
                answer = numFirst * numSecond;
            } else if (cmd == 14) {
                answer = numFirst - numSecond;
            } else if (cmd == 15) {
                answer = numFirst + numSecond;
            }
            output.setText(String.valueOf(answer));
        }
        catch (Exception e){
            output.setText(R.string.error);
        }
        numFirst = answer;
    }

    private int getIndex(View v){
        for(int i = 0; i < buttons.length; i++){
            if(v.getId() == buttons[i].getId()){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onSaveInstanceState(Bundle bum) {
        super.onSaveInstanceState(bum);
        bum.putString("inputString", input.getText().toString());
        bum.putString("outputString", output.getText().toString());
        bum.putString("inputText", inputText);
        bum.putFloat("answer", answer);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        input.setText(savedInstanceState.getString("inputString"));
        output.setText(savedInstanceState.getString("outputString"));
        inputText = savedInstanceState.getString("inputText");
        answer = savedInstanceState.getFloat("answer");
    }

    private void setUp(){

        num7 = (Button) findViewById(R.id.r0c0);
        num8 = (Button) findViewById(R.id.r0c1);
        num9 = (Button) findViewById(R.id.r0c2);
        div = (Button) findViewById(R.id.r0c3);
        num4 = (Button) findViewById(R.id.r1c0);
        num5 = (Button) findViewById(R.id.r1c1);
        num6 = (Button) findViewById(R.id.r1c2);
        mul = (Button) findViewById(R.id.r1c3);
        num1 = (Button) findViewById(R.id.r2c0);
        num2 = (Button) findViewById(R.id.r2c1);
        num3 = (Button) findViewById(R.id.r2c2);
        sub = (Button) findViewById(R.id.r2c3);
        period = (Button) findViewById(R.id.r3c0);
        num0 = (Button) findViewById(R.id.r3c1);
        plusminus = (Button) findViewById(R.id.r3c2);
        add = (Button) findViewById(R.id.r3c3);
        ce = (Button) findViewById(R.id.r4c0);
        c = (Button) findViewById(R.id.r4c1);
        equals = (Button) findViewById(R.id.r4c2);

        input = (TextView) findViewById(R.id.input);
        input.setMaxLines(3);
        output = (TextView) findViewById(R.id.output);
        output.setMaxLines(2);

        buttons = new Button[]{num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, period, plusminus, div, mul, sub, add, c, ce, equals};

        for(Button butt: buttons){
            butt.setOnClickListener(this);
        }

    }

}
