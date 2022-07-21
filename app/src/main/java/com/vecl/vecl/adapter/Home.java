package com.vecl.vecl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.vecl.vecl.R;
import com.vecl.vecl.ui.fragment.keta3Car.Keta3Fragment;
import com.vecl.vecl.ui.fragment.newcar.NewCarsFragment;

public class Home extends RecyclerView.Adapter<Home.ViewHolder> {
    List<String>titles;
    List<Integer>images;
    LayoutInflater layoutInflater;

    public Home(Context context,List<String> titles, List<Integer> images) {
        this.titles = titles;
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.home_grid_rv,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt.setText(titles.get(position));
        holder.imageView.setImageResource(images.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.txt.getText().toString().equals("سيارات جديدة")||holder.txt.getText().toString().equals("New Cars")){
                    AppCompatActivity activity =(AppCompatActivity)
                     v.getContext();
                             activity.getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                            .replace(R.id.container, new NewCarsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                if (holder.txt.getText().toString().equals("Spare Parts")|| holder.txt.getText().toString().equals("قطع غيار")){
                    AppCompatActivity activity =(AppCompatActivity)
                            v.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                            .replace(R.id.container, new Keta3Fragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagehome);
            txt = itemView.findViewById(R.id.namehome);
        }
    }

}
