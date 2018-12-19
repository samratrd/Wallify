package com.example.ritwik.wallify;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.CategoryAdapter;
import Adapter.GeneralViewAdapter;
import Models.Category;
import Models.GeneralViewModel;

public class FragmentRecent extends Fragment {

    private ProgressBar progressBar;
    private DatabaseReference dbRecent;
    private List<GeneralViewModel> generalViewModel;
    private RecyclerView recyclerView;
    private GeneralViewAdapter adapter;

    View v;
    public FragmentRecent(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.recent_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //make progressbr visible
        progressBar = view.findViewById(R.id.recent_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //initializing recycler view
        recyclerView = view.findViewById(R.id.recent_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);

        //Initializing list
        generalViewModel = new ArrayList<>();

        //setting adapter to rv
        adapter = new GeneralViewAdapter(getActivity(),generalViewModel);
        recyclerView.setAdapter(adapter);

        //Reading data from firebase
        dbRecent = FirebaseDatabase.getInstance().getReference("recent");
        dbRecent.addListenerForSingleValueEvent(new ValueEventListener() {
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
