package mobileapps.talbia.Halaman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
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
import mobileapps.talbia.Model.Informasi_m;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;
import mobileapps.talbia.bimbingan.Detail.Detail_doa;

public class Informasi extends AppCompatActivity implements InformasiAdapter.AdapterCallback {
    private static final String TAG = "data";
    ApiInterface mApiService;
    InformasiAdapter mRepoAdapter;

    @BindView(R.id.listInformasi)
    RecyclerView listInformasi;

    InformasiAdapter.AdapterCallback callback;

    List<Informasi_m> repoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        ButterKnife.bind(this);

        mApiService = BaseApiService.getAPIService();

        getSupportActionBar().setTitle(Html.fromHtml("<small>Kumpulan Do'a</small"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        inisialisasi adapter dan recyclerview
         */
        mRepoAdapter = new InformasiAdapter(this, repoList, this);
        listInformasi.setLayoutManager(new LinearLayoutManager(this));
        listInformasi.setItemAnimator(new DefaultItemAnimator());
        listInformasi.setHasFixedSize(true);
        listInformasi.setAdapter(mRepoAdapter);

        requestRepos("amaragya");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) //The ID here is no longer R.id.home
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestRepos(String username) {

        mApiService.ambil_informasi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Informasi_m>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Informasi_m> responseRepos) {
                        /*
                        onNext disini ketika data sudah masuk dan biasanya kita memasukan data API
                        ke lokal ataupun sesuai kebutuhan kamu. Di contoh ini data dari API Server dimasukan
                        dalam List repoList.
                         */
                        for (int i = 0; i < responseRepos.size(); i++) {
                            Informasi_m doamodel = responseRepos.get(i);
                            repoList.add(doamodel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Informasi.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(Informasi.this, "Berhasil mengambil data", Toast.LENGTH_SHORT).show();
                        mRepoAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void onRowClicked(int position) {
//
        try {
            Toast.makeText(getApplicationContext(), "Test 2", Toast.LENGTH_SHORT).show();
            Intent inten = new Intent(Informasi.this, Detail_doa.class);
            startActivity(inten);
        } catch (Exception e) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
