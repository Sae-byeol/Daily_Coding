package com.example.dailycoding.api;

import com.example.dailycoding.model.UserRankResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceUserApi {

    @GET("/user/allranking")
    Call<UserRankResponse> getRank();



}