package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private RecyclerView recyclerView;
    private Myadapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
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


        // specify an adapter (see also next example)
        mMissionsList = new ArrayList<Todo>();

        currentUser = mAuth.getCurrentUser();
       // recyclerView.setHasFixedSize(true);
       // mMissionListView = findViewById(R.id.list_item);
       task2 = findViewById(R.id.task);
        //get Database
        db = FirebaseFirestore.getInstance();
        //Set up the ArrayList
        recyclerView =  findViewById(R.id.my_recycler_view);
        mAdapter = new Myadapter(mMissionsList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        //set the Adapter

        db.collection("todos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  task2.setText("Task LIST");

                    for (QueryDocumentSnapshot document : task.getResult()) {
                       task2.setVisibility(View.GONE);

                        Log.d("MissionActivity", document.getId() + " => " + document.getData());
                        Todo miss = document.toObject(Todo.class);
                        if (currentUser.getUid().equals(miss.getUserid()))
                            mMissionsList.add(miss);
                        mAdapter.notifyDataSetChanged();
                    }

                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager



    }
}