package mobileapps.talbia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import mobileapps.talbia.Network.ApiInterface;

import static mobileapps.talbia.Utility.Constant.MyPREFERENCES;

public class Splashscreen extends AppCompatActivity {


    SharedPreferences sharedpreferences;
    private ApiInterface apiService;
    private ProgressDialog pb;
    private ImageView logo;
    private Button tombolupdate;
    private TextView textpesan;
    private String versionName;
    private int versionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


//
//
//        apiService = ApiClient.getClient().create(ApiInterface.class);
        logo = (ImageView) findViewById(R.id.logosplash);
        tombolupdate = (Button) findViewById(R.id.tombolupdate);
        textpesan = (TextView) findViewById(R.id.pesan);

        pb = new ProgressDialog(this);
        pb.setCancelable(false);

        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        versionNumber = pinfo.versionCode;
        versionName = pinfo.versionName;


        cekversi();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pergi_main();
//                cekversi();
            }
        }, 3000);

    }


    public void cekversi() {
        textpesan.setText("Versi : " + versionName);
        textpesan.setVisibility(View.VISIBLE);
        tombolupdate.setVisibility(View.GONE);

    }


    public void pergi_main() {

        Intent pergimain;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (!sharedpreferences.contains("rememberme")) {
//            pergimain = new Intent(getApplicationContext(),LoginActivity.class);
            pergimain = new Intent(getApplicationContext(), MainActivity.class);
        } else {
            pergimain = new Intent(getApplicationContext(), MainActivity.class);
        }

        startActivity(pergimain);

        finish();
    }

    public void force_update(JSONObject status) {
        try {
            textpesan.setText(status.getString("status") + "\nVersi yang digunakan : " + versionName);
            textpesan.setVisibility(View.VISIBLE);
            tombolupdate.setVisibility(View.VISIBLE);
            tombolupdate.setText("Update");
            tombolupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://inlab_basketball.com/apk/main.apk"));
                    startActivity(i);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void cobalagi() {
        textpesan.setText("Terjadi masalah jaringan!");
        tombolupdate.setText("Coba lagi");
        tombolupdate.setVisibility(View.VISIBLE);
        tombolupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekversi();
            }
        });
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);

    }


}
