package com.dannysh.heartme.feedback;

import com.dannysh.heartme.utils.Constants;
import com.dannysh.heartme.utils.Damerau;
import com.dannysh.heartme.utils.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FeedbackPresenter {

    private FeedbackView _view;
    Repository _repo;
    private final double MAX_VALUE = 100;


    public FeedbackPresenter(FeedbackView view) {
        this._view = view;
        _repo = new Repository();
        _repo.loadData();
    }

    /**
     * remove all non char or digits from string . replace by " "
     * lowercase the string
     * @param userTestName
     * @return
     */
    private String prepareForComparison(String userTestName) {
        String result="";
        if(!userTestName.isEmpty()){
            result = userTestName.toLowerCase();
        }
        //remove all non alphanumeric chars and trim spaces
        result =  result.replaceAll("[^a-zA-Z0-9]", " ").replace(" +" ,"").trim();
        return result;
    }

    public void checkResults(String testName , double testvalue){

        double minDistance=MAX_VALUE;
        String targetTestName="";

        Set<String> repoTestNames = _repo.getTestNames();
        if(repoTestNames.isEmpty()){
            _view.showFeedback("" , Constants.ERROR_IMG_URL);
            return;
        }

        String standardizedUserInput = prepareForComparison(testName);
        for (String tName: repoTestNames) {
            double tempDistance = calcScore(standardizedUserInput, tName.toLowerCase());
            if(tempDistance<minDistance){
                minDistance=tempDistance;
                targetTestName = tName;
            }
        }
        //input doesnt exists in _repo
        if(minDistance==MAX_VALUE){
            _view.showFeedback("" , Constants.UNKNOWN_IMG_URL);
            return;
        }
        if(testvalue > _repo.getThreshold(targetTestName)){
            _view.showFeedback(targetTestName , Constants.BAD_IMG_URL);
        }
        else{
            _view.showFeedback(targetTestName, Constants.GOOD_IMG_URL);
        }
    }

    /**
     * rules:
     * at least one word of the user input must occur in the test name correctly
     * sentence is dealt as a string
     * @param userInput
     * @param name
     * @return
     */
    private double calcScore(String userInput, String name) {

        List<String> splitInput = Arrays.asList(userInput.split(" "));
        List<String> splitName = Arrays.asList(name.split(" "));
        long size = splitInput.stream()
                .filter(splitName::contains)
                .count();

        if(size==0){
            return MAX_VALUE;
        }
        else{
           return Damerau.distance(userInput,name);
        }
    }


}
