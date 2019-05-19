package mobileapps.talbia.pemesanan;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
import mobileapps.talbia.Utility.AgeCalculation;
import mobileapps.talbia.Utility.utility;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static mobileapps.talbia.Utility.Constant.MyPREFERENCES;

public class Umroh extends AppCompatActivity {


    @BindView(R.id.baju_s)
    public RadioButton baju_s_v;
    @BindView(R.id.linear_form_umroh)
    LinearLayout lfu;
    @BindView(R.id.no_registrasi)
    TextInputEditText no_registrasi;

    @BindView(R.id.nama_counter)
    TextInputEditText nama_counter;

    @BindView(R.id.nama_lengkap)
    TextInputEditText nama_lengkap;

    @BindView(R.id.nama_panggilan)
    TextInputEditText nama_panggilan;


    @BindView(R.id.status)
    TextInputEditText status;


    @BindView(R.id.tempat_lahir)
    TextInputEditText tempat_lahir;


    @BindView(R.id.tgl_lahir)
    TextInputEditText tgl_lahir;


    @BindView(R.id.usia)
    TextInputEditText usia;

    @BindView(R.id.radio_jk)
    RadioGroup radio_jk;

    @BindView(R.id.jk_pria)
    RadioButton jk_pria;

    @BindView(R.id.jk_wanita)
    RadioButton jk_wanita;

    @BindView(R.id.telepon_rumah)
    TextInputEditText telepon_rumah;

    @BindView(R.id.hp)
    TextInputEditText hp;

    @BindView(R.id.nama_kantor)
    TextInputEditText nama_kantor;


    @BindView(R.id.alamat_kantor)
    TextInputEditText alamat_kantor;

    @BindView(R.id.telepon_kantor)
    TextInputEditText telepon_kantor;

    @BindView(R.id.fax_kantor)
    TextInputEditText fax_kantor;

    @BindView(R.id.email_kantor)
    TextInputEditText email_kantor;

    @BindView(R.id.provinsi)
    TextInputEditText provinsi;

    @BindView(R.id.rt)
    TextInputEditText rt;

    @BindView(R.id.rw)
    TextInputEditText rw;

    @BindView(R.id.kelurahan)
    TextInputEditText kelurahan;

    @BindView(R.id.kecamatan)
    TextInputEditText kecamatan;

    @BindView(R.id.kota)
    TextInputEditText kota;


    @BindView(R.id.alamat)
    TextInputEditText alamat;

    @BindView(R.id.kode_pos)
    TextInputEditText kode_pos;

    @BindView(R.id.tanggal_keberangkatan)
    TextInputEditText tanggal_keberangkatan;
    @BindView(R.id.radio_baju)
    RadioGroup radio_baju;
    private String darah;
    private int year;
    private int month;
    private int day;
    private String baju;
    private String charge_1 = "tidak";
    private String charge_2 = "tidak";
    private TextInputEditText required_edittext;
    private AgeCalculation age = new AgeCalculation();
    private String program_paket;
    private String program;
    private ApiInterface apiInterface;
    private JSONObject jsonobjek;
    private String id_member;
    private SharedPreferences sharedreferences;
    private ProgressDialog pb;
    private String program_paket_triple = "tidak";
    private String program_paket_double = "tidak";
    private String program_paket_quard = "tidak";
    private String program_lailatul_qodr = "tidak";
    private String program_plus_andalusia = "tidak";
    private String program_reguler = "tidak";
    private String program_plus_dubai = "tidak";
    private String program_plus_cairo = "tidak";
    private String program_plus_eropa = "tidak";
    private String program_awal_ramadhan = "tidak";
    private String program_idul_fitri = "tidak";
    private String program_nuzulul_quran = "tidak";
    private String program_plus_istanbul = "tidak";
    private String jenis_kelamin = "NULL";
    private utility utils;
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    @OnClick(R.id.baju_s)
    public void baju_s(View view) {
        baju = "s";
    }

    @OnClick(R.id.baju_m)
    public void baju_m(View view) {

        baju = "m";
    }

    @OnClick(R.id.baju_l)
    public void baju_l(View view) {

        baju = "l";
    }

    @OnClick(R.id.baju_xl)
    public void baju_xl(View view) {

        baju = "xl";
    }

    @OnClick(R.id.baju_3l)
    public void baju_3l(View view) {

        baju = "3l";
    }

    @OnClick(R.id.baju_4l)
    public void baju_4l(View view) {

        baju = "3l";
    }

