package com.example.finedu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewholder extends RecyclerView.ViewHolder {
    TextView module_name;


    public viewholder(@NonNull View itemView) {
        super(itemView);
        module_name=itemView.findViewById(R.id.Module_name);

    }
}
