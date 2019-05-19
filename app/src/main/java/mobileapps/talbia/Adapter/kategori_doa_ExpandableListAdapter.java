package mobileapps.talbia.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobileapps.talbia.Model.bimbingan.Doa_m;
import mobileapps.talbia.Model.bimbingan.doa.Doa_2_m;
import mobileapps.talbia.R;

public class kategori_doa_ExpandableListAdapter extends BaseExpandableListAdapter {

    MediaPlayer mPlayer;
    @BindView(R.id.lblListHeader)
    TextView judulgroup;
    private Button btn;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;
    private Context _context;
    // child data in format of header title, child title
    private List<Doa_2_m> _listDataHeader; // header titles
    private String jenis_layout;
    private String audioUrl;
    private Doa_m doa_m;
    private TextView lblListHeader;
    private ImageView imgplaypause;
    private DoaAdapter mRepoAdapter;

    public kategori_doa_ExpandableListAdapter(Context context, List<Doa_2_m> list) {
        this._context = context;
        this._listDataHeader = list;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.kategori_doa_fragment_child_item, null);


//        final ExpandableListView listdoa = convertView.findViewById(R.id.list_detail_pengujian);


////        Toast.makeText(_context, String.valueOf(this._listDataHeader.get(groupPosition).getData().size()), Toast.LENGTH_SHORT).show();
//       doa_ExpandableListAdapter listAdapter = new doa_ExpandableListAdapter(_context,_listDataHeader.get(groupPosition).getData(),null);


//        listdoa.setAdapter(listAdapter);
////


        }


//        Toast.makeText(_context, String.valueOf(groupPosition), Toast.LENGTH_SHORT).show();
        RecyclerView rvRepos = convertView.findViewById(R.id.rvRepos);


        mRepoAdapter = new DoaAdapter(_context, this._listDataHeader.get(groupPosition).getData(), (DoaAdapter.AdapterCallback) _context);
        rvRepos.setLayoutManager(new LinearLayoutManager(_context));
        rvRepos.setItemAnimator(new DefaultItemAnimator());
        rvRepos.setHasFixedSize(true);
        rvRepos.setAdapter(mRepoAdapter);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition).getKategori();
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = _listDataHeader.get(groupPosition).getKategori();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.kategori_doa_group_item, null);
            ButterKnife.bind(this, convertView);


        }


        final ImageView plusbutton = convertView.findViewById(R.id.kategori_bab_handler);
        judulgroup.setText(headerTitle);


        if (isExpanded) {
            plusbutton.setImageDrawable(_context.getDrawable(R.drawable.minus_dropdown));
        } else {
            plusbutton.setImageDrawable(_context.getDrawable(R.drawable.plus_dropdown));
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void destroy() {

        mRepoAdapter.clear();
    }
}
