package com.example.dailycoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Json 스키마를 POJO 형식으로 컨버팅
 * http://www.jsonschema2pojo.org/ 사이트를 이용하면 편하게 코드를 작성해주니 참고
 */

public class CategoryResponse {
    @SerializedName("category")
    @Expose
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
