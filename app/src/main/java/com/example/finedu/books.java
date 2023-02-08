package com.example.finedu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class books extends Fragment {
    RecyclerView recycle;
    bookAdapter myAdapter;
    Context context;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public books() {

    }

    public static books newInstance(String param1, String param2) {
        books fragment = new books();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_books, container, false);
        context= container.getContext();
        recycle=rootView.findViewById(R.id.books_recycle);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        recycle.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<bookModal> options = new FirebaseRecyclerOptions.Builder<bookModal>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("book"), bookModal.class).build();

        myAdapter=new bookAdapter(options);
        recycle.setAdapter(myAdapter);
        return rootView;
    }
    @Override
    public void onStart(){
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        myAdapter.stopListening();
    }
}
