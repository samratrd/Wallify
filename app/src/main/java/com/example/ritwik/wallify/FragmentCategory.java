package com.example.ritwik.wallify;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.CategoryAdapter;
import Models.Category;

public class FragmentCategory extends Fragment {

    private ProgressBar progressBar;
    private DatabaseReference dbCategories;
    private List<Category> categoryList;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;

    public FragmentCategory(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.category_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //We make progressbar visible at first


        progressBar = view.findViewById(R.id.category_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.category_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity(),categoryList);
        recyclerView.setAdapter(adapter);

        //Reference of database
        dbCategories = FirebaseDatabase.getInstance().getReference("categories");

        dbCategories.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {


                    //in Datasnapshot object we have all categories node
                    progressBar.setVisibility(View.GONE);

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String title = ds.child("title").getValue(String.class);
                        String thumburl = ds.child("url").getValue(String.class);

                        Category category = new Category(title, thumburl);
                        categoryList.add(category);
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
