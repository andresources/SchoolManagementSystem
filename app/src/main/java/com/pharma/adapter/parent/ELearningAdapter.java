package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pharma.R;
import com.pharma.model.parent.ELearningModel;
import com.pharma.model.parent.EventsModel;

import java.util.List;


public class ELearningAdapter extends RecyclerView.Adapter<ELearningAdapter.ViewHolder> {

    private Context context;
    List<ELearningModel> list;

    public ELearningAdapter(Context context, List<ELearningModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_elearning,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ELearningModel model=list.get(i);
        viewHolder.tvSubject.setText(model.getTitle());
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAppInstalled = isAppInstalled(context,"com.learn.tapasyaedu");
                if(isAppInstalled) {
                    //This intent will help you to launch if the package is already installed
                    Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage("com.learn.tapasyaedu");
                    context.startActivity(LaunchIntent);
                } else {
                    Toast.makeText(context.getApplicationContext(), "Please install LMS app,please contact your branch admin",Toast.LENGTH_LONG).show();
                }
            }
        });
        Glide.with(context)
                .load(context.getResources()
                        .getIdentifier(model.getLogo(), "drawable", context.getPackageName())).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvSubject;
        private final ImageView iv;
        private final CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.iv);
            tvSubject=(TextView) itemView.findViewById(R.id.tvSubject);
            cv=(CardView) itemView.findViewById(R.id.cv);
        }
    }
    public  boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}