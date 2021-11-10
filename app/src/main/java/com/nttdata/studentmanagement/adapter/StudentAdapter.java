package com.nttdata.studentmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nttdata.studentmanagement.R;
import com.nttdata.studentmanagement.StudentActivity;
import com.nttdata.studentmanagement.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_student, parent, false);
        return new StudentViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        if (student == null) {
            return;
        }
        holder.bindView(student);
    }

    @Override
    public int getItemCount() {
        if (students != null) {
            return students.size();
        }
        return 0;
    }

//    private Context context;
//    private List<Student> students;
//
//    public StudentAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
//        super(context, resource, objects);
//        this.context = context;
//        this.students = objects;
//    }
//
//    @Override
//    public View getView(final int pos, View convertView, ViewGroup parent){
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.list_student, parent, false);
//
//        TextView txtStudentName = (TextView) rowView.findViewById(R.id.txtStudentName);
//        TextView txtStudentClass = (TextView) rowView.findViewById(R.id.txtStudentClass);
//
//        txtStudentName.setText(String.format("Student name: %s", students.get(pos).getName()));
//        txtStudentClass.setText(String.format("Class: %s", students.get(pos).getClasses()));
//
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, StudentActivity.class);
//                intent.putExtra("id", String.valueOf(students.get(pos).getId()));
//                intent.putExtra("name", students.get(pos).getName());
//                intent.putExtra("dob", students.get(pos).getDob());
//                intent.putExtra("gender", students.get(pos).getGender());
//                intent.putExtra("email", students.get(pos).getEmail());
//                intent.putExtra("class", students.get(pos).getClasses());
//                context.startActivity(intent);
//            }
//        });
//        return rowView;
//    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtStudentName;
        TextView txtStudentClass;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStudentName = (TextView) itemView.findViewById(R.id.txtStudentName);
            txtStudentClass = (TextView) itemView.findViewById(R.id.txtStudentClass);
        }

        public void bindView(Student student) {
            txtStudentName.setText(String.format("Student name: %s", student.getName()));
        txtStudentClass.setText(String.format("Class: %s", student.getClasses()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), StudentActivity.class);
                intent.putExtra("id", String.valueOf(student.getId()));
                intent.putExtra("name", student.getName());
                intent.putExtra("dob", student.getDob());
                intent.putExtra("gender", student.getGender());
                intent.putExtra("email", student.getEmail());
                intent.putExtra("class", student.getClasses());
                itemView.getContext().startActivity(intent);
            }
        });
        }
    }
}
