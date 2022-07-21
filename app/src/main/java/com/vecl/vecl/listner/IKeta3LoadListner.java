package com.vecl.vecl.listner;

import com.vecl.vecl.model.Keta3Model;

import java.util.List;

public interface IKeta3LoadListner {
    void onDrinkLoadListner(List<Keta3Model>drinkModelList);
    void onDrinkLoadFaild(String message);
}
