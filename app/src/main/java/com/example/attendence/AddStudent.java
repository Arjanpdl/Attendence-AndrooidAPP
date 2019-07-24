package com.example.attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {
private EditText studentName, mcneeseId;
private Button add;
private Button delete;
    private FirebaseAuth firebaseAuth;

private  TextView btnvaluedatabase;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        firebaseAuth = FirebaseAuth.getInstance();
        btnvaluedatabase = findViewById(R.id.passedclassnamedatabase);
        Intent classintent = getIntent();
        String classnamepassed = classintent.getStringExtra("Classname1");
        btnvaluedatabase.setText(classnamepassed);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");


        studentName = (EditText)findViewById(R.id.studentNamedatabase);
        mcneeseId = (EditText)findViewById(R.id.mcneeseiddatabase);
        add = (Button) findViewById(R.id.addStudentdatabase);
        delete = (Button) findViewById(R.id.deleteStudentdatabase);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });


    }


    public void addStudent(){
        String studentNameValue = studentName.getText().toString();
        String mcneeseIdValue = mcneeseId.getText().toString();
        if(!TextUtils.isEmpty(studentNameValue)&&!TextUtils.isEmpty(mcneeseIdValue)){
            String id = databaseReference.push().getKey();
            Students students = new Students(id,studentNameValue,mcneeseIdValue);
           // databaseReference.child(bttnName.getText().toString()).push().setValue(students);
            databaseReference.child(btnvaluedatabase.getText().toString()).child(mcneeseId.getText().toString()).setValue(students);
            studentName.setText("");
            mcneeseId.setText("");
            Toast.makeText(AddStudent.this,"Student Details Added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AddStudent.this,"Please Fill Fields",Toast.LENGTH_SHORT).show();
        }
    }

    public  void  deleteStudent(){
        String studentNameValue = studentName.getText().toString();
        String mcneeseIdValue = mcneeseId.getText().toString();

        if(!TextUtils.isEmpty(studentNameValue)&&!TextUtils.isEmpty(mcneeseIdValue)){
            String id = databaseReference.push().getKey();
            Students students = new Students(id,studentNameValue,mcneeseIdValue);
            // databaseReference.child(bttnName.getText().toString()).push().setValue(students);
            databaseReference.child(btnvaluedatabase.getText().toString()).child(mcneeseId.getText().toString()).removeValue();

                    studentName.setText("");
            mcneeseId.setText("");
            Toast.makeText(AddStudent.this,"Student Deleted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AddStudent.this,"Please Fill Fields",Toast.LENGTH_SHORT).show();
        }
    }









    //logout

    // logout below
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AddStudent.this,SecondActivity.class));
        Toast.makeText(AddStudent.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
