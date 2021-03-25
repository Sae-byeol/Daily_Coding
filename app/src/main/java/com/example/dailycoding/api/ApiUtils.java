package com.example.dailycoding.api;



public class ApiUtils {

    public static String BASE_URL_USER = "http://52.78.57.24:8080/";

    public static String BASE_URL_PROBLEM = "https://radiant-hollows-57561.herokuapp.com/";

    public static ServiceProblemApi getServiceProblemApi() {
        return RetrofitClient.getClient(BASE_URL_PROBLEM).create(ServiceProblemApi.class);
    }

    public static ServiceUserApi getServiceUserApi() {
        return RetrofitClient.getClient2(BASE_URL_USER).create(ServiceUserApi.class);
    }

}