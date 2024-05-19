package ledbanner.ledboard.ledscrollingtext.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import ledbanner.ledboard.ledscrollingtext.Activity.Led_PreviewVideoActivity;
import ledbanner.ledboard.ledscrollingtext.Activity.ViewHistoryActivity;
import ledbanner.ledboard.ledscrollingtext.Theme.BannerViewHistoryActivity;
import ledbanner.ledboard.ledscrollingtext.Model.Video;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    Context context;
    ArrayList<Video> arrayList1;

    public BannerAdapter(Context context, ArrayList<Video> arrayList) {
        this.arrayList1 = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(arrayList1.get(position).getPath()).into(holder.imageView);
        if (position == 2) {
            holder.relseeall.setVisibility(View.VISIBLE);
            holder.relseeall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent = new Intent(context, BannerViewHistoryActivity.class);
                            context.startActivity(intent);

                }
            });
        } else {
            holder.relseeall.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Led_PreviewVideoActivity.class);
                intent.putExtra("vedio",arrayList1.get(position).getPath());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (arrayList1.size() > 3) {
            return 3;
        } else {
            return arrayList1.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageView;
        RelativeLayout relseeall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemhistory);
            relseeall = itemView.findViewById(R.id.relseeall);
        }
    }


}
