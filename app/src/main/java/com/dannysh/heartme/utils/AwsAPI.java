package com.dannysh.heartme.utils;
import com.dannysh.heartme.model.BloodTestConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AwsAPI {

    @GET("{path}")
    Call<BloodTestConfig> getTestData(@Path("path") String filePath);
}
