package com.pharma.adapter.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pharma.R;
import com.pharma.model.parent.ELearningSectionModel;
import com.pharma.model.parent.EventsModel;
import java.util.List;

public class ELearningSectionAdapter extends RecyclerView.Adapter<ELearningSectionAdapter.ViewHolder> {

    private Context context;
    List<ELearningSectionModel> list;

    public ELearningSectionAdapter(Context context, List<ELearningSectionModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_elearning_section,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ELearningSectionModel model=list.get(i);
        viewHolder.tvSection.setText(model.getSection_name());
        viewHolder.rvSection.setHasFixedSize(true);
        viewHolder.rvSection.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        viewHolder.rvSection.setLayoutManager(gridLayoutManager);
        ELearningAdapter e=new ELearningAdapter(context,model.getList());
        viewHolder.rvSection.setAdapter(e);
        /*Glide.with(context)
                .load(model.getImg_url())
                .into(viewHolder.iv);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvSection;
        private final RecyclerView rvSection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvSection=(RecyclerView) itemView.findViewById(R.id.rvSection);
            tvSection=(TextView) itemView.findViewById(R.id.tvSection);
        }
    }
}