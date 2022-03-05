package com.example.kfaumanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.CardDesignHold> {
    private Context mContext;
    private List<Students> studentsList;

    public StudentsAdapter() {
    }

    @NonNull
    @Override
    public CardDesignHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);

        return new CardDesignHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHold holder, int position) {
        final Students students = studentsList.get(position);

        holder.textViewId.setText(String.valueOf(students.getId()));
        holder.textViewName.setText(students.getName());
        holder.textViewPrice.setText(String.valueOf(students.getPrice()));

        holder.studentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);

                intent.putExtra("object", students);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public StudentsAdapter(Context mContext, List<Students> studentsList) {
        this.mContext = mContext;
        this.studentsList = studentsList;
    }

    public class CardDesignHold extends RecyclerView.ViewHolder{
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewPrice;
        private CardView studentCard;

        public CardDesignHold(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            studentCard = itemView.findViewById(R.id.studentCard);
        }
    }
}
