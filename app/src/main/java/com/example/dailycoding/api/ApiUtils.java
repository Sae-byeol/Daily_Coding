package com.example.dailycoding.api;



public class ApiUtils {

    public static final String BASE_URL_PROBLEM = "https://radiant-hollows-57561.herokuapp.com/";

    public static final String BASE_URL_USER = "http://15.164.247.159:8081/";

    public static ServiceProblemApi getServiceProblemApi() {
        return RetrofitClient.getClient(BASE_URL_PROBLEM).create(ServiceProblemApi.class);
    }

    public static ServiceUserApi getServiceUserApi() {
        return RetrofitClient.getClient(BASE_URL_USER).create(ServiceUserApi.class);
    }
}