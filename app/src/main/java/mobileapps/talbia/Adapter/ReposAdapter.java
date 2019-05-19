package mobileapps.talbia.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobileapps.talbia.Model.Repo;
import mobileapps.talbia.R;

public class ReposAdapter extends
        RecyclerView.Adapter<mobileapps.talbia.Adapter.ReposAdapter.ViewHolder> {

    private static final String TAG = ReposAdapter.class.getSimpleName();

    private Context context;
    private List<Repo> list;
    private AdapterCallback mAdapterCallback;

    public ReposAdapter(Context context, List<Repo> list, AdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo,
                parent, false);
        return new ReposAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(mobileapps.talbia.Adapter.ReposAdapter.ViewHolder holder, final int position) {
        Repo item = list.get(position);

        String name = item.getName();
        String description = item.getDescription();

        holder.tvName.setText(name);
        holder.tvDesc.setText(description);

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                try {
//
//                    Intent inten = new Intent(context,Detail_doa.class);
//                    startActivity(inten);
                    mAdapterCallback.onRowClicked("1");
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
        void onRowClicked(String position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDesc)
        TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}