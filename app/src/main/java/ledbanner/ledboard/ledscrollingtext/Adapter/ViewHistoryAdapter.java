package ledbanner.ledboard.ledscrollingtext.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import ledbanner.ledboard.ledscrollingtext.Activity.Led_PreviewVideoActivity;
import ledbanner.ledboard.ledscrollingtext.Model.Video;
import ledbanner.ledboard.ledscrollingtext.R;

import java.util.ArrayList;

public class ViewHistoryAdapter extends RecyclerView.Adapter<ViewHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Video> arrayList;

    public ViewHistoryAdapter(Context context, ArrayList<Video> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position).getPath()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Led_PreviewVideoActivity.class);
                intent.putExtra("vedio",arrayList.get(position).getPath());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemhistory);

        }
    }


}
