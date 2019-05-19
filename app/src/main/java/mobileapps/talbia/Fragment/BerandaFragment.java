package mobileapps.talbia.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Adapter.InformasiAdapter;
import mobileapps.talbia.Adapter.LayananAdapter;
import mobileapps.talbia.Fragment.Detail.Detail_informasi;
import mobileapps.talbia.Model.Informasi_m;
import mobileapps.talbia.Model.Konfigurasi_m;
import mobileapps.talbia.Model.Layanan_m;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;

import static android.support.constraint.Constraints.TAG;

@SuppressLint("ValidFragment")
public class BerandaFragment extends Fragment implements InformasiAdapter.AdapterCallback, LayananAdapter.LayananAdapterCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.listInformasiberanda)
    RecyclerView listInformasi;
    @BindView(R.id.webview_beranda)
    WebView beranda_webview;
    InformasiAdapter mRepoAdapter;
    InformasiAdapter.AdapterCallback callback;
    List<Informasi_m> repoList = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private List<Layanan_m> listlayanan = new ArrayList<>();
    private LayananAdapter mLayananAdapter;
    private ApiInterface mApiService;
    private ArrayList<Konfigurasi_m> list_konfigurasi = new ArrayList<>();
    private String notel = "0123456789";
    private String email = "amaragya@gmail.com";
    private Konfigurasi_m konfigurasi_model;
    private String profile;

    public BerandaFragment() {

    }

    public static BerandaFragment newInstance(String param1, String param2) {
        BerandaFragment fragment = new BerandaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        mApiService = BaseApiService.getAPIService();
//        requestRepos("amaragya");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_beranda, container, false);
        ButterKnife.bind(this, rootview);

        return rootview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedIntanceState) {


        mRepoAdapter = new InformasiAdapter(getActivity(), repoList, this);


        listInformasi.setItemAnimator(new DefaultItemAnimator());
        listInformasi.setHasFixedSize(true);
        listInformasi.setAdapter(mRepoAdapter);


        mLayananAdapter = new LayananAdapter(getActivity(), listlayanan, this);

        ambildata_konfigurasi();


    }

    private void ambil_informasi() {
        mApiService.ambil_informasi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Informasi_m>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Informasi_m> responseRepos) {
                        for (int i = 0; i < responseRepos.size(); i++) {
                            Informasi_m doamodel = responseRepos.get(i);
                            repoList.add(doamodel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                        FirebaseCrash.report(e);
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                        mRepoAdapter.notifyDataSetChanged();

                    }
                });
    }

    @Override
    public void onRowClicked(int position) {

        try {
            Intent inten = new Intent(getContext(), Detail_informasi.class);

            inten.putExtra("judul", repoList.get(position).getTitle());
            inten.putExtra("konten", repoList.get(position).getBody());
            inten.putExtra("foto", String.valueOf(repoList.get(position).getImage()));

            startActivity(inten);

        } catch (Exception e) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
            e.printStackTrace();
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRowClicked_layanan(int position) {

        try {
            Intent inten = new Intent(getContext(), Detail_informasi.class);

            inten.putExtra("judul", listlayanan.get(position).getJudul());
            inten.putExtra("konten", listlayanan.get(position).getKonten());
            inten.putExtra("foto", String.valueOf(listlayanan.get(position).getFoto()));

            startActivity(inten);

        } catch (Exception e) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
            e.printStackTrace();
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void ambildata_konfigurasi() {

        try {
            mApiService.ambil_konfigurasi()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Konfigurasi_m>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Konfigurasi_m konfigurasi_m) {
                            try {
                                konfigurasi_model = konfigurasi_m;
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                            FirebaseCrash.report(e);
                            e.printStackTrace();
//                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            try {


                                String htmlText = "<html><body> %s </body></Html>";
                                beranda_webview.setInitialScale(200);
                                beranda_webview.getSettings().getJavaScriptEnabled();

                                beranda_webview.loadData(String.format(htmlText, konfigurasi_model.getData_profile()), "text/html", "utf-8");
//                                beranda_webview.loadUrl("http://www.youtube.com/");
                                beranda_webview.setWebViewClient(new WebViewClient());


                                ambil_informasi();
                            } catch (Exception t) {
                                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                                FirebaseCrash.report(t);
                                t.printStackTrace();
                                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
            e.printStackTrace();
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public interface cek_program_lain {
        void callback();
    }


}
