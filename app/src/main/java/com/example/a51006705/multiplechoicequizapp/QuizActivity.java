package com.example.a51006705.multiplechoicequizapp;

import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewScore;
    private TextView textViewQuestionNumber;
    private TextView textViewTimer;
    private TextView textViewQuestion;
    private RadioGroup radioGroup;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private Button buttonSubmitNext;
    //just to have the color of Radio button options
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewScore = findViewById(R.id.textview_score);
        textViewQuestionNumber = findViewById(R.id.textview_question_number);
        textViewTimer = findViewById(R.id.textview_timer);
        textViewQuestion = findViewById(R.id.textview_question);
        radioGroup = findViewById(R.id.radiogroup);
        option1 = findViewById(R.id.radiobutton_option1);
        option2 = findViewById(R.id.radiobutton_option2);
        option3 = findViewById(R.id.radiobutton_option3);
        buttonSubmitNext = findViewById(R.id.button_submit_next);

        textColorDefaultRb = option1.getTextColors();

        QuizDBHelper quizDBHelper = new QuizDBHelper(this);
        questionList = quizDBHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        //For shuffling questions
        Collections.shuffle(questionList);
        showNextQuestion();
    }

    private void showNextQuestion() {
        option1.setTextColor(textColorDefaultRb);
        option2.setTextColor(textColorDefaultRb);
        option3.setTextColor(textColorDefaultRb);


        if(questionCounter < questionCountTotal){
            
        }
    }
}
