package com.example.dailycoding.api;

import com.example.dailycoding.model.CategoryResponse;
import com.example.dailycoding.model.GetOneProblem;
import com.example.dailycoding.model.TheoryProblem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit2 서비스 정의
 * api를 입력하고 해당 api를 호출할 함수, 응답받을 데이터 형태 작성
 */

public interface ServiceProblemApi {

    @GET("/problem/theory/{lang}")
    Call<ArrayList<CategoryResponse>> getData(@Path("lang") String language);

    @GET("/problem/theory/detail/{lang}")
    Call <ArrayList<TheoryProblem>> getProblem(@Path("lang") String language, @Query("category") String category);

    @GET("/problem/getOne/{id}")
    Call <ArrayList<GetOneProblem>> getOneProblem(@Path("id") int id);
}