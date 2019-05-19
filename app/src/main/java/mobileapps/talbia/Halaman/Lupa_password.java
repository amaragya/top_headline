package mobileapps.talbia.Halaman;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;
import okhttp3.ResponseBody;

import static android.support.constraint.Constraints.TAG;

public class Lupa_password extends AppCompatActivity {


    @BindView(R.id.email_reset)
    TextInputEditText email_reset;
    @BindView(R.id.form_request_reset)
    CardView formrequest;
    @BindView(R.id.response_request_reset)
    CardView response_request;
    private ProgressDialog pb;
    private ApiInterface apiInterface;
    private JSONObject jsonobjek;

    @OnClick(R.id.kirim_data)
    public void request_reset_password(View v) {
        if (!email_reset.getText().toString().isEmpty()) {

            email_reset.setError(null);
            pb.show();
            apiInterface.request_reset_password(email_reset.getText().toString())
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
                            pb.dismiss();
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
            email_reset.setError("Wajib diisi!");
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
        setContentView(R.layout.activity_lupa_password);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(Html.fromHtml("<small>Lupa Password</small"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        response_request.setVisibility(View.GONE);

        apiInterface = BaseApiService.getAPIService();
        pb = new ProgressDialog(this);
        pb.setMessage("Mohon Tunggu");
        pb.setCancelable(false);

    }
}