    @OnClick(R.id.jk_pria)
    public void jk_pria_click() {
        jenis_kelamin = "pria";
    }

    @OnClick(R.id.jk_wanita)
    public void jk_wanita_click() {
        jenis_kelamin = "wanita";
    }

    @OnClick(R.id.program_reguler)
    public void program_reguler(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_reguler = "ya";
        } else {
            program_reguler = "tidak";
        }
    }

    @OnClick(R.id.program_dubai)
    public void program_dubai(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_plus_dubai = "ya";
        } else {
            program_plus_dubai = "tidak";
        }
    }

    @OnClick(R.id.program_awal_ramadhan)
    public void program_awal_ramadhan(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_awal_ramadhan = "ya";
        } else {
            program_awal_ramadhan = "tidak";
        }
    }

    @OnClick(R.id.program_idul_fitri)
    public void program_idul_fitri(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_idul_fitri = "ya";
        } else {
            program_idul_fitri = "tidak";
        }
    }

    @OnClick(R.id.program_plus_cairo)
    public void program_plus_cairo(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_plus_cairo = "ya";
        } else {
            program_plus_cairo = "tidak";
        }
    }

    @OnClick(R.id.program_plus_eropa)
    public void program_plus_eropa(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_plus_eropa = "ya";
        } else {
            program_plus_eropa = "tidak";
        }
    }

    @OnClick(R.id.program_nuzulul_quran)
    public void program_nuzulul_quran(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_nuzulul_quran = "ya";
        } else {
            program_nuzulul_quran = "tidak";
        }
    }

    @OnClick(R.id.program_plus_istanbul)
    public void program_plus_istanbul(View v) {
        CheckBox data = (CheckBox) v;

        if (data.isChecked()) {
            program_plus_istanbul = "ya";
        } else {
            program_plus_istanbul = "tidak";
        }
    }

    @OnClick(R.id.program_plus_andalusia)
    public void program_plus_andalusia(View v) {
        CheckBox data = (CheckBox) v;
//
        if (data.isChecked()) {
            program_plus_andalusia = "ya";
        } else {
            program_plus_andalusia = "tidak";
        }
    }

    @OnClick(R.id.program_lailatul_qodr)
    public void program_lailatul_qodr(View v) {
        CheckBox data = (CheckBox) v;
        if (data.isChecked()) {
            program_lailatul_qodr = "ya";
        } else {
            program_lailatul_qodr = "tidak";
        }
    }

    @OnClick(R.id.program_1)
    public void program_1(View v) {
        CheckBox data = (CheckBox) v;
        if (data.isChecked()) {
            program_paket_double = "ya";
        } else {
            program_paket_double = "tidak";
        }
    }

    @OnClick(R.id.program_2)
    public void program_2(View v) {
        CheckBox data = (CheckBox) v;
        if (data.isChecked()) {
            program_paket_triple = "ya";
        } else {
            program_paket_triple = "tidak";
        }
    }

    @OnClick(R.id.program_3)
    public void program_3(View v) {
        CheckBox data = (CheckBox) v;
        if (data.isChecked()) {
            program_paket_quard = "ya";
        } else {
            program_paket_quard = "tidak";
        }
    }

    @OnClick(R.id.charge_1)
    public void charge_1(View view) {
        CheckBox data = (CheckBox) view;
        if (data.isChecked()) {
            charge_1 = "ya";
        } else {
            charge_2 = "tidak";
        }
    }

    @OnClick(R.id.charge_2)
    public void charge_2(View view) {
        CheckBox data = (CheckBox) view;
        if (data.isChecked()) {
            charge_2 = "ya";
        } else {
            charge_2 = "tidak";
        }
    }

    @OnClick(R.id.kirim_data)
    public void click_kirimdata(View view) {
        if (validasi_form()) {
            try {
                kirim_data();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean validasi_form() {

        Boolean hasil = false;

        if (nama_lengkap.getText().toString().isEmpty()) {
            hasil = false;
            nama_lengkap.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            nama_lengkap.setError(null);
        }

        if (nama_panggilan.getText().toString().isEmpty()) {
            hasil = false;
            nama_panggilan.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            nama_panggilan.setError(null);
        }

        if (status.getText().toString().isEmpty()) {
            hasil = false;
            status.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            status.setError(null);
        }

        if (tempat_lahir.getText().toString().isEmpty()) {
            hasil = false;
            tempat_lahir.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            tempat_lahir.setError(null);
        }

        if (tgl_lahir.getText().toString().isEmpty()) {
            hasil = false;
            tgl_lahir.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            tgl_lahir.setError(null);
        }

        if (hp.getText().toString().isEmpty()) {
            hasil = false;
            hp.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            hp.setError(null);
        }

        if (nama_kantor.getText().toString().isEmpty()) {
            hasil = false;
            nama_kantor.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            nama_kantor.setError(null);
        }

        if (alamat_kantor.getText().toString().isEmpty()) {
            hasil = false;
            alamat_kantor.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            alamat_kantor.setError(null);
        }

        if (email_kantor.getText().toString().isEmpty()) {
            hasil = false;
            email_kantor.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            email_kantor.setError(null);
        }

        if (provinsi.getText().toString().isEmpty()) {
            hasil = false;
            provinsi.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            provinsi.setError(null);
        }

        if (rt.getText().toString().isEmpty()) {
            hasil = false;
            rt.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            rt.setError(null);
        }

        if (rw.getText().toString().isEmpty()) {
            hasil = false;
            rw.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            rw.setError(null);
        }

        if (kelurahan.getText().toString().isEmpty()) {
            hasil = false;
            kelurahan.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            kelurahan.setError(null);
        }

        if (kecamatan.getText().toString().isEmpty()) {
            hasil = false;
            kecamatan.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            kecamatan.setError(null);
        }

        if (kota.getText().toString().isEmpty()) {
            hasil = false;
            kota.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            kota.setError(null);
        }

        if (alamat.getText().toString().isEmpty()) {
            hasil = false;
            alamat.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            alamat.setError(null);
        }


        if (alamat.getText().toString().isEmpty()) {
            hasil = false;
            alamat.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            alamat.setError(null);
        }


        if (kode_pos.getText().toString().isEmpty()) {
            hasil = false;
            kode_pos.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            kode_pos.setError(null);
        }

        if (tanggal_keberangkatan.getText().toString().isEmpty()) {
            hasil = false;
            tanggal_keberangkatan.setError("Wajib Diisi! ");
        } else {
            hasil = true;
            tanggal_keberangkatan.setError(null);
        }


        int id_radio_jk = radio_jk.getCheckedRadioButtonId();
        if (id_radio_jk == -1) {
            hasil = false;
            jk_pria.setError("Pilih Salah Satu!");
        } else {
            hasil = true;
            jk_pria.setError(null);
        }


        int id_radio_baju = radio_baju.getCheckedRadioButtonId();
        if (id_radio_baju == -1) {
            hasil = false;
            baju_s_v.setError("Pilih Salah Satu!");
        } else {
            hasil = true;
            baju_s_v.setError(null);
        }
        return hasil;
    }

    public void pick_tgl_lahir() {

        showDialog(0);

    }

    private void kirim_data() throws ParseException {

        pb.show();
        id_member = sharedreferences.getString("id", "empty");

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id_member", createPartFromString(id_member));

        //DATA REGISTRASI
        map.put("no_registrasi", createPartFromString(no_registrasi.getText().toString()));
        map.put("nama_counter", createPartFromString(nama_counter.getText().toString()));

        //DATA PERSONAL
        map.put("nama_lengkap", createPartFromString(nama_lengkap.getText().toString()));
        map.put("nama_panggilan", createPartFromString(nama_panggilan.getText().toString()));
        map.put("tempat_lahir", createPartFromString(tempat_lahir.getText().toString()));
        map.put("tanggal_lahir", createPartFromString(utils.formattanggaldb(tgl_lahir.getText().toString())));
        map.put("status", createPartFromString(status.getText().toString()));
        map.put("usia", createPartFromString(usia.getText().toString()));
        map.put("jenis_kelamin", createPartFromString(jenis_kelamin));
        map.put("telepon_rumah", createPartFromString(telepon_rumah.getText().toString()));
        map.put("hp", createPartFromString(hp.getText().toString()));

        //DATA PEKERJAAAN
        map.put("nama_kantor", createPartFromString(nama_kantor.getText().toString()));
        map.put("alamat_kantor", createPartFromString(alamat_kantor.getText().toString()));
        map.put("telepon_kantor", createPartFromString(telepon_kantor.getText().toString()));
        map.put("email_kantor", createPartFromString(email_kantor.getText().toString()));
        map.put("fax_kantor", createPartFromString(fax_kantor.getText().toString()));

        //DATA ADMINISTRASI
        map.put("alamat_tinggal", createPartFromString(alamat.getText().toString()));
        map.put("kelurahan", createPartFromString(kelurahan.getText().toString()));
        map.put("kecamatan", createPartFromString(kecamatan.getText().toString()));
        map.put("kota", createPartFromString(kota.getText().toString()));
        map.put("provinsi", createPartFromString(provinsi.getText().toString()));
        map.put("kode_pos", createPartFromString(kode_pos.getText().toString()));
        map.put("rt", createPartFromString(rt.getText().toString()));
        map.put("rw", createPartFromString(rw.getText().toString()));

        map.put("ukuran_baju", createPartFromString(baju));

        //PROGRAM UMROH
        map.put("program_reguler", createPartFromString(program_reguler));
        map.put("program_plus_dubai", createPartFromString(program_plus_dubai));
        map.put("program_awal_ramadhan", createPartFromString(program_awal_ramadhan));
        map.put("program_idul_fitri", createPartFromString(program_idul_fitri));
        map.put("program_plus_cairo", createPartFromString(program_plus_cairo));
        map.put("program_plus_eropa", createPartFromString(program_plus_eropa));
        map.put("program_nuzulul_quran", createPartFromString(program_nuzulul_quran));
        map.put("program_plus_istanbul", createPartFromString(program_plus_istanbul));
        map.put("program_plus_andalusia", createPartFromString(program_plus_andalusia));
        map.put("program_lailatul_qodr", createPartFromString(program_lailatul_qodr));

        //program paket
        map.put("program_paket_double", createPartFromString(program_paket_double));
        map.put("program_paket_triple", createPartFromString(program_paket_triple));
        map.put("program_paket_quard", createPartFromString(program_paket_quard));

        map.put("tanggal_keberangkatan", createPartFromString(tanggal_keberangkatan.getText().toString()));

        //CHarge
        map.put("charge_1", createPartFromString(charge_1));
        map.put("charge_2", createPartFromString(charge_2));


        apiInterface.kirim_data_umroh(map)
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

                    }

                    @Override
                    public void onComplete() {
                        pb.dismiss();
                        try {

                            if (jsonobjek.getString("status").equals("ok")) {
//                                JSONObject datamember = jsonobjek.getJSONObject("result");

                                Toast.makeText(getApplicationContext(), "Data berhasil dikirim! kami akan segera menghubungi anda", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), jsonobjek.getString("alasan"), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Gagal ! silahkan coba lagi" + e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                });


//        Toast.makeText(this, "Data Berhasil Dikirim!", Toast.LENGTH_SHORT).show();
    }

    public void pick_status() {
        final String[] colors = {"Menikah", "Belum Menikah"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(Umroh.this);
        builder.setTitle("Pilih Status");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialog.cancel();
                status.setText(colors[which]);

            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umroh);

        try {
            ButterKnife.bind(this);


            getSupportActionBar().setTitle(Html.fromHtml("<small>Form Pemesanan Program Umrah</small"));

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            sharedreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
            apiInterface = BaseApiService.getAPIService();
            pb = new ProgressDialog(this);
            pb.setCancelable(false);
            pb.setMessage("Mohon Tunggu!");


            utils = new utility();
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);


            status.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    try {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            pick_status();
                        }

                    } catch (Exception e) {
                        Toast.makeText(Umroh.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });

            tgl_lahir.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pick_tgl_lahir();
                    return false;
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Opps! sepertinya ada yang salah", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        return new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                myDateListener, 2000, month, day);

    }

    private void showDate(int year, int month, int day) {
        tgl_lahir.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));
        usia.setText(getAge(year, month, day));
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


//fungsi getage

    private String getAge(int year, int month, int day) {
        String birthDate = day + "-" + month + "-" + year;
        Date currentDate = new Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        ;
        Date date = null;     //birthDate is a String, in format dd-MM-yyyy
        try {
            date = (Date) formatter.parse(birthDate);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        double diff = currentDate.getTime() - date.getTime();

        double d = (1000 * 60 * 60 * 24 * 365.25);


        long years = Math.round(diff / 86400000 / 365);


        int umurage = (int) years;


        age.setDateOfBirth(year, month, day);
        age.calcualteYear();
        age.calcualteMonth();
        age.calcualteDay();


        int daysBetween = Days.daysBetween(new DateTime(currentDate), new DateTime(date)).getDays();


        return String.valueOf(Math.abs(daysBetween / 365));

    }


    //createpartfromstring function

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }


}
