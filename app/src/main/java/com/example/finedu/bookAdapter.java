package com.example.finedu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class bookAdapter extends FirebaseRecyclerAdapter<bookModal,bookAdapter.viewholder> {

    public bookAdapter(@NonNull FirebaseRecyclerOptions<bookModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull bookModal model) {
        holder.bookName.setText(model.getName());
        Glide.with(holder.bookImage.getContext()).load(model.getImage()).into(holder.bookImage);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.card.getContext(),ViewPdf.class);
                intent.putExtra("pdf",model.getPdf());
                intent.putExtra("intent","books");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.card.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcard,parent,false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView bookName;
        ImageView bookImage;
        CardView card;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            bookName=itemView.findViewById(R.id.bookName);
            bookImage=itemView.findViewById(R.id.bookImage);
            card=itemView.findViewById(R.id.card);

        }
    }
}
