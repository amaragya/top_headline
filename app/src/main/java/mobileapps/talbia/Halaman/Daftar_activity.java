package mobileapps.talbia.Halaman;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

public class Daftar_activity extends AppCompatActivity {


    @BindView(R.id.nama_lengkap)
    TextInputEditText nama_lengkap;


    @BindView(R.id.tgl_lahir)
    TextInputEditText tgl_lahir;


    @BindView(R.id.email)
    TextInputEditText email;


    @BindView(R.id.hp)
    TextInputEditText no_hp;


    @BindView(R.id.jenis_kelamin)
    Spinner jenis_kelamin;

    @BindView(R.id.password)
    TextInputEditText password;

    @BindView(R.id.eye_password)
    ImageView eye_password;


    @BindView(R.id.confirm_password)
    TextInputEditText confirm_password;

    @BindView(R.id.eye_password_confirm)
    ImageView eye_password_confirm;

    @BindView(R.id.daftar_akun)
    Button daftar;
    Boolean passwordterlihat = false;
    Boolean confirm_passwordterlihat = false;
    ProgressDialog pb;
    Boolean bolehinput = false;
    private ApiInterface apiInterface;
    private boolean ada_di_dpt = false;
    private int year;
    private int month;
    private int day;
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    @OnClick(R.id.kembali_daftar)
    public void kembali() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_activity);
        ButterKnife.bind(this);


        pb = new ProgressDialog(this);
        pb.setMessage("Mohon Tunggu!");
        pb.setCancelable(false);


        ArrayList<String> jk = new ArrayList();
        jk.add("Pilih Jenis Kelamin");
        jk.add("Pria");
        jk.add("Wanita");


        ArrayAdapter adapterjk = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, jk);
        jenis_kelamin.setAdapter(adapterjk);


        apiInterface = BaseApiService.getAPIService();

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


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        tgl_lahir.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(0);
                return false;
            }
        });


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


        password.addTextChangedListener(new TextWatcher() {
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


        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (jenis_kelamin.getSelectedItem().toString().equals("Pilih Jenis Kelamin")) {
                    bolehinput = false;
                    Toast.makeText(Daftar_activity.this, "Silahkan Pilih Input Jenis Kelamin!", Toast.LENGTH_SHORT).show();
                } else {
                    bolehinput = true;
                }

                if (nama_lengkap.getText().toString().equals("")) {
                    nama_lengkap.setError("Wajib Diisi!");
                    bolehinput = false;
                } else {
                    nama_lengkap.setError(null);
                    bolehinput = true;
                }

                if (email.getText().toString().equals("")) {
                    email.setError("Wajib Diisi!");
                    bolehinput = false;
                } else {
                    email.setError(null);
                    bolehinput = true;
                }

                if (no_hp.getText().toString().equals("")) {
                    no_hp.setError("Wajib Diisi!");
                    bolehinput = false;
                } else {
                    no_hp.setError(null);
                    bolehinput = true;
                }


                if (password.getText().toString().equals("")) {
                    password.setError("Wajib Diisi!");
                    bolehinput = false;
                } else {
                    password.setError(null);
                    bolehinput = true;
                }


                if (tgl_lahir.getText().toString().equals("")) {
                    tgl_lahir.setError("Wajib Diisi!");
                    bolehinput = false;
                } else {
                    tgl_lahir.setError(null);
                    bolehinput = true;
                }

                cek_password();


                if (bolehinput) {
//    Toast.makeText(Daftar_activity.this, bolehinput.toString(), Toast.LENGTH_SHORT).show();
                    daftar();
                }

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        return new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                myDateListener, 2000, month, day);

    }

    private void showDate(int year, int month, int day) {

        tgl_lahir.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));

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

    public void daftar() {
        pb.show();
        apiInterface.daftar(
                email.getText().toString(),
                password.getText().toString(),
                nama_lengkap.getText().toString(),
                jenis_kelamin.getSelectedItem().toString(),
                no_hp.getText().toString(),
                tgl_lahir.getText().toString()
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


                                Toast.makeText(getApplicationContext(), "Berhasil Daftar! Silahkan login kembali!", Toast.LENGTH_SHORT).show();
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


    @OnClick(R.id.kembali_daftar)
    public void kembali_daftar() {
        finish();
    }
}
