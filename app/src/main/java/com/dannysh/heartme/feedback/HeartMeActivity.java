package com.dannysh.heartme.feedback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dannysh.heartme.R;
import com.dannysh.heartme.utils.Constants;
import com.dannysh.heartme.utils.NetworkUtils;

public class HeartMeActivity extends Activity implements FeedbackView {

    EditText testName , testValue;
    Button submitBtn;
    FeedbackPresenter fbPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load data
        NetworkUtils.getBloodTestConfig();

        testName  = findViewById(R.id.testName);
        testValue = findViewById(R.id.testValue);

        fbPresenter = new FeedbackPresenter(this,this);

        submitBtn = findViewById(R.id.button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validInput = fbPresenter.validateTestName(testName.getText().toString());
                if(validInput){
                    //activate spinner
                    fbPresenter.getResult(testName.getText().toString(),Double.parseDouble(testValue.getText().toString()));
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void showFeedback(Constants.TetsFeedBackStatus status) {
        //deactivate spinner

        //use picasso to show relevant image based on urls in Constants.
    }
}
