package com.example.dailycoding.api;

import com.example.dailycoding.model.CheckAnswerBody;
import com.example.dailycoding.model.CheckAnswerResponse;
import com.example.dailycoding.model.LastProblemResponse;
import com.example.dailycoding.model.UserRankResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceUserApi {

    @GET("/user/allranking")
    Call<UserRankResponse> getRank();

    @Headers({
            "Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYXJ1Q29kaW5nIiwidXNlcklkeCI6M30.z0K53O6VotKLIWkdgNPrQ91T-u7ZY3zw9UAn_q-V4N0",
            "Content-Type: application/json"
    })
    @POST("/question")
    Call<CheckAnswerResponse> postCheckAnswer(@Body CheckAnswerBody checkAnswerBody);

    @Headers({
            "Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYXJ1Q29kaW5nIiwidXNlcklkeCI6M30.z0K53O6VotKLIWkdgNPrQ91T-u7ZY3zw9UAn_q-V4N0",
    })
    @GET("/last")
    Call<LastProblemResponse> getLastProblem();
}