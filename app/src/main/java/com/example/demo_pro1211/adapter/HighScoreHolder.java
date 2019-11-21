package com.example.demo_pro1211.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_pro1211.R;

class HighScoreHolder extends RecyclerView.ViewHolder {
    public TextView tvText1, tvText2;
    public HighScoreHolder(@NonNull View itemView) {
        super(itemView);
        tvText1 = itemView.findViewById(R.id.tvText1);
        tvText2 = itemView.findViewById(R.id.tvText2);
    }
}
