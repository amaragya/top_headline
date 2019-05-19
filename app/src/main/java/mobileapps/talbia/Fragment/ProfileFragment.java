package mobileapps.talbia.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Halaman.Akun_setting;
import mobileapps.talbia.Halaman.Ganti_password;
import mobileapps.talbia.Model.Konfigurasi_m;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;

import static android.support.constraint.Constraints.TAG;
import static mobileapps.talbia.Utility.Constant.MyPREFERENCES;

@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    callback cb;
    @BindView(R.id.nama_lengkap)
    TextView namalengkap;
    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    private String mParam1;
    private String mParam2;
    private SharedPreferences sharedpreferences;
    private ApiInterface mApiService;
    private ArrayList<Konfigurasi_m> listlayanan = new ArrayList<>();
    private String notel = "0123456789";
    private String email = "amaragya@gmail.com";
    private Konfigurasi_m konfigurasi_model;


    public ProfileFragment(callback cb) {
        this.cb = cb;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mApiService = BaseApiService.getAPIService();

        ambildata_konfigurasi();
    }

    @OnClick(R.id.edit_biodata)
    public void edit_biodata() {
        startActivity(new Intent(getContext(), Akun_setting.class));
    }

    @OnClick(R.id.kritik_saran)
    public void kritik_saran() {

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");


        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kritik untuk fitur ....");

        startActivity(Intent.createChooser(emailIntent, "Pilih Aplikasi email untuk melanjutkan!"));

    }

    @OnClick(R.id.layanan_konsumen)
    public void layanan_konsumen() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + notel));
        startActivity(intent);
    }

    @OnClick(R.id.ubah_sandi)
    public void ubah_sandi() {
        startActivity(new Intent(getContext(), Ganti_password.class));
    }

    @OnClick(R.id.logout)
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        sharedpreferences.edit().clear().commit();
        LoginManager.getInstance().logOut();
        cb.onLogout();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootview);

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        namalengkap.setText(sharedpreferences.getString("namalengkap", "empty"));


        RequestOptions ro = new RequestOptions().error(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        Glide.with(getContext())
                .applyDefaultRequestOptions(ro)
                .load(sharedpreferences.getString("foto", "empty"))
                .into(profile_image);


        return rootview;
    }

    public void ambildata_konfigurasi() {

        try {
            mApiService.ambil_konfigurasi()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Konfigurasi_m>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Konfigurasi_m layanan_ms) {
                            try {
                                konfigurasi_model = layanan_ms;
                            } catch (Exception e) {
                                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            try {
                                email = konfigurasi_model.getEmail();
                                notel = konfigurasi_model.getNoTelepon();

                            } catch (Exception t) {
                                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                                FirebaseCrash.report(t);
                                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
        }
    }


    public interface callback {
        void onLogout();
    }


}
