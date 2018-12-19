package com.example.ritwik.wallify;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.GeneralViewAdapter;
import Models.GeneralViewModel;

public class SectionDetail extends AppCompatActivity {

    private Bundle extras;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private DatabaseReference dbSectionDetail;
    private List<GeneralViewModel> generalViewModel;
    private RecyclerView recyclerView;
    private GeneralViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();

        if (extras!=null){

            getSupportActionBar().setTitle(extras.getString("title"));
        }

        progressBar = findViewById(R.id.detail_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //initializing recycler view
        recyclerView = findViewById(R.id.section_detail_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        generalViewModel = new ArrayList<>();

        //setting adapter to rv
        adapter = new GeneralViewAdapter(this,generalViewModel);
        recyclerView.setAdapter(adapter);

        //Reading data from firebase
        dbSectionDetail = FirebaseDatabase.getInstance().getReference(extras.getString("title"));
        dbSectionDetail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    progressBar.setVisibility(View.GONE);

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String thumburl = ds.child("url").getValue(String.class);

                        GeneralViewModel gvm = new GeneralViewModel(thumburl);
                        generalViewModel.add(gvm);
                    }
                    adapter.notifyDataSetChanged();//Relode Recycler View

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
