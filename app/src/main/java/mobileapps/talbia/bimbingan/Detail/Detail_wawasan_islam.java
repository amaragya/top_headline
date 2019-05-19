package mobileapps.talbia.bimbingan.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobileapps.talbia.R;

public class Detail_wawasan_islam extends AppCompatActivity {


    @BindView(R.id.konten)
    WebView webview_konten;

    @BindView(R.id.image_konten)
    ImageView foto_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wawasan_islam);
        ButterKnife.bind(this);

        Intent inten = getIntent();

        String judul_doa = inten.getStringExtra("judul");
        String konten = inten.getStringExtra("konten");
        String foto = inten.getStringExtra("foto");


        getSupportActionBar().setTitle(Html.fromHtml("<center><small>" + judul_doa + "</small></center>"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String htmlText = "<html><body> %s </body></Html>";

        webview_konten.setInitialScale(200);
        webview_konten.getSettings().getJavaScriptEnabled();
        webview_konten.loadData(String.format(htmlText, konten), "text/html", "utf-8");


        String base_url = "http://talbia.decimatech.co.id/public/storage/";


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(getApplicationContext())
                .applyDefaultRequestOptions(options)
                .load(base_url + foto)
                .into(foto_img);

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
}
