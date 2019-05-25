package amaragya.bahaso;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.util.List;

import amaragya.bahaso.Model.ArticlesItem;
import amaragya.bahaso.Utility.utility;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InformasiAdapter extends
        RecyclerView.Adapter<InformasiAdapter.ViewHolder> {

    private static final String TAG = InformasiAdapter.class.getSimpleName();

    private Context context;
    private List<ArticlesItem> list;

    public InformasiAdapter(Context context, List<ArticlesItem> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_information,
                parent, false);
        return new ViewHolder(view);
    }


    void addItems(List<ArticlesItem> items) {
        this.list.addAll(items);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ArticlesItem item = list.get(position);


        try {
            String name = item.getTitle();
            String date = new utility().formattanggalindo(item.getPublishedAt());

            holder.title.setText(name);
            holder.content.setText(item.getContent());
            holder.date.setText(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String image = item.getUrlToImage();


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .priority(Priority.HIGH);

        Glide.with(holder.context)
                .applyDefaultRequestOptions(options)
                .load(image)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface AdapterCallback {
        void onRowClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.image)
        ImageView image;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = itemView.getContext();


        }
    }
}