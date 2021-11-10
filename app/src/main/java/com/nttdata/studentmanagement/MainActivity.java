package com.nttdata.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.nttdata.studentmanagement.adapter.StudentAdapter;
import com.nttdata.studentmanagement.api.APIUtils;
import com.nttdata.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddStudent;
    Button btnGetStudentsList;
    RecyclerView recyclerView;
    SearchView searchView;

    StudentService studentService;
    List<Student> list = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Student Management Application");

        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        btnGetStudentsList = (Button) findViewById(R.id.btnGetStudentsList);
        recyclerView = (RecyclerView) findViewById(R.id.listView);
        studentService = APIUtils.getStudentService();


        btnGetStudentsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudentList();
            }
        });

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                intent.putExtra("id", "");
                intent.putExtra("name", "");
                intent.putExtra("dob", "");
                intent.putExtra("gender", "");
                intent.putExtra("email", "");
                intent.putExtra("classes", "");
                startActivity(intent);
            }
        });
    }

    public void getStudentList() {
        Call<List<Student>> call = studentService.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    recyclerView.setAdapter(new StudentAdapter( list));
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}