package ledbanner.ledboard.ledscrollingtext.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import ledbanner.ledboard.ledscrollingtext.Model.LanguageModel;
import ledbanner.ledboard.ledscrollingtext.R;

import java.util.List;


public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHodler> {
    private List<LanguageModel> list;
    Context context;
    LangClickListener langClickListener;

    public interface LangClickListener {
        public void onCLick(LanguageModel model);
    }

    public LanguageAdapter(Context context, List<LanguageModel> list, LangClickListener langClickListener) {
        this.context = context;
        this.list = list;
        this.langClickListener = langClickListener;
    }

    @Override
    @NonNull
    public LanguageViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LanguageViewHodler(LayoutInflater.from(context).inflate(R.layout.layout_item_language, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHodler holder, int position) {
        LanguageModel model = this.list.get(position);

        holder.imgLanguage.setImageResource(model.getLangImage());
        holder.txtLanguageName.setText(model.getLangName());

        if (model.isSelected()) {
            holder.radioSelected.setBackgroundResource(R.drawable.bg_stage_border);
        } else {
            holder.radioSelected.setBackgroundResource(R.drawable.bg_stage);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LanguageModel iVar : list) {
                    iVar.setSelected(false);
                }

                list.get(position).setSelected(true);
                langClickListener.onCLick(list.get(position));
                notifyDataSetChanged();
            }
        });
    }

    public class LanguageViewHodler extends RecyclerView.ViewHolder {
        public final AppCompatImageView imgLanguage;
        public final AppCompatTextView txtLanguageName;
        public final RelativeLayout radioSelected;

        public LanguageViewHodler(@NonNull View view) {
            super(view);
            this.imgLanguage = view.findViewById(R.id.img_avatar);
            this.txtLanguageName = view.findViewById(R.id.tv_title);
            this.radioSelected = view.findViewById(R.id.radioSelected);

        }
    }

}
