package com.example.demo_pro1211.presenter;

import com.example.demo_pro1211.interfaces.HomeView;

public class HomePresenter {
    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    public void setting() {
        homeView.setting();
    }
    public void rule(){
        homeView.rule();
    }
    public void play(){
        homeView.play();
    }
    public void highscore(){
        homeView.highscore();
    }
    public void animator(){
        homeView.animator();
    }
}
