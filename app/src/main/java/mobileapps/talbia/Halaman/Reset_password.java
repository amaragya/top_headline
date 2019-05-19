package mobileapps.talbia.Halaman;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.MainActivity;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;
import okhttp3.ResponseBody;

import static android.support.constraint.Constraints.TAG;

public class Reset_password extends AppCompatActivity {


    @BindView(R.id.password)
    TextInputEditText password;

    @BindView(R.id.eye_password)
    ImageView eye_password;


    @BindView(R.id.confirm_password)
    TextInputEditText confirm_password;


    @BindView(R.id.eye_password_confirm)
    ImageView eye_password_confirm;
    Boolean passwordterlihat = false;
    Boolean confirm_passwordterlihat = false;
    Boolean bolehinput = false;
    @BindView(R.id.form_request_reset)
    CardView formrequest;
    @BindView(R.id.response_request_reset)
    CardView response_request;
    String token = "";
    private ProgressDialog pb;
    private ApiInterface apiInterface;
    private JSONObject jsonobjek;
    private boolean ada_di_dpt = false;
    private int year;
    private int month;
    private int day;
    private boolean passwordlamaterlihat = false;

    @OnClick(R.id.success_ok)
    public void ok_success() {
        startActivity(new Intent(Reset_password.this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.kirim_data)
    public void proses(View v) {
        if (!token.isEmpty()) {
            apiInterface.reset_password(password.getText().toString(),
                    token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {

                            try {
                                jsonobjek = new JSONObject(responseBody.string().toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                            FirebaseCrash.report(e);
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            pb.dismiss();
                            try {
                                if (jsonobjek.getString("status").equals("ok")) {
                                    response_request.setVisibility(View.VISIBLE);
                                    formrequest.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(getApplicationContext(), jsonobjek.getString("status"), Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                                FirebaseCrash.report(e);
                                Toast.makeText(getApplicationContext(), "Gagal login ! silahkan coba lagi" + e.toString(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Gagal! Silahkan coba lagi", Toast.LENGTH_SHORT).show();
        }
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(Html.fromHtml("<small>Reset Password</small"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        response_request.setVisibility(View.GONE);

        apiInterface = BaseApiService.getAPIService();
        pb = new ProgressDialog(this);
        pb.setMessage("Mohon Tunggu");
        pb.setCancelable(false);


        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        String[] datatoken = data.toString().split("/");
        token = datatoken[4];
//        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

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
