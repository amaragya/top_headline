package mobileapps.talbia.Halaman;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;
import okhttp3.ResponseBody;

import static mobileapps.talbia.Utility.Constant.MyPREFERENCES;

public class Akun_setting extends AppCompatActivity {


    @BindView(R.id.nama_lengkap)
    TextInputEditText nama_lengkap;


    @BindView(R.id.tgl_lahir)
    TextInputEditText tgl_lahir;


    @BindView(R.id.email)
    TextInputEditText email;


    @BindView(R.id.hp)
    TextInputEditText no_hp;


    @BindView(R.id.jenis_kelamin)
    TextView jenis_kelamin;
    private ProgressDialog pb;
    private ApiInterface apiInterface;
    private String id_pelanggan;
    private SharedPreferences sharedPreferences;


    @OnClick(R.id.kembali_daftar)
    public void kembali() {
        finish();
    }

    @OnClick(R.id.daftar_akun)
    public void updateakun() {
        update_akun();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_setting);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        id_pelanggan = sharedPreferences.getString("id", "empty");
        nama_lengkap.setText(sharedPreferences.getString("namalengkap", "empty"));
        tgl_lahir.setText(sharedPreferences.getString("tanggal_lahir", "empty"));
        email.setText(sharedPreferences.getString("email", "empty"));
        jenis_kelamin.setText(sharedPreferences.getString("jenis_kelamin", "empty"));
        no_hp.setText(sharedPreferences.getString("no_hp", "empty"));
        pb = new ProgressDialog(this);
        pb.setMessage("Mohon Tunggu!");
        pb.setCancelable(false);

        apiInterface = BaseApiService.getAPIService();
    }


    public void update_akun() {
        pb.show();
        apiInterface.update_akun(
                id_pelanggan,
                email.getText().toString(),
                no_hp.getText().toString(),
                nama_lengkap.getText().toString()
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            pb.dismiss();

                            JSONObject status = new JSONObject(responseBody.string());
                            if (status.getString("error").equals("tidak")) {

                                JSONObject datamember = status.getJSONObject("data");
                                sharedPreferences.edit()
                                        .putString("id", datamember.getString("id"))
                                        .putString("namalengkap", datamember.getString("namalengkap"))
                                        .putString("jenis_kelamin", datamember.getString("jenis_kelamin"))
                                        .putString("tanggal_lahir", datamember.getString("tanggal_lahir"))
                                        .putString("email", datamember.getString("email"))
                                        .putString("no_hp", datamember.getString("no_hp"))
                                        .putString("foto", datamember.getString("foto"))
                                        .putString("rememberme", "ya")
                                        .apply();
                                Toast.makeText(getApplicationContext(), "Data berhasil di perbarui!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), status.getString("alasan"), Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            pb.dismiss();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        pb.dismiss();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
