package com.pharma.adapter.parent;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pharma.R;
import com.pharma.fragment.patient.StudentGalleryFragment;
import com.pharma.model.parent.EventsModel;
import com.pharma.model.parent.GalleryModel;

import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    List<GalleryModel> list;
    StudentGalleryFragment frg;
    public GalleryAdapter(Context context, List<GalleryModel> list,StudentGalleryFragment frg) {
        this.context = context;
        this.list = list;
        this.frg = frg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_gallery,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GalleryModel model=list.get(i);
        Log.i("ImgURL",model.getUrl());
        Glide.with(context)
                .load(model.getUrl())
                .into(viewHolder.iv);
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg.displayImageAlert(model.getUrl(),"yes");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.iv);
        }
    }
}