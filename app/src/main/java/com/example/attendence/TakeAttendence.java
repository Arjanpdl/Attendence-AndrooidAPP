package com.example.attendence;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TakeAttendence extends AppCompatActivity {
private EditText takeAttendence;
private Button attendenceBtn;
    private TextView btnvaluedatabase;
    private FirebaseAuth firebaseAuth;
    //..........Date
    private   TextView tvCounter;

    private TextView mDisplayDate;





   // DatabaseReference databaseReference;

    private  int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);
        firebaseAuth = FirebaseAuth.getInstance();

        //.. DAte
        mDisplayDate = (TextView) findViewById(R.id.tvDate);


        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = month+1 + "-" + day + "-" + year;

        mDisplayDate.setText(date);

        //..

        btnvaluedatabase = findViewById(R.id.classnamepassedtakeattendence);
        Intent classintent = getIntent();
        String classnamepassed = classintent.getStringExtra("Classname1");
        btnvaluedatabase.setText(classnamepassed);
        tvCounter = findViewById(R.id.tvcounter);
        takeAttendence = findViewById(R.id.takeattendence);
        attendenceBtn = findViewById(R.id.attendencebtn);

      // databaseReference = FirebaseDatabase.getInstance().getReference("Attendence");
// FROM LOACATION BUT REMBER TO GIVE IT INSIDE LOOOP ELSE WILL COME BACK
       final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("students");

  // final     DatabaseReference fromPath = FirebaseDatabase.getInstance().getReference("students");

           ref.child(btnvaluedatabase.getText().toString()).orderByChild("mcneeseId").equalTo(takeAttendence.getText().toString());
//....................... TO LOCATION
    final   DatabaseReference toPath = FirebaseDatabase.getInstance()
                .getReference("Attendence")
            .child(btnvaluedatabase.getText().toString())  // NEED TO GET DATE INPUT
            .child("Date = "+date)

          .child(takeAttendence.getText().toString());
// CHECKING IF THE GIVEN INPUT FROM USER IS IN DATABASE AND THEN IF IT IS COPY THAT CHILD AND PASTE THAT CHILD TO ATTENDENCE
        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(btnvaluedatabase.getText().toString()).orderByChild("mcneeseId").equalTo(takeAttendence.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            //.........................

                            ValueEventListener valueEventListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    toPath.child(takeAttendence.getText().toString()).setValue(dataSnapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isComplete()) {
                                                takeAttendence.setText("");
                                              //  Toast.makeText(TakeAttendence.this,"Attendence Accepted",Toast.LENGTH_SHORT).show();

                                            } else {

                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {}
                            };
                            ref.child(btnvaluedatabase.getText().toString()).child(takeAttendence.getText().toString()).addListenerForSingleValueEvent(valueEventListener);

                            //..................................................................
                            counter = counter + 1;
                            tvCounter.setText(String.valueOf(counter));
                            Toast.makeText(TakeAttendence.this,"Attendence Accepted",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(TakeAttendence.this,"Invalid",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }


    // attendence counter



    // logout below
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TakeAttendence.this,SecondActivity.class));
        Toast.makeText(TakeAttendence.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

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
