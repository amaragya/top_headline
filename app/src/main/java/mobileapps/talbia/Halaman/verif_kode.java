package mobileapps.talbia.Halaman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import butterknife.ButterKnife;
import mobileapps.talbia.R;

public class verif_kode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_kode);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(Html.fromHtml("<small>Verifikasi Kode</small"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
