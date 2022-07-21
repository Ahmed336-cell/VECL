package com.vecl.vecl.listner;

import com.vecl.vecl.model.CartModel;

import java.util.List;

public interface IcartLoadListner {
    void onCartLoadListner(List<CartModel> cartModelList);
    void onCartLoadFaild(String message);

}
