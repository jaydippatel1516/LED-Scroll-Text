package ledbanner.ledboard.ledscrollingtext.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import ledbanner.ledboard.ledscrollingtext.Activity.Led_EditActivity;
import ledbanner.ledboard.ledscrollingtext.AppUtils.ThemeData;
import ledbanner.ledboard.ledscrollingtext.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdp extends RecyclerView.Adapter<ThemeAdp.MyViewHolder> {
    List<ThemeData> data;
    Context context;

    public ThemeAdp(Context context, ArrayList<ThemeData> data) {
        this.context = context;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView imageView;

        public MyViewHolder(View v) {
            super(v);

            imageView = v.findViewById(R.id.itemimg);

        }
    }

    @Override
    public ThemeAdp.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themelist, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        ThemeData all = data.get(position);
        holder.imageView.setImageResource(all.imageId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Led_EditActivity.class).putExtra("img", all.orgimageId));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
