package com.example.dailycoding.api;

public class ApiUtils {

    public static final String BASE_URL = "https://radiant-hollows-57561.herokuapp.com/";

    public static ServiceApi getServiceApi() {
        return RetrofitClient.getClient(BASE_URL).create(ServiceApi.class);
    }
}