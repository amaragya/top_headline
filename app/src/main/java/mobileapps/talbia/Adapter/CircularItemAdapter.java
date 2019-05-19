package mobileapps.talbia.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jh.circularlist.CircularAdapter;

import java.util.ArrayList;

import mobileapps.talbia.R;

public class CircularItemAdapter extends CircularAdapter {

    private ArrayList<String> mItems;       // custom data, here we simply use string
    private LayoutInflater mInflater;
    private ArrayList<View> mItemViews;     // to store all list item

    public CircularItemAdapter(LayoutInflater inflater, ArrayList<String> items) {
        this.mItemViews = new ArrayList<>();
        this.mItems = items;
        this.mInflater = inflater;

        for (final String s : mItems) {
            View view = mInflater.inflate(R.layout.view_circular_item, null);
            TextView itemView = (TextView) view.findViewById(R.id.bt_item);
            itemView.setText(s);
            mItemViews.add(view);

//            Log.d("","aasfasfas");
        }
    }

    @Override
    public ArrayList<View> getAllViews() {
        return mItemViews;
    }

    @Override
    public int getCount() {
        return mItemViews.size();
    }

    @Override
    public View getItemAt(int i) {
        return mItemViews.get(i);
    }

    @Override
    public void removeItemAt(int i) {
        if (mItemViews.size() > 0) {
            // remove from view list
            mItemViews.remove(i);
            // this is necessary to call to notify change
            notifyItemChange();
        }
    }

    @Override
    public void addItem(View view) {
        // add to view list
        mItemViews.add(view);
        // // this is necessary to call to notify change
        notifyItemChange();
    }
}