package com.vecl.vecl.ui.fragment.keta3Car;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;
import com.vecl.vecl.R;
import com.vecl.vecl.adapter.Kata3Adapter;
import com.vecl.vecl.eventBus.myUpdateCartEvent;
import com.vecl.vecl.listner.IcartLoadListner;
import com.vecl.vecl.model.CartModel;
import com.vecl.vecl.model.Keta3Model;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class Keta3Fragment extends Fragment  {
    Keta3ViewModel keta3ViewModel;
    RecyclerView recyclerView;
    List<Keta3Model> keta3ModelList = new ArrayList<>();
    IcartLoadListner icartLoadListner;
    NotificationBadge badge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keta3, container, false);
        recyclerView = view.findViewById(R.id.keta3_rv);
        badge = view.findViewById(R.id.badge);

        keta3ViewModel = ViewModelProviders.of(getActivity()).get(Keta3ViewModel.class);
        keta3ViewModel.getKat3();
        final Kata3Adapter kata3Adapter = new Kata3Adapter(keta3ModelList,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(kata3Adapter);
        keta3ViewModel.keta3Mutaple.observe(this, new Observer<List<Keta3Model>>() {
            @Override
            public void onChanged(List<Keta3Model> keta3Models) {
                kata3Adapter.setList(keta3Models);
            }
        });
        countCartItem();



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        if (EventBus.getDefault().hasSubscriberForEvent(myUpdateCartEvent.class))
            EventBus.getDefault().removeStickyEvent(myUpdateCartEvent.class);
        EventBus.getDefault().unregister(this);
        super.onStop();

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(myUpdateCartEvent event){
        countCartItem();
    }




    public void stota(List<CartModel>cartModelList){
        int sum =0;
        for (CartModel cartModel:cartModelList){
            sum+=cartModel.getQuantity();
            badge.setNumber(sum);

        }
    }



    @Override
    public void onResume() {
        super.onResume();
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel>cartModelList = new ArrayList<>();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference("Cart")
                .child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot:snapshot.getChildren()){
                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModelList.add(cartModel);
                        }
//                        icartLoadListner.onCartLoadListner(cartModelList);
                        stota(cartModelList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                  //      icartLoadListner.onCartLoadFaild(error.getMessage());
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}