package com.example.a51006705.multiplechoicequizapp;

import android.provider.BaseColumns;

/**
 * Created by 51006705 on 20-06-2019.
 */

public final class QuizContract {

    //so that no one can initialize object

    private QuizContract(){}

    //Inner class for every table
    public static class QuestionsTable implements BaseColumns{

        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWERNR = "answer_nr";

    }
}
