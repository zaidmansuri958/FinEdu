package com.example.finedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Futures extends AppCompatActivity {
    RecyclerView recycle;
    adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futures);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recycle = findViewById(R.id.futures_recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modal> options = new FirebaseRecyclerOptions.Builder<modal>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("futures"), modal.class).build();

        myAdapter = new adapter(options);
        recycle.setAdapter(myAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}