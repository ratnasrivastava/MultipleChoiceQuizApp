package com.example.a51006705.multiplechoicequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import com.example.a51006705.multiplechoicequizapp.QuizContract.*;

/**
 * Created by 51006705 on 20-06-2019.
 */

public class QuizDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //to store an instance
        this.db = db;

        final String CREATE_SQL_QUESTION_TABLE = "CREATE TABLE " + QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + "TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + "TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + "TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWERNR + "INTEGER" +
                ")";

        db.execSQL(CREATE_SQL_QUESTION_TABLE);

        fillQuestionsTable();
    }

    private void fillQuestionsTable() {

        Question q1 = new Question("What is A?", "A", "B", "C", 1);
        addQuestion(q1);

        Question q2 = new Question("What is B?", "A", "B", "C", 2);
        addQuestion(q2);

        Question q3 = new Question("What is C?", "A", "B", "C", 3);
        addQuestion(q3);

        Question q4 = new Question("What is Anu?", "Anu", "B", "C", 1);
        addQuestion(q4);

        Question q5 = new Question("What is Bat?", "A", "Bat", "C", 2);
        addQuestion(q5);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //If you changes in strurcture of table, you must change database number eg 1 to 2.
        // if changes occured drop table and again recreate it.
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void addQuestion(Question question) {

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWERNR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions(){
        List<Question> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
              Question question = new Question();
              question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));

                questionsList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return questionsList;
    }
}
