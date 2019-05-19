package mobileapps.talbia.bimbingan.Detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobileapps.talbia.R;

public class Detail_doa extends AppCompatActivity {


    MediaPlayer mPlayer;
    @BindView(R.id.konten)
    WebView webview_konten;
    @BindView(R.id.terjemahan)
    WebView webview_terjemah;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    private Button btn;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;
    private String audioUrl;
    private Menu menu;
    private ImageView mButtonPlay;

    @OnClick(R.id.back_halaman)
    public void kembali() {
        finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.audio_handler)
    public void audio_handler(View v) {
        mButtonPlay = (ImageView) v;

        init_audio();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_audio() {

        if (!playPause) {
            mButtonPlay.setImageDrawable(getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));

            if (initialStage) {
                new Player().execute(audioUrl);
            } else {
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
            }

            playPause = true;

        } else {
            mButtonPlay.setImageDrawable(getDrawable(R.drawable.ic_play_circle_filled_black_24dp));

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }

            playPause = false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doa);
        ButterKnife.bind(this);

        Intent inten = getIntent();

        String judul_doa = inten.getStringExtra("judul");
        String konten = inten.getStringExtra("konten");
        String terjemahan = inten.getStringExtra("terjemah");
//        audioUrl = "http://www.all-birds.com/Sound/western%20bluebird.wav";
        try {

            JSONArray jsonarray = new JSONArray(inten.getStringExtra("audio"));

            JSONObject jsonobjek = jsonarray.getJSONObject(0);
            audioUrl = "http://talbia.decimatech.co.id/public/storage/" + jsonobjek.getString("download_link");
//            Toast.makeText(this, audioUrl, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        toolbar_title.setText(judul_doa);

        String htmlText = "<html><body> %s </body></Html>";
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        webview_konten.setInitialScale(200);
        webview_konten.getSettings().getJavaScriptEnabled();
        webview_konten.loadData(String.format(htmlText, konten), "text/html", "utf-8");

        webview_terjemah.setInitialScale(200);

        webview_terjemah.getSettings().getJavaScriptEnabled();
//        webview_terjemah.getSettings().setBuiltInZoomControls(true);
//        webview_terjemah.getSettings().setDisplayZoomControls(false);
        webview_terjemah.loadData(String.format(htmlText, terjemahan), "text/html", "utf-8");


    }


    class Player extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        playPause = false;
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mButtonPlay.setImageDrawable(getDrawable(R.drawable.ic_play_circle_filled_black_24dp));

                    }
                });

                mediaPlayer.prepare();
                prepared = true;

            } catch (Exception e) {
                Log.e("MyAudioStreamingApp", e.getMessage());
                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }

            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Menyiapkan audio");
            progressDialog.show();
        }
    }


}
