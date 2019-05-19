package mobileapps.talbia.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.jh.circularlist.CircularListView;
import com.jh.circularlist.CircularTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Adapter.CircularItemAdapter;
import mobileapps.talbia.Fragment.Detail.Detail_layanan;
import mobileapps.talbia.Model.Layanan_m;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;

import static android.support.constraint.Constraints.TAG;

public class LayananFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public LayoutInflater layoutinflater;
    @BindView(R.id.bt_item)
    TextView bt_item;
    private String mParam1;
    private String mParam2;
    private CircularListView circularListView;
    private ArrayList<String> itemTitles;
    private ApiInterface apiInterface;
    private CircularItemAdapter adapter;
    private List<Layanan_m> listlayanan;

    public LayananFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        apiInterface = BaseApiService.getAPIService();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_layanan, container, false);
        ButterKnife.bind(this, rootview);

        itemTitles = new ArrayList<>();
        circularListView = (CircularListView) rootview.findViewById(R.id.my_circular_list);


        circularListView.setOnItemClickListener(new CircularTouchListener.CircularItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try {
                    Intent inten = new Intent(getContext(), Detail_layanan.class);


                    inten.putExtra("judul", listlayanan.get(position).getJudul());
                    inten.putExtra("konten", listlayanan.get(position).getKonten());
                    inten.putExtra("foto", String.valueOf(listlayanan.get(position).getFoto()));

                    startActivity(inten);

                } catch (Exception e) {
                    FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                    FirebaseCrash.report(e);

                    FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                    FirebaseCrash.report(e);
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        circularListView.setRadius(40f);

        ambildata();
        return rootview;
    }


    public void ambildata() {
        bt_item.setText("Mengambil Data");

        try {
            apiInterface.ambil_layanan()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Layanan_m>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<Layanan_m> layanan_ms) {
                            try {
                                listlayanan = layanan_ms;
                                for (int i = 0; i < layanan_ms.size(); i++) {
                                    Layanan_m layanan = layanan_ms.get(i);
                                    itemTitles.add(layanan.getJudul());
                                }
                            } catch (Exception e) {
                                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            try {
                                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                                FirebaseCrash.report(e);
//                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                bt_item.setText("Layanan Kami");
                            } catch (Exception t) {
                                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onComplete() {
                            try {
                                bt_item.setText("Layanan Kami");
                                adapter = new CircularItemAdapter(getActivity().getLayoutInflater(), itemTitles);
                                circularListView.setAdapter(adapter);
//                        Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                            } catch (Exception t) {
                                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                                FirebaseCrash.report(t);
                            }
                        }
                    });

        } catch (Exception e) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        layoutinflater = this.getLayoutInflater();


    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onViewCreated(View rootview, Bundle savedIntanceState) {
        super.onViewCreated(rootview, savedIntanceState);


    }

}
