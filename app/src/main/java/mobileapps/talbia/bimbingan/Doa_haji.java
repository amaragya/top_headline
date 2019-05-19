package mobileapps.talbia.bimbingan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobileapps.talbia.Adapter.DoaAdapter;
import mobileapps.talbia.Adapter.doa_ExpandableListAdapter;
import mobileapps.talbia.Model.bimbingan.doa.DataItem;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;

@SuppressLint("ValidFragment")
public class Doa_haji extends Fragment implements doa_ExpandableListAdapter.detailtestInterface {

    private final List<DataItem> datadoa;
    ApiInterface mApiService;
//    DoaAdapter mRepoAdapter;
//
//    @BindView(R.id.rvRepos)
//    RecyclerView rvRepos;


    @BindView(R.id.list_detail_pengujian)
    ExpandableListView listdoa;

    DoaAdapter.AdapterCallback callback;

    private String TAG = "data";
    private doa_ExpandableListAdapter listAdapter;


    public Doa_haji(List<DataItem> data) {
        this.datadoa = data;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.activity_doa, container, false);


        ButterKnife.bind(this, rootview);

        mApiService = BaseApiService.getAPIService();

        listAdapter = new doa_ExpandableListAdapter(getContext(), datadoa, this);
        // setting list adapter
        listdoa.setAdapter(listAdapter);

        // Listview Group expanded listener
        listdoa.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != 4) {
                    if (listdoa.isGroupExpanded(groupPosition + 1)) {
                        listdoa.collapseGroup(groupPosition + 1);
                    } else if (listdoa.isGroupExpanded(groupPosition - 1)) {
                        listdoa.collapseGroup(groupPosition - 1);
                    }
                }
            }
        });


        return rootview;
    }


    @Override
    public void kirimdata(String value, String childid) {

    }
}
