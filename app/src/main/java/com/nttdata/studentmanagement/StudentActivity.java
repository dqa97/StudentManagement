package com.nttdata.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nttdata.studentmanagement.api.APIUtils;
import com.nttdata.studentmanagement.model.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    StudentService studentService;
    EditText edtName;
    EditText edtDob;
    EditText edtGender;
    EditText edtEmail;
    EditText edtClass;
    EditText edtId;
    Button btnSave;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        setTitle("Student Management Application");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = (EditText) findViewById(R.id.edtName);
        edtDob = (EditText) findViewById(R.id.edtDob);
        edtGender = (EditText) findViewById(R.id.edtGender);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtClass = (EditText) findViewById(R.id.edtClass);
        edtId = (EditText) findViewById(R.id.edtId);
        btnSave =(Button) findViewById(R.id.btnSave);
        btnDel =(Button) findViewById(R.id.btnDel);
        studentService = APIUtils.getStudentService();

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        String name = extras.getString("name");
        String dob = extras.getString("dob");
        String gender = extras.getString("gender");
        String email = extras.getString("email");
        String classes = extras.getString("class");

        edtName.setText(name);
        edtDob.setText(dob);
        edtGender.setText(gender);
        edtEmail.setText(email);
        edtClass.setText(classes);

        if (id != null && id.trim().length() > 0) {
            edtId.setFocusable(false);
        } else {
            edtId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
//                student.setId(edtId.getId());
                student.setName(edtName.getText().toString());
                student.setDob(edtDob.getText().toString());
                student.setGender(edtGender.getText().toString());
                student.setEmail(edtEmail.getText().toString());
                student.setClasses(edtClass.getText().toString());
                if (id != null && id.trim().length() >0) {
                    updateStudent(Integer.parseInt(id), student);
                } else {
                    addStudent(student);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent(Integer.parseInt(id));
                Intent intent = new Intent(StudentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void addStudent(Student student) {
        Call<Student> call = studentService.addStudent(student);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    Toast.makeText(StudentActivity.this, "Student created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    private void updateStudent(int id, Student student) {
        Call<Student> call = studentService.updateStudent(id,student);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    Toast.makeText(StudentActivity.this, "Student updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void deleteStudent(int id) {
        Call<Student> call = studentService.deleteStudent(id);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    Toast.makeText(StudentActivity.this, "Student delete Successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}