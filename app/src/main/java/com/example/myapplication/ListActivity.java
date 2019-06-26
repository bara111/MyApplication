package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListActivity extends Activity {


    private ListView mMissionListView;

    //Firebase
    private FirebaseFirestore db;

    //Adapter
    private Myadapter mMissionAdapter;
    private ArrayList<Todo> mMissionsList;
    private TextView task2;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * My code here deals with authentication
         *
         */
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        mMissionListView = findViewById(R.id.list_item);

        task2 = findViewById(R.id.task);
        //get Database
        db = FirebaseFirestore.getInstance();
        //Set up the ArrayList
        mMissionsList = new ArrayList<Todo>();
        //set the Adapter

        db.collection("todos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    task2.setText("Task LIST");

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Log.d("MissionActivity", document.getId() + " => " + document.getData());
                        task2.setVisibility(View.GONE);
                        Todo miss = document.toObject(Todo.class);
                        if (currentUser.getUid().equals(miss.getUserid()))
                            mMissionsList.add(miss);

                    }

                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });
        mMissionAdapter = new Myadapter(this, mMissionsList);

        mMissionListView.setAdapter(mMissionAdapter);
    }
}