package mobileapps.talbia.bimbingan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;

import mobileapps.talbia.Adapter.FiqihAdapter;
import mobileapps.talbia.Fragment.FiqihFragment;
import mobileapps.talbia.R;

public class Fiqih extends AppCompatActivity implements FiqihAdapter.AdapterCallback {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiqih);


        getSupportActionBar().setTitle(Html.fromHtml("<small>Fiqih </small"));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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


    @Override
    public void onRowClicked(int position) {

    }

    @Override
    public void onProgressDialogShow(String Pesan) {

        progressDialog.setMessage(Pesan);
        progressDialog.show();
    }

    @Override
    public void onProgressDialogClose() {
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            FiqihFragment ff = null;
            if (position == 0) {
                ff = FiqihFragment.newInstance("haji");
            } else if (position == 1) {
                ff = FiqihFragment.newInstance("umroh");
            }

            return ff;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
