package com.dannysh.heartme.model;

import java.util.List;

public class BloodTestConfig {

    private static BloodTestConfig instance = new BloodTestConfig();

    private BloodTestConfig(){

    }

    public static BloodTestConfig getInstance(){
        return instance;
    }


    private List<BloodTestParameter> bloodTestConfig;

    public List<BloodTestParameter> getTestParameters() {
        return bloodTestConfig;
    }

    public static void update(BloodTestConfig newConfig){
        instance = newConfig;
        // add Logic for pre-processing the test names
        // maybe seperate to a different class (hashmap<string -> double> + hashmap<string -> list of strings?> )
    }
}
