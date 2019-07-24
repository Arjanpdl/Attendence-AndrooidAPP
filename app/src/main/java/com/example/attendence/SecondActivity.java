package com.example.attendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Email = (EditText) findViewById(R.id.editemail);
        Password =(EditText) findViewById(R.id.editpassword);
        Login = (Button ) findViewById(R.id.loginmainbutton);

        firebaseAuth = FirebaseAuth.getInstance();
        processDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(this,ThirdActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        //classname = findViewById(R.id.viewclassname);

//        Intent classintent = getIntent();
//        String classnamepassed = classintent.getStringExtra("Classname");
//        classname.setText(classnamepassed);
    }

  private void validate (String userEmail, String userPassword){

        processDialog.setMessage("........Please Wait.......");
        processDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    processDialog.dismiss();
                    Toast.makeText(SecondActivity.this,"LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
                }else {
                    Toast.makeText(SecondActivity.this,"LOGIN FAILED", Toast.LENGTH_SHORT).show();
                    processDialog.dismiss();

                }
            }
        });

  }

}
