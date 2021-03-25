package com.example.dailycoding.api;

import com.example.dailycoding.model.CategoryResponse;
import com.example.dailycoding.model.ErrorResponse;
import com.example.dailycoding.model.UserRankResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceUserApi {

    @GET("/user/allranking/")
    Call<UserRankResponse> getRank();

}