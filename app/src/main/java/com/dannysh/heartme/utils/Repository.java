package com.dannysh.heartme.utils;

import com.dannysh.heartme.model.BloodTestConfig;
import com.dannysh.heartme.model.BloodTestParameter;

import java.util.HashMap;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    AwsAPI _api;
    BloodTestConfig _bloodTestConfig = null;
    private HashMap<String, Double> _testToThreshold = null;

    public BloodTestConfig get_bloodTestConfig() {
        return _bloodTestConfig;
    }

    public void loadData() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.S3_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        _api = retrofit.create(AwsAPI.class);

        Call<BloodTestConfig> parameters = _api.getTestData(Constants.S3_PATH_URL);
        parameters.enqueue(new Callback<BloodTestConfig>() {
            @Override
            public void onResponse(Call<BloodTestConfig> call, Response<BloodTestConfig> response) {
                _testToThreshold = new HashMap<>();
                _bloodTestConfig = response.body();
                //make sure there is a response
                if (_bloodTestConfig != null) {
                    //make sure there is data in the response
                    if (!_bloodTestConfig.getTestParameters().isEmpty()) {
                        for (BloodTestParameter param : _bloodTestConfig.getTestParameters()) {
                            _testToThreshold.put(param.getName(), param.getThreshold());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BloodTestConfig> call, Throwable t) {

            }
        });
    }

    public Set<String> getTestNames() {
        if (_testToThreshold != null) {
            return _testToThreshold.keySet();
        }
        return null;
    }

    public double getThreshold(String testName) {
        return _testToThreshold.get(testName);
    }
}
