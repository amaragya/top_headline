package mobileapps.talbia.Adapter;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobileapps.talbia.Model.bimbingan.Dzikir_m;
import mobileapps.talbia.R;

public class FiqihAdapter extends
        RecyclerView.Adapter<FiqihAdapter.ViewHolder> {

    private static final String TAG = FiqihAdapter.class.getSimpleName();

    private Context context;
    private List<Dzikir_m> list;
    private AdapterCallback mAdapterCallback;

    private Button btn;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;


    private FiqihAdapter.ViewHolder holdernya;
    private int currentposition = 0;
    private int yanglagiplay = -1;

    public FiqihAdapter(Context context, List<Dzikir_m> list, AdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa,
                parent, false);
        return new FiqihAdapter.ViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final FiqihAdapter.ViewHolder holder, final int position) {
        Dzikir_m item = list.get(position);

        String name = item.getJudul();
        final String konten = item.getIsi();
        String terjemahan = item.getTerjemahan();


        try {

            String audionya = item.getFileAudio();
            if (!audionya.isEmpty()) {
                final String audioUrl = "http://talbia.decimatech.co.id/public/storage/" + audionya;
                holder.audio_handler.setOnClickListener(new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        init_audio(holder, audioUrl, position);
                    }
                });
            } else {
                holder.audio_handler.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            holder.audio_handler.setVisibility(View.GONE);
        }


        holder.audio_handler.setImageDrawable(context.getDrawable(R.drawable.ic_play_circle_filled_black_24dp));

//        Toast.makeText(context, list.toString(), Toast.LENGTH_SHORT).show();
//        progressDialog = new ProgressDialog(context);


        holder.tvName.setText(name);


        String htmlText = "<html><body> %s </body></Html>";

//        webview_konten.setInitialScale(250);
        holder.webview_konten.getSettings().getJavaScriptEnabled();
//        webview_konten.getSettings().setBuiltInZoomControls(true);
//        webview_konten.getSettings().setDisplayZoomControls(false);
        holder.webview_konten.loadData(String.format(htmlText, konten), "text/html", "utf-8");


        holder.webview_terjemah.setInitialScale(200);
        holder.webview_terjemah.getSettings().getJavaScriptEnabled();
        holder.webview_terjemah.loadData(String.format(htmlText, terjemahan), "text/html", "utf-8");


        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                konten_detail_handler(holder, currentposition);
            }
        });

        holder.dropdown_handler.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                konten_detail_handler(holder, currentposition);
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void konten_detail_handler(FiqihAdapter.ViewHolder holder, int position) {
        try {
            this.holdernya = holder;
            this.currentposition = position;

            if (holder.konten_wrapper.getVisibility() == View.GONE) {
                holder.konten_wrapper.setVisibility(View.VISIBLE);
//                        mAdapterCallback.onRowClicked(position);
//                        Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show();
                holder.dropdown_handler.setImageDrawable(context.getDrawable(R.drawable.chevron_up));
            } else if (holder.konten_wrapper.getVisibility() == View.VISIBLE) {
                holder.konten_wrapper.setVisibility(View.GONE);
                holder.dropdown_handler.setImageDrawable(context.getDrawable(R.drawable.chevron_down));
            }
        } catch (Exception e) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(e);
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);

        initialStage = true;
        playPause = false;
        mediaPlayer.stop();
        mediaPlayer.reset();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_audio(final FiqihAdapter.ViewHolder holder, String audioUrl, int currentposition) {


        try {


            if (this.yanglagiplay != currentposition && this.yanglagiplay != -1) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

                this.initialStage = true;
                this.playPause = false;

                notifyItemChanged(this.yanglagiplay);

            }


            if (!this.playPause) {
                holder.audio_handler.setImageDrawable(context.getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));
                if (this.initialStage) {
                    this.mediaPlayer = new MediaPlayer();
                    this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }


                    mediaPlayer.setDataSource(audioUrl);
                    mAdapterCallback.onProgressDialogShow("Mohon Tunggu");
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mAdapterCallback.onProgressDialogClose();
                            mediaPlayer.start();
                            initialStage = false;
                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                                mediaPlayer.stop();
                                mediaPlayer.reset();
                            }
                            initialStage = true;
                            playPause = false;
                            holder.audio_handler.setImageDrawable(context.getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
                        }
                    });


                    this.yanglagiplay = currentposition;
                } else {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                }
                this.playPause = true;


//                Toast.makeText(context, String.valueOf(playPause), Toast.LENGTH_SHORT).show();


            } else {
                holder.audio_handler.setImageDrawable(context.getDrawable(R.drawable.ic_play_circle_filled_black_24dp));

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }


                this.playPause = false;
            }


        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public interface AdapterCallback {
        void onRowClicked(int position);

        void onProgressDialogShow(String Pesan);

        void onProgressDialogClose();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.audio_handler)
        ImageView audio_handler;


        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.konten)
        WebView webview_konten;
        @BindView(R.id.terjemahan)
        WebView webview_terjemah;
        @BindView(R.id.konten_wrapper)
        LinearLayout konten_wrapper;

        @BindView(R.id.dropdown_handler)
        ImageView dropdown_handler;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}