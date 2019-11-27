package com.example.demo_pro1211.presenter;

import android.view.View;

import com.example.demo_pro1211.interfaces.PlayerView;

public class PlayerPresenter {
    private PlayerView playerView;

    public PlayerPresenter(PlayerView playerView) {
        this.playerView = playerView;
    }
    public void hienthicauhoi(){
        playerView.HienThiCauHoi();
    }
    public void xulychondapan(View view){
        playerView.xuLyChonDapAn(view);
    }
    public void loaibo2dapan(){
        playerView.Loaibo2phuonansai();
    }
    public void goiykhangia(){
        playerView.Ykenkhangia();
    }
    public void goidiennguoithan(){
        playerView.Goidiennguoithan();
    }
    public void doicauhoi(){
        playerView.Doicauhoi();
    }
    public void GameOver(){
        playerView.Gameover();
    }
}
