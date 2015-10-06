package com.example.student.geoquiz;

        import android.os.Build;
        import android.os.Bundle;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Button;

        import com.example.student.geoquiz.R;

public class CheatActivity extends AppCompatActivity
{
    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.student.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.student.geoquiz.answer_shown";
    public static final String KEY_CHEAT = "cheat";
    private boolean mAnswerIsTrue;
    private boolean mIsAnswerShown = false;
    private TextView mAnswerTextView;


    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(mIsAnswerShown);//Answer will not be shown until the user presses the button
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        Button showAnswer = (Button) findViewById(R.id.showAnswerButton);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                mIsAnswerShown = true;
                setAnswerShownResult(true);
            }
        });

        TextView apiLevel = (TextView) findViewById(R.id.api_lvl);
        apiLevel.setText("API Level " + Build.VERSION.SDK_INT);
    }//end onCreate()

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {

        savedInstanceState.putBoolean(KEY_CHEAT, mIsAnswerShown);
        super.onSaveInstanceState(savedInstanceState);
    }
}