package com.dannysh.heartme.feedback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dannysh.heartme.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HeartMeActivity extends AppCompatActivity implements FeedbackView {

    EditText _testName, _testValue;
    Button _submitBtn;
    FeedbackPresenter _fbPresenter;
    private ProgressBar _spinner;
    private ImageView _imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _spinner = findViewById(R.id.progressBar1);

        _testName = findViewById(R.id.testName);
        _testValue = findViewById(R.id.testValue);
        _imgView = findViewById(R.id.feedbackImg);
        _fbPresenter = new FeedbackPresenter(this);

        _submitBtn = findViewById(R.id.button);
        _submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _spinner.setVisibility(View.VISIBLE);
                _fbPresenter.checkResults(_testName.getText().toString(), Double.parseDouble(_testValue.getText().toString()));
//                _imgView.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        _spinner.setVisibility(View.INVISIBLE);
//        _imgView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showFeedback(String formalTestName, String url) {

        //use picasso to show relevant image based on urls in Constants.
        _imgView.setVisibility(View.VISIBLE);
        Picasso.get().load(url).into(_imgView, new Callback() {
            @Override
            public void onSuccess() {
                _spinner.setVisibility(View.INVISIBLE);
                _testName.setText(formalTestName);
            }

            @Override
            public void onError(Exception e) {

            }
        });


    }
}
