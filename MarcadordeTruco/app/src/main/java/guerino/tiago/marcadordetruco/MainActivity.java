package guerino.tiago.marcadordetruco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTeamATextView, scoreTeamBTextView;
    private int scoreTeamA, scoreTeamB;
    private Button addOneAButton, addThreeAButton, addSixAButton, addNineAButton, addTwelveAButton, backTeamA;
    private Button addOneBButton, addThreeBButton, addSixBButton, addNineBButton, addTwelveBButton, backTeamB;
    private int scoreMinusA, sooreMinusB;
    //region [Stack]
    private Stack stackA = new Stack();
    private Stack stackB = new Stack();
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scoreTeamA = 0;
        scoreTeamB = 0;

        //region [Set ID]
        scoreTeamATextView = findViewById(R.id.ScoreTeamA);
        scoreTeamBTextView = findViewById(R.id.ScoreTeamB);
        addOneAButton = findViewById(R.id.Add1ButtonA);
        addThreeAButton = findViewById(R.id.Add3ButtonA);
        addSixAButton = findViewById(R.id.Add6ButtonA);
        addNineAButton = findViewById(R.id.Add9ButtonA);
        addTwelveAButton = findViewById(R.id.Add12ButtonA);
        addOneBButton = findViewById(R.id.Add1ButtonB);
        addThreeBButton = findViewById(R.id.Add3ButtonB);
        addSixBButton = findViewById(R.id.Add6ButtonB);
        addNineBButton = findViewById(R.id.Add9ButtonB);
        addTwelveBButton = findViewById(R.id.Add12ButtonB);
        backTeamA = findViewById(R.id.UndoA);
        backTeamB = findViewById(R.id.UndoB);
        //endregion
    }

    //region [Add Score]
    public void addOneTeamA(View view) {
        addScore("A", 1);
    }

    public void addOneTeamB(View view) {
        addScore("B", 1);
    }

    public void addThreeTeamA(View view) {
        addScore("A", 3);
    }

    public void addThreeTeamB(View view) {
        addScore("B", 3);
    }

    public void addSixTeamA(View view) {
        addScore("A", 6);
    }

    public void addSixTeamB(View view) {
        addScore("B", 6);
    }

    public void addNineTeamA(View view) {
        addScore("A", 9);
    }

    public void addNineTeamB(View view) {
        addScore("B", 9);
    }

    public void addTwelveTeamA(View view) {
        addScore("A", 12);
    }

    public void addTwelveTeamB(View view) {
        addScore("B", 12);
    }

    public void addScore(String team, int score) {
        //region [Add A]
        if (team.equals("A")) {
            stackA.push(score);
            scoreTeamA += score;
            if (scoreTeamA == 11) {
                elevenA();
            } else if (scoreTeamA > 11) {
                scoreTeamA = 12;
                endGame();
            }
            scoreTeamATextView.setText(String.valueOf(scoreTeamA));
        }
        //endregion

        //region [Add B]
        else {

            stackB.push(score);
            scoreTeamB += score;
            if (scoreTeamB == 11) {
                elevenB();
            } else if (scoreTeamB > 11) {
                scoreTeamB = 12;
                endGame();
            }
            scoreTeamBTextView.setText(String.valueOf(scoreTeamB));
        }
        //endregion

    }
    //endregion

    //region [Reset Score]
    public void resetPoints(View view) {
        //set score to 0
        scoreTeamA = 0;
        scoreTeamB = 0;
        //set text score to 0
        scoreTeamATextView.setText(String.valueOf(scoreTeamB));
        scoreTeamBTextView.setText(String.valueOf(scoreTeamA));

        //enableTeam A
        addOneAButton.setEnabled(true);
        addThreeAButton.setEnabled(true);
        addSixAButton.setEnabled(true);
        addNineAButton.setEnabled(true);
        addTwelveAButton.setEnabled(true);
        backTeamA.setEnabled(true);

        //enable Team B
        addOneBButton.setEnabled(true);
        addThreeBButton.setEnabled(true);
        addSixBButton.setEnabled(true);
        addNineBButton.setEnabled(true);
        addTwelveBButton.setEnabled(true);
        backTeamB.setEnabled(true);

        //reset Stacks
        while(!stackA.empty()){
            stackA.pop();
        }

        while(!stackB.empty()){
            stackB.pop();
        }
    }
    //endregion

    //region [End Game]
    public void endGame() {
        //Lock Team A
        addOneAButton.setEnabled(false);
        addThreeAButton.setEnabled(false);
        addSixAButton.setEnabled(false);
        addNineAButton.setEnabled(false);
        addTwelveAButton.setEnabled(false);
        backTeamA.setEnabled(false);

        //Lock Team B
        addOneBButton.setEnabled(false);
        addThreeBButton.setEnabled(false);
        addSixBButton.setEnabled(false);
        addNineBButton.setEnabled(false);
        addTwelveBButton.setEnabled(false);
        backTeamB.setEnabled(false);
    }
    //endregion

    //region [Eleven A]
    public void elevenA() {
        addThreeAButton.setEnabled(false);
        addSixAButton.setEnabled(false);
        addNineAButton.setEnabled(false);
        addTwelveAButton.setEnabled(false);
    }
    //endregion

    //region [Eleven B]
    public void elevenB() {
        addThreeBButton.setEnabled(false);
        addSixBButton.setEnabled(false);
        addNineBButton.setEnabled(false);
        addTwelveBButton.setEnabled(false);
    }
    //endregion

    public void minusScoreA() {
        if (!stackA.empty()) {
            backTeamA.setEnabled(true);
            scoreMinusA = Integer.parseInt(String.valueOf(stackA.lastElement()));
            scoreTeamA = scoreTeamA - scoreMinusA;
            stackA.pop();
            scoreTeamATextView.setText(String.valueOf(scoreTeamA));
        }
    }

    public void minusScoreB() {
        if (!stackB.empty()) {
            backTeamB.setEnabled(true);
            sooreMinusB = Integer.parseInt(String.valueOf(stackB.lastElement()));
            scoreTeamB = scoreTeamB - sooreMinusB;
            stackB.pop();
            scoreTeamBTextView.setText(String.valueOf(scoreTeamB));
        }
    }

    public void backScoreA(View view){
        minusScoreA();
    }
    public void backScoreB(View view){
        minusScoreB();
    }
}
