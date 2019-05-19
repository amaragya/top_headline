package mobileapps.talbia.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobileapps.talbia.Model.Informasi_m;
import mobileapps.talbia.R;

public class InformasiAdapter extends
        RecyclerView.Adapter<InformasiAdapter.ViewHolder> {

    private static final String TAG = InformasiAdapter.class.getSimpleName();

    private Context context;
    private List<Informasi_m> list;
    private AdapterCallback mAdapterCallback;

    public InformasiAdapter(Context context, List<Informasi_m> list, AdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informasi,
                parent, false);
        return new InformasiAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(InformasiAdapter.ViewHolder holder, final int position) {
        Informasi_m item = list.get(position);

        String name = item.getTitle();
        String description = item.getExcerpt();

        holder.tvName.setText(name);
        holder.tvDesc.setText("By : " + item.getAuthor());
        holder.excerpt.setText(item.getExcerpt());
        holder.tanggal.setText(item.getCreatedAt());
        final String gambar = item.getImage();


        String base_url = "http://talbia.decimatech.co.id/public/storage/";


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(holder.context)
                .applyDefaultRequestOptions(options)
                .load(base_url + gambar)
                .into(holder.gambar);


        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
//
//                    Intent inten = new Intent(context,Detail_doa.class);
//                    startActivity(inten);
                    mAdapterCallback.onRowClicked(position);
                } catch (Exception e) {
                    FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                    FirebaseCrash.report(e);
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public interface AdapterCallback {
        void onRowClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.informasi_judul)
        TextView tvName;
        @BindView(R.id.informasi_kategori)
        TextView tvDesc;
        @BindView(R.id.informasi_excerpt)
        TextView excerpt;
        @BindView(R.id.informasi_created_at)
        TextView tanggal;
        @BindView(R.id.informasifoto)
        ImageView gambar;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = itemView.getContext();


        }
    }
}