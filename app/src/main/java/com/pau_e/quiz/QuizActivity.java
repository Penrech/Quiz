package com.pau_e.quiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private final int[] ids_botons = {
            R.id.answer1, R.id.answer2 , R.id.answer3, R.id.answer4
    };

    private RadioGroup rgroup;

    //Dades sobre les preguntes
    private String[] questions;
    private String[][] answers;
    private int[] solutions;

    private int curr; //current question index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        curr = 0;
        loadQuestions();
        showQuestion();

        Button btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer ();
            }
        });

        rgroup = findViewById(R.id.answers);
    }

    private void loadQuestions() {
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        solutions = res.getIntArray(R.array.solutions);
        String[] answ = res.getStringArray(R.array.answer_texts);
        answers = new String[answ.length][];
        for(int i = 0; i < answ.length; i++){
            answers[i] = answ[i].split(";");
        }
    }

    private void showQuestion() {
        TextView question_text = findViewById(R.id.question_text);
        question_text.setText(questions[0]);

        for (int i = 0; i < answers[0].length; i++){
            RadioButton rb = findViewById(ids_botons[i]);
            rb.setText(answers[curr][i]);
        }
    }

    private void checkAnswer() {
        int id_check = rgroup.getCheckedRadioButtonId();
        int quin = -1;
        for (int i=0; i < ids_botons.length;i++){
            if (id_check == ids_botons[i]){
                quin = i+1;
            }
        }
        if (quin != -1){
            if (quin == solutions[curr]){
                Toast.makeText(this, "Correcte!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Incorrecte...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
