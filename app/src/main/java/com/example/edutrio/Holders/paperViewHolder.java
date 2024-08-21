package com.example.edutrio.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edutrio.R;

public class paperViewHolder extends RecyclerView.ViewHolder {
    TextView tvSubject;
    TextView tvClass;
    TextView tvYear;
    TextView tvGroup;
    TextView tvBoard;
    View view;

    public paperViewHolder(View itemView) {
        super(itemView);
        tvSubject = itemView.findViewById(R.id.tvPCardSubject);
        tvYear = itemView.findViewById(R.id.tvPCardYear);
        tvClass = itemView.findViewById(R.id.tvPCardClass);
        tvGroup = itemView.findViewById(R.id.tvPCardGroup);
        tvBoard = itemView.findViewById(R.id.tvPCardBoard);
        view = itemView;
    }

    public void setSubject(String subject) {
        this.tvSubject.setText(subject);
    }

    public void setClass(String discipline) {
        this.tvClass.setText(discipline);
    }

    public void setYear(int year) {
        this.tvYear.setText(String.valueOf(year));
    }

    public void setGroup(String group) {
        this.tvGroup.setText(group);
    }

    public void setBoard(String board) {
        this.tvBoard.setText(board);
    }

    public String getSubject(){
        return this.tvSubject.getText().toString();
    }

    public int getYear(){
        String year = this.tvYear.getText().toString();
        return Integer.parseInt(year);
    }

    public String getDiscipline(){
        return this.tvClass.getText().toString();
    }

    public String getGroup(){
        return this.tvGroup.getText().toString();
    }

    public String getBoard(){
        return this.tvBoard.getText().toString();
    }
}