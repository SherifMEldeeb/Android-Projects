package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }


    public void displayForTeamA(int Score) {
        TextView ScoreView = (TextView) findViewById(R.id.scoreA);
        ScoreView.setText(String.valueOf(Score));
    }
    public void displayForTeamB(int Score) {
        TextView ScoreView = (TextView) findViewById(R.id.scoreB);
        ScoreView.setText(String.valueOf(Score));
    }


    public void ThreePoints(View a) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

    public void TwoPoints(View b) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    public void OnePoint(View c) {
        scoreTeamA++;
        displayForTeamA(scoreTeamA);
    }
    public void ThreePointsB(View x) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    public void TwoPointsB(View y) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    public void OnePointB(View z) {
        scoreTeamB++;
        displayForTeamB(scoreTeamB);
    }

    public void reset(View r) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
