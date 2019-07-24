package com.example.attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText classname;
    int request_code =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //classname = findViewById(R.id.editclassname);
    }
    public void submit (View view)
    {

        startActivity(new Intent(this,SecondActivity.class));
//        String TextClassname = classname.getText().toString();
//        // starting our intent
//        Intent classintent = new Intent(this,SecondActivity.class);
//        classintent.putExtra("Classname",TextClassname);
//        startActivityForResult(classintent,request_code);
    }
    public void register (View view)
    {
        startActivity(new Intent(this,FourthActivity.class));
    }
}
