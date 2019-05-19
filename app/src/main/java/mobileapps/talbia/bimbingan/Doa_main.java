package mobileapps.talbia.bimbingan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Adapter.DoaAdapter;
import mobileapps.talbia.Adapter.kategori_doa_ExpandableListAdapter;
import mobileapps.talbia.Model.bimbingan.doa.Doa_2_m;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;

public class Doa_main extends AppCompatActivity implements DoaAdapter.AdapterCallback {


    ApiInterface mApiService;
    @BindView(R.id.list_detail_pengujian)
    ExpandableListView listdoa;
    ProgressDialog progressDialog;
    List<Doa_2_m> repoList = new ArrayList<>();
    private ViewPager mViewPager;
    private String TAG = "data";
    private kategori_doa_ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_main);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(Doa_main.this);
        progressDialog.setCancelable(false);


        getSupportActionBar().setTitle(Html.fromHtml("<small>Do'a & Fiqih Fragment</small"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mApiService = BaseApiService.getAPIService();

        listAdapter = new kategori_doa_ExpandableListAdapter(this, repoList);
        listdoa.setAdapter(listAdapter);


        // Listview Group expanded listener
        listdoa.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (listdoa.isGroupExpanded(groupPosition + 1)) {
                    listdoa.collapseGroup(groupPosition + 1);
                } else if (listdoa.isGroupExpanded(groupPosition - 1)) {
                    listdoa.collapseGroup(groupPosition - 1);
                }

            }
        });


        ambildoa();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doa_main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        try {
            listAdapter.destroy();
        } catch (Exception e) {

        }
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (item.getItemId() == android.R.id.home) //The ID here is no longer R.id.home
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void ambildoa() {
        mApiService.ambil_doa("haji")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Doa_2_m>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Doa_2_m> responseRepos) {

                        for (int i = 0; i < responseRepos.size(); i++) {
                            repoList.add(responseRepos.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
//                        Toast.makeText(Doa_main.this, repoList.get(0).getData().get(1).toString(), Toast.LENGTH_SHORT).show();
                        listAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void onRowClicked(int position) {

    }

    @Override
    public void onProgressDialogShow(String Pesan) {

        progressDialog.setMessage(Pesan);
        progressDialog.show();
    }

    @Override
    public void onProgressDialogClose() {
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}
