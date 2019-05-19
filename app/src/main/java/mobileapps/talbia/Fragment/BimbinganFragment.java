package mobileapps.talbia.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mobileapps.talbia.R;
import mobileapps.talbia.bimbingan.Almasurat;
import mobileapps.talbia.bimbingan.Doa_main;
import mobileapps.talbia.bimbingan.Fiqih;
import mobileapps.talbia.bimbingan.JadwalShalatActivity;
import mobileapps.talbia.bimbingan.Sejarah_islam;
import mobileapps.talbia.bimbingan.Wawasan_dunia;

public class BimbinganFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public BimbinganFragment() {
        // Required empty public constructor
    }

    public static BimbinganFragment newInstance(String param1, String param2) {
        BimbinganFragment fragment = new BimbinganFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @OnClick(R.id.frame_jadwal_shalat)
    public void pergi_jadwal_shalat() {
        startActivity(new Intent(getContext(), JadwalShalatActivity.class));
    }

    @OnClick(R.id.frame_doa)
    public void pergi_doa() {
        startActivity(new Intent(getContext(), Doa_main.class));
    }

    @OnClick(R.id.frame_dzikir)
    public void pergi_dzikir() {
        startActivity(new Intent(getContext(), Fiqih.class));
    }

    @OnClick(R.id.frame_almasurat)
    public void pergi_almasurat() {
        startActivity(new Intent(getContext(), Almasurat.class));
    }

    @OnClick(R.id.frame_wawasan_dunia)
    public void pergi_wawasan_dunia() {
        startActivity(new Intent(getContext(), Wawasan_dunia.class));
    }

    @OnClick(R.id.frame_sejarah_islam)
    public void pergi_sejarah_islam() {
        startActivity(new Intent(getContext(), Sejarah_islam.class));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_bimbingan, container, false);
        ButterKnife.bind(this, rootview);

        Toolbar toolbar = rootview.findViewById(R.id.toolbar_top);
        TextView judul_actionbar = toolbar.findViewById(R.id.toolbar_title);

        judul_actionbar.setText("Bimbingan");

        return rootview;
    }
}
