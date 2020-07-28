package com.example.yeshasprabhakar.todo.ucacue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yeshasprabhakar.todo.R;
import com.example.yeshasprabhakar.todo.model.User;
import com.example.yeshasprabhakar.todo.model.UuidMother;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //writeNewUser("Maria Pinguil", "maria@gmail.com");
        getData();
    }

    private void getData() {

        // My top posts by number of stars
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i =0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                        i++;
                }
                Toast.makeText(getApplicationContext(),"Usuarios "+ i,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        });
    }

    private void writeNewUser( String name, String email) {
        User user = new User(name, email);
        mDatabase.child("users").child(UuidMother.random()).setValue(user);
    }
}