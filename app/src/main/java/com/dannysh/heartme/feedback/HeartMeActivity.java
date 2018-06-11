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
//        _testName.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                ((EditText)view).setText("");
//                return true;
//            }
//        });
        _testValue = findViewById(R.id.testValue);
//        _testValue.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                ((EditText)view).setText("");
//                return true;
//            }
//        });
        _imgView = findViewById(R.id.feedbackImg);
        _fbPresenter = new FeedbackPresenter(this);

        _submitBtn = findViewById(R.id.button);
        _submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _spinner.setVisibility(View.VISIBLE);
                _imgView.setVisibility(View.GONE);
                ImageView imgView = findViewById(R.id.feedbackImg);
                _fbPresenter.checkResults(_testName.getText().toString(), Double.parseDouble(_testValue.getText().toString()));

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        _spinner.setVisibility(View.GONE);
        _imgView.setVisibility(View.GONE);
    }

    @Override
    public void showFeedback(String formalTestName, String url) {
        //deactivate _spinner
        _spinner.setVisibility(View.GONE);
        //use picasso to show relevant image based on urls in Constants.
        Picasso.get().load(url).into(_imgView);
//        _imgView.setVisibility(View.VISIBLE);
        _testName.setText(formalTestName);
    }
}
