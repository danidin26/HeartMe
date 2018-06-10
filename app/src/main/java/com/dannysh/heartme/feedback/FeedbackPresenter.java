package com.dannysh.heartme.feedback;

import android.content.Context;
import com.dannysh.heartme.utils.Constants;

public class FeedbackPresenter {

    private FeedbackView view;
    private Context context;


    public FeedbackPresenter(FeedbackView view, Context context) {
        this.view = view;
        this.context = context;
    }


    public boolean validateTestName(String userTestName) {
        //check for empty string and regex for valid chars a-Z
        return true;
    }

    public void getResult(String testName , double testvalue){
        //check for test name
        //all the logic required to support typos and reorder of test name and such.....

        //activate the fun in activity to show relevant feedback to user
        view.showFeedback(Constants.TetsFeedBackStatus.BAD);
    }
}
