package com.example.dailycoding.model;

public class RecentProblem {

    private int recentPython;
    private int recentJava;
    private int recentCpp;
    private int recentPythonIdx;
    private int recentJavaIdx;

    public int getRecentPythonIdx() {
        return recentPythonIdx;
    }

    public void setRecentPythonIdx(int recentPythonIdx) {
        this.recentPythonIdx = recentPythonIdx;
    }

    public int getRecentJavaIdx() {
        return recentJavaIdx;
    }

    public void setRecentJavaIdx(int recentJavaIdx) {
        this.recentJavaIdx = recentJavaIdx;
    }

    public int getRecentCppIdx() {
        return recentCppIdx;
    }

    public void setRecentCppIdx(int recentCppIdx) {
        this.recentCppIdx = recentCppIdx;
    }

    private int recentCppIdx;

    public RecentProblem(){}

    public RecentProblem(int recentPython, int recentJava, int recentCpp) {
        this.recentPython = recentPython;
        this.recentJava = recentJava;
        this.recentCpp = recentCpp;
    }

    public int getRecentPython() {
        return recentPython;
    }

    public void setRecentPython(int recentPython) {
        this.recentPython = recentPython;
    }

    public int getRecentJava() {
        return recentJava;
    }

    public void setRecentJava(int recentJava) {
        this.recentJava = recentJava;
    }

    public int getRecentCpp() {
        return recentCpp;
    }

    public void setRecentCpp(int recentCpp) {
        this.recentCpp = recentCpp;
    }
}
