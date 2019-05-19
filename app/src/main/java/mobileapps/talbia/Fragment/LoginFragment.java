package mobileapps.talbia.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.crash.FirebaseCrash;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobileapps.talbia.Halaman.Daftar_activity;
import mobileapps.talbia.Halaman.Lupa_password;
import mobileapps.talbia.Network.ApiInterface;
import mobileapps.talbia.Network.BaseApiService;
import mobileapps.talbia.R;
import okhttp3.ResponseBody;

import static mobileapps.talbia.Utility.Constant.MyPREFERENCES;

@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Data login fb : ";
    private static final int RC_SIGN_IN = 9001;
    hasillogin hl;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.kata_sandi)
    TextInputEditText kata_sandi;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ApiInterface apiInterface;
    private JSONObject jsonobjek;
    private SharedPreferences sharedpreferences;
    private ProgressDialog pb;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;


    public LoginFragment(hasillogin hl) {
        // Required empty public constructor
        this.hl = hl;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getContext());
        AppEventsLogger.activateApp(getContext());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        apiInterface = BaseApiService.getAPIService();

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        pb = new ProgressDialog(getContext());
        pb.setCancelable(false);
        pb.setMessage("Mohon tunggu!");


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, rootview);

        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = rootview.findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.setFragment(this);


        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                Toast.makeText(getContext(), "oke mantap", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                FirebaseCrash.report(error);
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }

    @OnClick(R.id.go_daftar)
    public void go_daftar() {
        startActivity(new Intent(getActivity(), Daftar_activity.class));
    }

    @OnClick(R.id.go_lupa_password)
    public void go_lupa_password() {
        startActivity(new Intent(getActivity(), Lupa_password.class));

    }

    @OnClick(R.id.google_sign_in_button)
    public void login_google(View v) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.login)
    public void login() {
        pb.show();
        apiInterface.login(email.getText().toString(), kata_sandi.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            jsonobjek = new JSONObject(responseBody.string().toString()).getJSONObject("hasil");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        pb.dismiss();
                        try {

                            if (jsonobjek.getString("status").equals("ok")) {
                                JSONObject datamember = jsonobjek.getJSONObject("result");
                                sharedpreferences.edit()
                                        .putString("id", datamember.getString("id"))
                                        .putString("namalengkap", datamember.getString("namalengkap"))
                                        .putString("jenis_kelamin", datamember.getString("jenis_kelamin"))
                                        .putString("tanggal_lahir", datamember.getString("tanggal_lahir"))
                                        .putString("email", datamember.getString("email"))
                                        .putString("no_hp", datamember.getString("no_hp"))
                                        .putString("foto", datamember.getString("foto"))
                                        .putString("rememberme", "ya")
                                        .putString("login_method", "email")
                                        .apply();

                                Toast.makeText(getContext(), "Selamat Datang! " + datamember.getString("namalengkap"), Toast.LENGTH_LONG).show();
                                hl.sukseslogin();
                            } else {
                                Toast.makeText(getContext(), jsonobjek.getString("status"), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Gagal login ! silahkan coba lagi" + e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                });


    }

    private void handleFacebookAccessToken(AccessToken token) {
        pb.show();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

                e.printStackTrace();
                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                FirebaseCrash.report(e);
                // Google Sign In failed, update UI appropriately

                // ...
            }
        } else {

            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void displayProgressDialog() {
        pb.setMessage("Logging In.. Please wait...");
        pb.setIndeterminate(false);
        pb.setCancelable(false);
        pb.show();

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        displayProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Login Failed: ", Toast.LENGTH_SHORT).show();
                        }
                        hideProgressDialog();
                    }
                });
    }

    private void hideProgressDialog() {
        pb.dismiss();
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            sharedpreferences.edit()
                    .putString("id", user.getUid())
                    .putString("namalengkap", user.getDisplayName())
                    .putString("jenis_kelamin", "anon")
                    .putString("tanggal_lahir", "anon")
                    .putString("email", user.getEmail())
                    .putString("no_hp", user.getPhoneNumber())
                    .putString("foto", user.getPhotoUrl().toString())
                    .putString("rememberme", "ya")
                    .putString("login_method", "google")
                    .apply();

            Toast.makeText(getContext(), "Selamat Datang! " + user.getDisplayName(), Toast.LENGTH_LONG).show();

            hl.sukseslogin();
            pb.dismiss();

        }

    }

    public interface hasillogin {
        void sukseslogin();
    }


}
