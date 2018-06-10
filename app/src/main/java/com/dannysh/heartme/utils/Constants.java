package com.dannysh.heartme.utils;

public class Constants {

    //direct access
    public static final String S3_URL = "https://s3.amazonaws.com/s3.helloheart.home.assignment/bloodTestConfig.json";

    public static final int SOCKET_TIMEOUT = 10000;

    public static final String BAD_IMG_URL ="https://cdn1.iconfinder.com/data/icons/toolbar-signs/512/cancel-512.png";
    public static final String GOOD_IMG_URL ="https://cdn1.iconfinder.com/data/icons/toolbar-signs/512/OK-512.png";
    public static final String UNKNOWN_IMG_URL="https://cdn1.iconfinder.com/data/icons/toolbar-signs/512/question-512.png";
    public static final String ERROR_IMG_URL="https://cdn1.iconfinder.com/data/icons/toolbar-signs/512/warning-512.png";

    public enum TetsFeedBackStatus{
        GOOD , BAD , UNKNOWN , ERROR
    }
}
