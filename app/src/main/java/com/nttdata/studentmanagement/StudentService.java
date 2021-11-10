package com.nttdata.studentmanagement;

import com.nttdata.studentmanagement.model.ResObj;
import com.nttdata.studentmanagement.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {

    @GET("list")
    Call<List<Student>> getStudents();

    @POST("add")
    Call<Student> addStudent(@Body Student student);

    @PUT("update/{id}")
    Call<Student> updateStudent(@Path("id") int id, @Body Student student);

    @DELETE("delete/{id}")
    Call<Student> deleteStudent(@Path("id") int id);

    @GET("login/{username}/{password}")
    Call<ResObj> login(@Path("username") String username, @Path("password") String password);
}
