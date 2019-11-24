package com.example.demo_pro1211.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.dao.HighScoreDAO;
import com.example.demo_pro1211.model.HighScore;

import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreHolder> {
    private Context context;
    private List<HighScore> highScoreList;
    private HighScoreDAO highScoreDAO;



    public HighScoreAdapter(Context context, List<HighScore> highScoreList) {
        this.context = context;
        this.highScoreList = highScoreList;
    }

    @NonNull
    @Override
    public HighScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvhighscore, parent, false);
        HighScoreHolder highScoreHolder = new HighScoreHolder(view);

        return highScoreHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HighScoreHolder holder,final int position) {
        highScoreDAO=new HighScoreDAO(context);

        holder.tvText1.setText("Name: "+highScoreList.get(position).getName());
        holder.tvText2.setText("Score: "+highScoreList.get(position).getDiem() +"vnd");
        if (position==0){
            holder.imgstt.setImageResource(R.drawable.thu1);
        }
        else if (position == 1){
            holder.imgstt.setImageResource(R.drawable.so2);
        }else if (position  == 2){
            holder.imgstt.setImageResource(R.drawable.so3);
        }else {
            holder.imgstt.setImageResource(R.drawable.cry);
        }
    }

    @Override
    public int getItemCount() {

        return highScoreList.size();
    }
}
