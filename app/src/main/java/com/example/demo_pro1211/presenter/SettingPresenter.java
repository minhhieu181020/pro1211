package com.example.demo_pro1211.presenter;

import com.example.demo_pro1211.interfaces.SettingView;

public class SettingPresenter {
    private SettingView settingView;

    public SettingPresenter(SettingView settingView) {
        this.settingView = settingView;
    }
   public void mute(){
        settingView.mute();
    }
}
