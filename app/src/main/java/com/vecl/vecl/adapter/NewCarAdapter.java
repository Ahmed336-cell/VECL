package com.vecl.vecl.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.vecl.vecl.R;
import com.vecl.vecl.ui.fragment.CarDetailesFragment;
import com.vecl.vecl.model.NewCarModel;

import java.util.ArrayList;
import java.util.List;

public class NewCarAdapter extends RecyclerView.Adapter<NewCarAdapter.NewCarViewHolder> {
    private List<NewCarModel> newCarModels = new ArrayList<>();
    Context context;

    public NewCarAdapter(List<NewCarModel> newCarModels, Context context) {
        this.newCarModels = newCarModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NewCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewCarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_car_item, parent, false));    }

    @Override
    public void onBindViewHolder(@NonNull NewCarViewHolder holder, int position) {
        Glide.with(context).load(newCarModels.get(position).getImg_url())
               .into(holder.car);
        holder.name.setText(newCarModels.get(position).getName());
        holder.cylinder.setText(newCarModels.get(position).getNum_models()+"");
        holder.date.setText(new StringBuilder(String.format("%.2f",newCarModels.get(position).getAvg_price())).append("$"));
      //  holder.acc.setText(new StringBuilder(newCarModels.get(position).getAcceleration()).append(" km/h^2"));
       holder.hourse.setText(String.format("%.2f",newCarModels.get(position).getAvg_horsepower()));

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               CarDetailesFragment carDetailesFragment = new CarDetailesFragment();
               Bundle bundle = new Bundle();
               bundle.putString("name",newCarModels.get(position).getName());
               bundle.putString("img",newCarModels.get(position).getImg_url());
               carDetailesFragment.setArguments(bundle);
               AppCompatActivity activity = (AppCompatActivity)v.getContext();
               activity.getSupportFragmentManager().beginTransaction().
                       setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                       .replace(R.id.container , carDetailesFragment)
                       .addToBackStack(null).commit();

           }
       });


    }
    public void setList (List<NewCarModel> newCarModels){
        this.newCarModels = newCarModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newCarModels.size();
    }

    public class NewCarViewHolder extends RecyclerView.ViewHolder {
        TextView name,acc,date,hourse,country,cylinder;
        ImageView car;
        public NewCarViewHolder(@NonNull View itemView) {
            super(itemView);
            car = itemView.findViewById(R.id.image_new_car);
            name = itemView.findViewById(R.id.newcar_name);
            hourse = itemView.findViewById(R.id.new_car_horsepower);
            date = itemView.findViewById(R.id.year_car_new);
            cylinder = itemView.findViewById(R.id.new_car_cylinders);

        }
    }
}
