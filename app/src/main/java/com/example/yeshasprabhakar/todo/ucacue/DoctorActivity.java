package com.example.yeshasprabhakar.todo.ucacue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeshasprabhakar.todo.R;
import com.example.yeshasprabhakar.todo.adapter.DoctorAdapter;
import com.example.yeshasprabhakar.todo.adapter.ItemAdapter;
import com.example.yeshasprabhakar.todo.model.DataModel;
import com.example.yeshasprabhakar.todo.model.DatabaseHelper;
import com.example.yeshasprabhakar.todo.model.Doctor;
import com.example.yeshasprabhakar.todo.model.User;
import com.example.yeshasprabhakar.todo.model.UuidMother;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    private static final String TAG = "MainActivity";

    private ArrayList<Doctor> items ;
    private DoctorAdapter itemsAdopter;
    private ListView itemsListView;
    private DatabaseReference mDatabase;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Button and items
        fab = findViewById(R.id.fabDoctor);
        itemsListView = findViewById(R.id.itemsListDoctor);

        //initialise and set empty listView
        TextView empty = findViewById(R.id.emptyTextViewDoctor);
        empty.setText(Html.fromHtml(getString(R.string.listEmptyText)));
        FrameLayout emptyView = findViewById(R.id.emptyViewDoctor);
        itemsListView.setEmptyView(emptyView);

         writeNewUser("Maria Pinguil", "maternidad","03010506225");
         getData();

         onFabClick();
         hideFab();


    }

    private void writeNewUser( String name, String email,String cedula) {
        Doctor doctor = new Doctor(name, email,cedula);
        mDatabase.child("doctors").child(UuidMother.random()).setValue(doctor);
    }

    private void getData() {
        final ArrayList<Doctor> respu = new ArrayList<>();
        // My top posts by number of stars
        mDatabase.child("doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i =0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        Doctor valor = postSnapshot.getValue(Doctor.class);
                      respu.add(valor);
                        i++;
                }
                Toast.makeText(getApplicationContext(),"Usuarios "+ i,Toast.LENGTH_LONG).show();

                populateListView(respu);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        });
    }


    //On floating button click open dialog
    private void onFabClick() {
        try {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   showAddDialog();
                    Log.d(TAG, "onFabClick: Opened edit dialog");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //Populate listView with data from database
    private void populateListView(ArrayList<Doctor> respu ) {
        try {
            // items = databaseHelper.getAllData();
            itemsAdopter = new DoctorAdapter(this, respu);
            itemsListView.setAdapter(itemsAdopter);
            itemsAdopter.notifyDataSetChanged();
            Log.d(TAG, "populateListView: Displaying data in list view");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Hide fab on list scroll
    private void hideFab() {
        itemsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    fab.show();
                }else{
                    fab.hide();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }
}