package com.example.finedu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class adapter extends FirebaseRecyclerAdapter<modal,viewholder> {
    Context context;
    public adapter(@NonNull FirebaseRecyclerOptions<modal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull modal model) {
        holder.module_name.setText(model.getModule_name());
        holder.module_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.module_name.getContext(),ViewPdf.class);
                intent.putExtra("pdf",model.getPdf());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.module_name.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new viewholder(view);
    }
}
