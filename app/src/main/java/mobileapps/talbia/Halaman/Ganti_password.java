package mobileapps.talbia.Halaman;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
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

public class Ganti_password extends AppCompatActivity {


    @BindView(R.id.password)
    TextInputEditText password;

    @BindView(R.id.eye_password)
    ImageView eye_password;


    @BindView(R.id.confirm_password)
    TextInputEditText confirm_password;


    @BindView(R.id.eye_password_confirm)
    ImageView eye_password_confirm;

    @BindView(R.id.password_lama)
    TextInputEditText password_lama;


    @BindView(R.id.eye_password_lama)
    ImageView eye_password_lama;
    Boolean passwordterlihat = false;
    Boolean confirm_passwordterlihat = false;
    ProgressDialog pb;
    Boolean bolehinput = false;
    private SharedPreferences sharedPreferences;
    private String id_pelanggan;
    private ApiInterface apiInterface;
    private boolean ada_di_dpt = false;
    private int year;
    private int month;
    private int day;
    private boolean passwordlamaterlihat = false;

    @OnClick(R.id.daftar_akun)
    public void proses_update_sandi() {


        if (password.getText().toString().equals("")) {
            password.setError("Wajib Diisi!");
            bolehinput = false;
        } else {
            password.setError(null);
            bolehinput = true;
        }


        if (password_lama.getText().toString().equals("")) {
            password_lama.setError("Wajib Diisi!");
            bolehinput = false;
        } else {
            password_lama.setError(null);
            bolehinput = true;
        }

        if (bolehinput) {

            update_sandi();
        }


    }

    @OnClick(R.id.kembali_daftar)
    public void kembali() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);
        ButterKnife.bind(this);

        pb = new ProgressDialog(this);
        pb.setMessage("Mohon Tunggu!");
        pb.setCancelable(false);


        apiInterface = BaseApiService.getAPIService();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        id_pelanggan = sharedPreferences.getString("id", "empty");


        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cek_password();
            }
        });


        eye_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordterlihat) {
                    passwordterlihat = false;
                    password.setTransformationMethod(null);
                    eye_password.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_black_24dp));

                } else {
                    passwordterlihat = true;
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    eye_password.setImageDrawable(getResources().getDrawable(R.drawable.eye_open));
                }
            }
        });

        eye_password_lama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordlamaterlihat) {
                    passwordlamaterlihat = false;
                    password_lama.setTransformationMethod(null);
                    eye_password_lama.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_black_24dp));

                } else {
                    passwordlamaterlihat = true;
                    password_lama.setTransformationMethod(new PasswordTransformationMethod());
                    eye_password_lama.setImageDrawable(getResources().getDrawable(R.drawable.eye_open));
                }
            }
        });

        eye_password_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirm_passwordterlihat) {
                    confirm_passwordterlihat = false;
                    confirm_password.setTransformationMethod(null);
                    eye_password_confirm.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_black_24dp));
                } else {
                    confirm_passwordterlihat = true;
                    confirm_password.setTransformationMethod(new PasswordTransformationMethod());
                    eye_password_confirm.setImageDrawable(getResources().getDrawable(R.drawable.eye_open));
                }
            }
        });


    }


    private void update_sandi() {
        pb.show();
        apiInterface.update_sandi(
                id_pelanggan,
                password_lama.getText().toString(),
                password.getText().toString()
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

                                Toast.makeText(getApplicationContext(), "Sandi berhasil di perbarui!", Toast.LENGTH_SHORT).show();
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


    public void cek_password() {
        if (confirm_password.getText().toString().equals(password.getText().toString()) && !password.getText().toString().equals("")) {
            bolehinput = true;
            confirm_password.setError(null);
        } else {
            bolehinput = false;
            confirm_password.setError("Password tidak sama");
        }
    }
}
