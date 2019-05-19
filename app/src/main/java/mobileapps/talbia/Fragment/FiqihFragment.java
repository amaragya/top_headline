package mobileapps.talbia.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Adapter.FiqihAdapter;
import mobileapps.talbia.Model.bimbingan.Dzikir_m;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;

public class FiqihFragment extends Fragment {

    private static final String TAG = "data";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ApiInterface mApiService;
    FiqihAdapter mRepoAdapter;
    @BindView(R.id.rvRepos)
    RecyclerView rvRepos;
    FiqihAdapter.AdapterCallback callback;
    List<Dzikir_m> repoList = new ArrayList<>();
    private String mParam1;


    public FiqihFragment() {

    }


    public static FiqihFragment newInstance(String param1) {
        FiqihFragment fragment = new FiqihFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        mApiService = BaseApiService.getAPIService();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_fiqih, container, false);
        ButterKnife.bind(this, rootview);

        return rootview;
    }


    @Override
    public void onViewCreated(View view, Bundle savedIntanceState) {


        mApiService = BaseApiService.getAPIService();

        mRepoAdapter = new FiqihAdapter(getActivity(), repoList, null);
        rvRepos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRepos.setItemAnimator(new DefaultItemAnimator());
        rvRepos.setHasFixedSize(true);
        rvRepos.setAdapter(mRepoAdapter);

        ambildzikir();
    }


    private void ambildzikir() {

        mApiService.ambil_dzikir(mParam1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Dzikir_m>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Dzikir_m> responseRepos) {

                        for (int i = 0; i < responseRepos.size(); i++) {
                            Dzikir_m doamodel = responseRepos.get(i);
                            repoList.add(doamodel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
//                        Toast.makeText(Doa.this, "Berhasil mengambil data", Toast.LENGTH_SHORT).show();
                        mRepoAdapter.notifyDataSetChanged();
                    }
                });
    }


}
