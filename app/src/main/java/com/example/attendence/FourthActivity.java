package com.example.attendence;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FourthActivity extends AppCompatActivity {

    private EditText UserNameEmail, UserClassName, UserPasswordRegister;
    private Button UserRegisterBtn;

    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        UserNameEmail = findViewById(R.id.profemail);
        UserClassName = findViewById(R.id.classnameregister);
        UserPasswordRegister = findViewById(R.id.passwordRegister);
        UserRegisterBtn= findViewById(R.id.buttonRegister);

        fireBaseAuth =FirebaseAuth.getInstance();

        UserRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validate()){
                   // Register to database upload
                   String user_email = UserNameEmail.getText().toString().trim();
                   String user_password = UserPasswordRegister.getText().toString().trim();
                   fireBaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(FourthActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(FourthActivity.this,ThirdActivity.class));
                           }else{
                               Toast.makeText(FourthActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                           }

                       }
                   });
               }
            }
        });

    }

    private Boolean validate(){
        boolean result = false;
        String name = UserNameEmail.getText().toString();
        String password = UserPasswordRegister.getText().toString();
        String classname = UserClassName.getText().toString();

        if(name.isEmpty() && password.isEmpty() || classname.isEmpty()){
            Toast.makeText(this,"Please Enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result =true;
        }
        return result;
    }
}
