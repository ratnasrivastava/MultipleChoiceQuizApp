package com.example.a51006705.multiplechoicequizapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
Log.d("ratna","QizActivity1");
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
        Log.d("ratna","QizActivity2");
        //For shuffling questions
        Collections.shuffle(questionList);
        showNextQuestion();

        buttonSubmitNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (option1.isChecked() || option2.isChecked() || option3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }

            }
        });
    }

    private void showNextQuestion() {
        Log.d("ratna","QizActivity3");
        option1.setTextColor(textColorDefaultRb);
        option2.setTextColor(textColorDefaultRb);
        option3.setTextColor(textColorDefaultRb);
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            questionCounter++;
            textViewQuestionNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonSubmitNext.setText("Confirm");
        } else {
            finishQuiz();
        }
        Log.d("ratna","QizActivity4");
    }

    private void checkAnswer() {
        Log.d("ratna","QizActivity5");
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbSelected)+1;
        Log.d("ratna","answerNr ="+answerNr);
        Log.d("ratna", "getAnsNR="+currentQuestion.getAnswerNr());
        if(answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
            Log.d("ratna","QizActivity6");
        }
        showSolution();

    }

    private void showSolution() {
        Log.d("ratna","QizActivity7");
        option1.setTextColor(Color.RED);
        option2.setTextColor(Color.RED);
        option3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                option1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                option2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                option3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }
        if(questionCounter < questionCountTotal){
            buttonSubmitNext.setText("Next");
        }
        else{
            buttonSubmitNext.setText("Finish");
        }
        Log.d("ratna","QizActivity8");
    }

    private void finishQuiz() {
        Log.d("ratna","QizActivity9");
       finish();

    }
}
