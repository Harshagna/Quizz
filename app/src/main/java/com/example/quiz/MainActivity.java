package com.example.quiz;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import  android.graphics.Color;
import  android.os.Bundle;
import  android.view.View;
import  android.widget.Button;
import  android.widget.TextView;
public class MainActivity  extends AppCompatActivity implements View.OnClickListener{
    TextView quetionTextView;
    TextView totalQuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button btn_sumbit;
    int score=0;
    int totalQuestion= QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        totalQuestionTextView=findViewById(R.id.total_question);
        quetionTextView= findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        btn_sumbit=findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_sumbit.setOnClickListener(this);
        totalQuestionTextView.setText("Total Question:"+totalQuestion);
        loadNewQuestion();
    }
    private void loadNewQuestion(){
        if (currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
       quetionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        selectedAnswer="";
    }
private void  finishQuiz(){
       String passStatus;
       if (score >= totalQuestion*0.6){
           passStatus= "Passed";
       }else{
passStatus = "Failed";
    }
       new AlertDialog.Builder(this)
               .setTitle(passStatus)
               .setMessage("Your Score is "+score+" Out of"+totalQuestion)
               .setPositiveButton("Restart",((dilog, i)-> restartQuiz()))
               .setCancelable(false)
               .show();

    }


private  void  restartQuiz(){
        score = 0;
        currentQuestionIndex=0;
        loadNewQuestion();
}
public   void onClick(View view){
        ansA.setBackgroundColor(Color.WHITE);
    ansB.setBackgroundColor(Color.WHITE);
    ansC.setBackgroundColor(Color.WHITE);
    ansD.setBackgroundColor(Color.WHITE);


    Button clickedButton = (Button) view;
    if(clickedButton.getId()==R.id.btn_submit){
        if(!selectedAnswer.isEmpty()){
            if(selectedAnswer.equals(QuestionAnswer.CorrectAnswer[currentQuestionIndex])){
                score++;
            }else{
                clickedButton.setBackgroundColor(Color.MAGENTA);
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }else {

        }
    }else{
        selectedAnswer=clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.YELLOW);
    }
}
}
