package com.example.finedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Fundamental extends AppCompatActivity {
    RecyclerView recycle;
    adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundamental);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recycle = findViewById(R.id.fundamental_recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modal> options = new FirebaseRecyclerOptions.Builder<modal>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("fundamental"), modal.class).build();

        myAdapter=new adapter(options);
        recycle.setAdapter(myAdapter);

    }


    @Override
    protected void onStart(){
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        myAdapter.stopListening();
    }
}