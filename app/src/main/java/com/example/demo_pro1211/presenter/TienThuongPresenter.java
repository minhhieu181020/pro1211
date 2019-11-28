package com.example.demo_pro1211.presenter;

import com.example.demo_pro1211.interfaces.TienThuongView;

public class TienThuongPresenter {
    private TienThuongView tienThuongView;

    public TienThuongPresenter(TienThuongView tienThuongView) {
        this.tienThuongView = tienThuongView;
    }
    public void showAlertDialog(){
        tienThuongView.ShowAlertDialog();
    }
    public void showDialog(){
        tienThuongView.ShowDialog();
    }
    public void back(){
        tienThuongView.back();
    }
}
