package amaragya.bahaso;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import amaragya.bahaso.Model.ArticlesItem;
import amaragya.bahaso.Model.Artikel_m;
import amaragya.bahaso.Network.ApiInterface;
import amaragya.bahaso.Network.BaseApiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final int VISIBLE_THRESHOLD = 1;
    ApiInterface mApiService;
    InformasiAdapter mRepoAdapter;
    boolean error, empty = false;
    @BindView(R.id.list_information)
    RecyclerView list_information;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.state)
    FrameLayout stateV;
    @BindView(R.id.shimmer_recycler_view)
    ShimmerRecyclerView loading_data;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    List<ArticlesItem> list_article = new ArrayList<>();
    @BindView(R.id.try_again)
    TextView try_again;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private Snackbar errorNotif;
    private int page = 1;
    private boolean loading = false;
    private int lastVisibleItem, totalItemCount;
    private LinearLayoutManager layoutManager;
    private int totalArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mApiService = BaseApiService.getAPIService();
        mRepoAdapter = new InformasiAdapter(this, list_article);
        errorNotif = Snackbar.make(container, "Problem with network", Snackbar.LENGTH_INDEFINITE).setAction("Reload", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                swipe_refresh.setRefreshing(true);
                stateV.setVisibility(View.GONE);

                ambildata();
            }
        });


        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                swipe_refresh.setRefreshing(true);
                list_information.setVisibility(View.GONE);
                stateV.setVisibility(View.GONE);


                list_article.clear();
                ambildata();
            }
        });

        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                swipe_refresh.setRefreshing(true);
                list_information.setVisibility(View.GONE);
                stateV.setVisibility(View.GONE);
                list_article.clear();
                ambildata();
            }
        });


        layoutManager = new LinearLayoutManager(this);
        list_information.setLayoutManager(layoutManager);
        list_information.setItemAnimator(new DefaultItemAnimator());
        list_information.setHasFixedSize(true);
        list_information.setAdapter(mRepoAdapter);

        loading_data.showShimmerAdapter();
        swipe_refresh.setRefreshing(true);
        list_information.setVisibility(View.GONE);
        stateV.setVisibility(View.GONE);


        ambildata();
        setUpLoadMoreListener();
    }


    private void setUpLoadMoreListener() {
        list_information.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD) && totalItemCount < totalArticle) {

                    page++;
                    loading = true;
                    progressBar.setVisibility(View.VISIBLE);
                    ambildata();
                }
            }
        });
    }


    private void ambildata() {
        mApiService.fetchData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Artikel_m>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Artikel_m response) {
                        if (response.getStatus().equals("ok")) {
                            if (response.getArticles().size() != 0) {

                                totalArticle = response.getTotalResults();
                                for (ArticlesItem articlesItem : response.getArticles()) {
                                    list_article.add(articlesItem);
                                }
                            } else {
                                empty = true;
                            }
                        } else {
                            error = true;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorNotif.show();
                        progressBar.setVisibility(View.GONE);
                        swipe_refresh.setRefreshing(false);
                        loading_data.hideShimmerAdapter();
                        loading = false;
                    }

                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                        swipe_refresh.setRefreshing(false);
                        loading_data.hideShimmerAdapter();
                        loading = false;

                        if (!error) {
                            if (!empty) {
                                stateV.setVisibility(View.GONE);
                                list_information.setVisibility(View.VISIBLE);
                                mRepoAdapter.notifyDataSetChanged();
                            } else {
                                list_information.setVisibility(View.GONE);
                                stateV.setVisibility(View.VISIBLE);
                            }
                        } else {
                            errorNotif.show();
                            list_information.setVisibility(View.GONE);
                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
            case R.id.cari:
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                break;
        }
        return true;
    }
}
