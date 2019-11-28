package com.example.demo_pro1211.presenter;

import com.example.demo_pro1211.interfaces.HighScoreView;

public class HighScorePresenter {
    private HighScoreView highScoreView;

    public HighScorePresenter(HighScoreView highScoreView) {
        this.highScoreView = highScoreView;
    }
   public void share(){
        highScoreView.Share();
    }
   public void back(){
        highScoreView.back();
    }
}
