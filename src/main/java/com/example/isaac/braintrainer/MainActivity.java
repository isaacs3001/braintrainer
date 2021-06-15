package com.example.isaac.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button retrybutton;
    TextView timertext;
    TextView questiontext;
    TextView tracktext;
    GridLayout answergrid;
    CountDownTimer countDownTimer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int correctlocation;
    TextView result;
    int score = 0;
    int numberofquestions=0;



    public void go(View view){
       button.setVisibility(View.INVISIBLE);
       timertext.setVisibility(View.VISIBLE);
       questiontext.setVisibility(View.VISIBLE);
        tracktext.setVisibility(View.VISIBLE);
        answergrid.setVisibility(View.VISIBLE);
        gameon();
        countDownTimer = new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timertext.setText(Integer.toString((int)millisUntilFinished/1000));


            }
            @Override
            public void onFinish() {
                retrybutton.setVisibility(View.VISIBLE);
                result.setText("DONE");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }
    public void retry(View view){
        result.setText("");
        retrybutton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        tracktext.setText("0/0");

        go(view);
    }

    public void gameon(){
        //result.setVisibility(View.INVISIBLE);
        Random random = new Random();
        int x = random.nextInt(50);
        int y = random.nextInt(50);
        questiontext.setText(Integer.toString(x)+"+"+Integer.toString(y));
        ArrayList<Integer> answers = new ArrayList<>();
            answers.clear();
         correctlocation = random.nextInt(4);

        for(int i=0;i<4;i++){
            if(i == correctlocation){
               answers.add(x+y);
            }
            else{
                int wronganswer = random.nextInt(50);
                while((x+y)==wronganswer){wronganswer = random.nextInt(50);}
                answers.add(random.nextInt(50));
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void answerclick(View view){

        result.setVisibility(View.VISIBLE);

        if(Integer.toString(correctlocation).equals(view.getTag().toString())) {
            score++;
            result.setText("CORRECT");
        }
        else{
            result.setText("WRONG ");
        }
        numberofquestions++;
        tracktext.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestions));
            gameon();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.gobutton);
        timertext = (TextView) findViewById(R.id.timertext);
        questiontext =(TextView) findViewById(R.id.questiontext);
        tracktext = (TextView) findViewById(R.id.tracktext);
        answergrid= (GridLayout) findViewById(R.id.answergrid);
        retrybutton = (Button) findViewById(R.id.retrybutton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.result);

    }
}
