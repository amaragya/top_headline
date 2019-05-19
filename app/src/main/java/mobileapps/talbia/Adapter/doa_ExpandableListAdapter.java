package mobileapps.talbia.Adapter;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mobileapps.talbia.Model.bimbingan.doa.DataItem;
import mobileapps.talbia.R;

public class doa_ExpandableListAdapter extends BaseExpandableListAdapter {

    MediaPlayer mPlayer;
    private Button btn;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;
    private Context _context;
    private List<DataItem> _listDataHeader; // header titles
    // child data in format of header title, child title

    private String jenis_layout;

    private detailtestInterface listener;
    private String audioUrl;
    private DataItem doa_m;
    private TextView lblListHeader;
    private ImageView imgplaypause;

    public doa_ExpandableListAdapter(Context context, List<DataItem> list, detailtestInterface listener) {
        this._context = context;
        this._listDataHeader = list;
        this.listener = listener;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataHeader.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.detail_test_item_basic, null);


            Toast.makeText(_context, groupPosition, Toast.LENGTH_SHORT).show();
            doa_m = this._listDataHeader.get(groupPosition);

            WebView konten = convertView.findViewById(R.id.konten);
            WebView terjemah = convertView.findViewById(R.id.terjemahan);

            String htmlText = "<html><body> %s </body></Html>";


            konten.getSettings().getJavaScriptEnabled();
            konten.loadData(String.format(htmlText, doa_m.getIsi()), "text/html", "utf-8");


            terjemah.setInitialScale(200);
            terjemah.getSettings().getJavaScriptEnabled();
            terjemah.loadData(String.format(htmlText, doa_m.getTerjemahan()), "text/html", "utf-8");


        }

        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition).getJudul();
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = _listDataHeader.get(groupPosition).getJudul();

        progressDialog = new ProgressDialog(_context);

//        Toast.makeText(_context, _listDataHeader.get(1).getIsi(), Toast.LENGTH_SHORT).show();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.detail_test_group_item_basic, null);


            imgplaypause = convertView.findViewById(R.id.audio_handler);
            try {
                if (!doa_m.getFileAudio().equals("")) {

                    audioUrl = "http://talbia.decimatech.co.id/public/storage/" + doa_m.getFileAudio();

                    imgplaypause.setOnClickListener(new View.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {
                            init_audio();
                        }
                    });

                } else {
//    imgplaypause.setVisibility(View.GONE);
                }
            } catch (Exception e) {
//            imgplaypause.setVisibility(View.GONE);
            }

            lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
//        lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(String.valueOf(headerTitle));
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_audio() {

        try {
            if (!playPause) {
                imgplaypause.setImageDrawable(_context.getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));

                if (initialStage) {
                    new Player().execute(audioUrl);
                } else {
                    if (!mediaPlayer.isPlaying())
                        mediaPlayer.start();
                }
                playPause = true;

            } else {
                imgplaypause.setImageDrawable(_context.getDrawable(R.drawable.ic_play_circle_filled_black_24dp));

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

                playPause = false;
            }


        } catch (Exception e) {

        }
    }


    public interface detailtestInterface {
        void kirimdata(String value, String childid);
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
                        imgplaypause.setImageDrawable(_context.getDrawable(R.drawable.ic_play_circle_filled_black_24dp));

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

            try {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }

                mediaPlayer.start();
                initialStage = false;
            } catch (Exception e) {

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Menyiapkan audio");
            progressDialog.show();
        }
    }


}
