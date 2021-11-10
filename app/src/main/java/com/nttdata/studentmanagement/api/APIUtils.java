package com.nttdata.studentmanagement.api;

import com.nttdata.studentmanagement.StudentService;

public class APIUtils {

    private APIUtils() {}

    public static final String API_URL = "http://192.168.1.186:8080/students/";

    public static StudentService getStudentService() {
        return RetrofitClient.getClient(API_URL).create(StudentService.class);
    }
}
