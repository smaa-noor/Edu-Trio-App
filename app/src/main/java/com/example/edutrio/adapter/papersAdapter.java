package com.example.edutrio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutrio.Holders.paperViewHolder;
import com.example.edutrio.Models.paperModel;
import com.example.edutrio.R;

import java.util.Collections;
import java.util.List;

public class papersAdapter extends RecyclerView.Adapter<paperViewHolder> {

    List<paperModel> papersList;
    Context context;

    public papersAdapter(List<paperModel> papers, Context context) {
        this.papersList = papers;
        this.context = context;
    }

    @NonNull
    @Override
    public paperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);
        View paperView
                = inflater
                .inflate(R.layout.paper_card,
                        parent, false);
        return new paperViewHolder(paperView);
    }

    @Override
    public void onBindViewHolder(@NonNull paperViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        paperModel paper = papersList.get(position);
        holder.setSubject(paper.getSubject());
        holder.setYear(paper.getYear());
        holder.setClass(paper.getDiscipline());
        holder.setGroup(paper.getGroup());
        holder.setBoard(paper.getBoard());
    }

    @Override
    public int getItemCount() {
        return papersList.size();
    }
}
