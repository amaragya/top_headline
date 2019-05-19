package mobileapps.talbia.Halaman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jh.circularlist.CircularListView;
import com.jh.circularlist.CircularTouchListener;

import java.util.ArrayList;

import mobileapps.talbia.Adapter.CircularItemAdapter;
import mobileapps.talbia.R;

public class Layanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        ArrayList<String> itemTitles = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            itemTitles.add("LayananFragment " + String.valueOf(i));
        }

        CircularListView circularListView = (CircularListView) findViewById(R.id.my_circular_list);
        CircularItemAdapter adapter = new CircularItemAdapter(getLayoutInflater(), itemTitles);
        circularListView.setOnItemClickListener(new CircularTouchListener.CircularItemClickListener() {
            @Override
            public void onItemClick(View view, int index) {
            }
        });


        circularListView.setAdapter(adapter);


        View view = getLayoutInflater().inflate(R.layout.view_circular_item, null);
        TextView itemView = (TextView) view.findViewById(R.id.bt_item);
        itemView.setText(String.valueOf(adapter.getCount() + 1));

// add to list
//        adapter.addItem(view);


        circularListView.setRadius(25f);
    }


}
