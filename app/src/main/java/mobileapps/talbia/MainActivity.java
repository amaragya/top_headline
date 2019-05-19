package mobileapps.talbia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import mobileapps.talbia.Fragment.BerandaFragment;
import mobileapps.talbia.Fragment.BimbinganFragment;
import mobileapps.talbia.Fragment.LayananFragment;
import mobileapps.talbia.Fragment.LoginFragment;
import mobileapps.talbia.Fragment.PemesananFragment;
import mobileapps.talbia.Fragment.ProfileFragment;

import static mobileapps.talbia.Utility.Constant.MyPREFERENCES;

public class MainActivity extends AppCompatActivity implements BerandaFragment.cek_program_lain, LoginFragment.hasillogin, ProfileFragment.callback {

    private SharedPreferences sharedpreferences;
    private TextView mTextMessage;
//
//    final Fragment fragment1 = new BerandaFragment();
//    final Fragment fragment2 = new LayananFragment();
//    final Fragment fragment3 = new PemesananFragment();
//    final Fragment fragment4 = new BimbinganFragment();


//    Fragment active = fragment1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_beranda:
                    final Fragment fragment1 = new BerandaFragment();

                    loadFragment(fragment1);
                    return true;
                case R.id.navigation_package:

                    final Fragment fragment2 = new LayananFragment();

                    loadFragment(fragment2);
                    return true;
                case R.id.navigation_form:
                    final Fragment fragment3;
                    if (!sharedpreferences.getString("rememberme", "empty").equals("ya")) {
                        fragment3 = new LoginFragment(MainActivity.this);
                    } else {
                        fragment3 = new PemesananFragment();
                    }

                    loadFragment(fragment3);
                    return true;
                case R.id.navigation_prayer:
                    final Fragment fragment4 = new BimbinganFragment();

                    loadFragment(fragment4);
                    return true;
                case R.id.navigation_profile:
                    final Fragment fragment5;
                    if (!sharedpreferences.getString("rememberme", "empty").equals("ya")) {
                        fragment5 = new LoginFragment(MainActivity.this);
                    } else {
                        fragment5 = new ProfileFragment(MainActivity.this);
                    }
                    loadFragment(fragment5);
                    return true;
            }
            return false;
        }
    };


    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        try {
            final Fragment fragment1 = new BerandaFragment();

            loadFragment(fragment1);
            navigation.setSelectedItemId(R.id.navigation_beranda);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void callback() {
        try {

            navigation.setSelectedItemId(R.id.navigation_package);
//    final Fragment fragment2 = new LayananFragment();
//
//    loadFragment(fragment2);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sukseslogin() {

        final Fragment fragment2 = new ProfileFragment(MainActivity.this);

        loadFragment(fragment2);
    }

    @Override
    public void onLogout() {


        final Fragment fragment2 = new LoginFragment(MainActivity.this);

        loadFragment(fragment2);

    }
}


class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }


}