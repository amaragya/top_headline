package mobileapps.talbia;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);
    private String stringToBetyped;
    private String stringToBetyped_angka;

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
        stringToBetyped_angka = "13";
    }

    @Test
    public void input_form_umroh() {
        onView(withId(R.id.navigation_form)).perform(click());
        onView(withId(R.id.req_umroh_promo)).perform(click());


        onView(withId(R.id.no_registrasi)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_counter)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_lengkap)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_panggilan)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.status)).perform(click());
        onView(withText("Menikah")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.tempat_lahir)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.tgl_lahir)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1998, 12, 12));
        onView(withText("Oke")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.jk_pria)).perform(click());
        onView(withId(R.id.telepon_rumah)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.hp)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.nama_kantor)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.alamat_kantor)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.telepon_kantor)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.fax_kantor)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.email_kantor)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.provinsi)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.rt)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.rw)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.kelurahan)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.kecamatan)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.kota)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.alamat)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.kode_pos)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.baju_l)).perform(click());

        onView(withId(R.id.kirim_data)).perform(scrollTo());

        onView(withId(R.id.program_reguler)).perform(click());
        onView(withId(R.id.program_dubai)).perform(click());
        onView(withId(R.id.program_awal_ramadhan)).perform(click());
        onView(withId(R.id.program_idul_fitri)).perform(click());
        onView(withId(R.id.program_plus_cairo)).perform(click());
        onView(withId(R.id.program_plus_eropa)).perform(click());
        onView(withId(R.id.program_nuzulul_quran)).perform(click());
        onView(withId(R.id.program_plus_istanbul)).perform(click());
        onView(withId(R.id.program_plus_andalusia)).perform(click());
        onView(withId(R.id.program_lailatul_qodr)).perform(click());

        onView(withId(R.id.program_1)).perform(click());
        onView(withId(R.id.program_2)).perform(click());
        onView(withId(R.id.program_3)).perform(click());

        onView(withId(R.id.tanggal_keberangkatan)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());

        onView(withId(R.id.charge_1)).perform(click());
        onView(withId(R.id.charge_2)).perform(click());
        onView(withId(R.id.kirim_data)).perform(click());
        pauseTestFor(50000);

//
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(stringToBetyped), closeSoftKeyboard());
    }


    @Test
    public void input_form_haji() {
        onView(withId(R.id.navigation_form)).perform(click());
        onView(withId(R.id.req_haji_khusus)).perform(click());


        onView(withId(R.id.no_registrasi)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_counter)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_lengkap)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_panggilan)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.status)).perform(click());
        onView(withText("Menikah")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.tempat_lahir)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.tgl_lahir)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1998, 12, 12));
        onView(withText("Oke")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.jk_pria)).perform(click());
        onView(withId(R.id.nama_ayah)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.nama_ibu)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.pendidikan_terakhir)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.telepon_rumah)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.hp)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());

        onView(withId(R.id.nama_kantor)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.alamat_kantor)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.telepon_kantor)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.fax_kantor)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.email_kantor)).perform(typeText(stringToBetyped), closeSoftKeyboard());

        onView(withId(R.id.berat_badan)).perform(scrollTo());
        onView(withId(R.id.provinsi)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.rt)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.rw)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.kelurahan)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.kecamatan)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.kota)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.alamat)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.kode_pos)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.berat_badan)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());
        onView(withId(R.id.tinggi_badan)).perform(typeText(stringToBetyped_angka), closeSoftKeyboard());

        onView(withId(R.id.charge_3)).perform(scrollTo());
        onView(withId(R.id.rambut_ikal)).perform(click());
        onView(withId(R.id.alis_tipis)).perform(click());
        onView(withId(R.id.hidung_mancung)).perform(click());
        onView(withId(R.id.muka_lonjong)).perform(click());
        onView(withId(R.id.darah_o)).perform(click());
        onView(withId(R.id.baju_l)).perform(click());
        onView(withId(R.id.kirim_data)).perform(scrollTo());


        onView(withId(R.id.charge_1)).perform(click());
        onView(withId(R.id.charge_2)).perform(click());
        onView(withId(R.id.charge_3)).perform(click());
        onView(withId(R.id.charge_4)).perform(click());
        onView(withId(R.id.charge_5)).perform(click());

        onView(withId(R.id.kirim_data)).perform(click());
        pauseTestFor(50000);

//
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(stringToBetyped), closeSoftKeyboard());
    }


    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
