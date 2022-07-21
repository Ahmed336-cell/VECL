package com.vecl.vecl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vecl.vecl.R;
import com.vecl.vecl.eventBus.myUpdateCartEvent;
import com.vecl.vecl.listner.IcartLoadListner;
import com.vecl.vecl.model.CartModel;
import com.vecl.vecl.model.Keta3Model;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class Kata3Adapter extends RecyclerView.Adapter<Kata3Adapter.Keta3ViewHolder> {
    private List<Keta3Model> keta3Models = new ArrayList<>();
    Context context;
    private IcartLoadListner icartLoadListner;


    public Kata3Adapter(List<Keta3Model> keta3Models, Context context) {
        this.keta3Models = keta3Models;
        this.context = context;
    }

    @NonNull
    @Override
    public Keta3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Keta3ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.keta3_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Keta3ViewHolder holder, int position) {
        Glide.with(context).load(keta3Models.get(position).getImgUrl())
                .into(holder.imageView);
        holder.name.setText(keta3Models.get(position).getName());
        holder.car.setText(keta3Models.get(position).getCar());
        holder.model.setText(keta3Models.get(position).getModel());
        holder.price.setText(keta3Models.get(position).getPrice()+ "$");
        holder.mech.setText(keta3Models.get(position).getMechPrice()+"");
        holder.Addcartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(keta3Models.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return keta3Models.size();
    }

    public void setList(List<Keta3Model> keta3Models) {
        this.keta3Models = keta3Models;
        notifyDataSetChanged();
    }

    public class Keta3ViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageView;
        AppCompatButton Addcartbtn;
        TextView name,price,model,car,mech;
        public Keta3ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageketa3);
            name = itemView.findViewById(R.id.nameKat3);
            price = itemView.findViewById(R.id.priceKat3);
            model = itemView.findViewById(R.id.modelCarKat3);
            car = itemView.findViewById(R.id.carKat3);
            mech = itemView.findViewById(R.id.mechCarKat3);
            Addcartbtn = itemView.findViewById(R.id.addcart);

        }
    }

    private void addToCart(Keta3Model keta3Model) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userCart = FirebaseDatabase.getInstance().getReference("Cart")
                .child(firebaseUser.getUid()); //add user id here
        userCart.child(keta3Model.getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()){
                            CartModel cartModel = new CartModel();
                            cartModel.setName(keta3Model.getName());
                            cartModel.setPrice(keta3Model.getPrice());
                            cartModel.setKey(keta3Model.getKey());
                            cartModel.setImage(keta3Model.getImgUrl());
                            cartModel.setMechPrice(keta3Model.getMechPrice());
                            cartModel.setQuantity(1);
                            userCart.child(keta3Model.getKey())
                                    .setValue(cartModel)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
//                                            icartLoadListner.onCartLoadFaild(context.getString(R.string.tocartsucc));
                                            Toast.makeText(context.getApplicationContext(), context.getString(R.string.tocartsucc), Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(e ->Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
                        }
                        EventBus.getDefault().postSticky(new myUpdateCartEvent());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        icartLoadListner.onCartLoadFaild(error.getMessage());

                    }
                });
    }
}
