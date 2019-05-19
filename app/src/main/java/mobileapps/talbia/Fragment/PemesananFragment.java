package mobileapps.talbia.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mobileapps.talbia.R;
import mobileapps.talbia.pemesanan.Haji;
import mobileapps.talbia.pemesanan.Umroh;

public class PemesananFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public PemesananFragment() {
        // Required empty public constructor
    }

    public static PemesananFragment newInstance(String param1, String param2) {
        PemesananFragment fragment = new PemesananFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_pemesanan, container, false);
        ButterKnife.bind(this, rootview);
        return rootview;
    }


//
//    @BindView(R.id.req_haji_khusus)
//    FrameLayout req_haji_khusus;
//
//    @BindView(R.id.req_umroh_promo)
//    FrameLayout req_umroh_promo;
//
//    @BindView(R.id.req_umroh_vip)
//    FrameLayout req_umroh_vip;
//
//    @BindView(R.id.req_umroh_reguler)
//    FrameLayout req_umroh_reguler;
//
//    @BindView(R.id.req_tour_eropa)
//    FrameLayout req_tour_eropa;
//
//    @BindView(R.id.req_tour_nusantara)
//    FrameLayout req_tour_nusantara;
//
//    @BindView(R.id.req_tour_asia)
//    FrameLayout req_tour_asia;
//
//    @BindView(R.id.req_tour_asean)
//    FrameLayout req_tour_asean;
//

    @OnClick(R.id.req_haji_khusus)
    public void pergihaji() {
        startActivity(new Intent(getContext(), Haji.class));
    }

    @OnClick(R.id.req_umroh_promo)
    public void pergiumroh() {
        startActivity(new Intent(getContext(), Umroh.class));
    }

    @OnClick(R.id.req_umroh_vip)
    public void pergiumroh_vip() {
        startActivity(new Intent(getContext(), Umroh.class));
    }

    @OnClick(R.id.req_umroh_reguler)
    public void pergi_umroh_reguler() {
        startActivity(new Intent(getContext(), Umroh.class));
    }

    @OnClick(R.id.req_tour_eropa)
    public void pergi_tour_eropa() {
        Toast.makeText(getContext(), "Fitur Belum tersedia!", Toast.LENGTH_SHORT).show();

//        startActivity(new Intent(getContext(),Tour.class));
    }

    @OnClick(R.id.req_tour_nusantara)
    public void pergi_tour_nusantara() {
        Toast.makeText(getContext(), "Fitur Belum tersedia!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getContext(),Tour.class));
    }

    @OnClick(R.id.req_tour_asia)
    public void pergi_tour_asia() {

        Toast.makeText(getContext(), "Fitur Belum tersedia!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getContext(),Tour.class));
    }

    @OnClick(R.id.req_tour_asean)
    public void pergi_tour_asean() {

        Toast.makeText(getContext(), "Fitur Belum tersedia!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getContext(),Tour.class));
    }


    @Override
    public void onViewCreated(View rootview, Bundle savedIntanceState) {


    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
