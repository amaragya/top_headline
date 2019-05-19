package mobileapps.talbia.Model;

import com.google.gson.annotations.SerializedName;

public class Konfigurasi_m {

    @SerializedName("email")
    private String email;

    @SerializedName("no_telepon")
    private String noTelepon;

    @SerializedName("data_profile")
    private String data_profile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    @Override
    public String toString() {
        return
                "Konfigurasi_m{" +
                        "email = '" + email + '\'' +
                        ",no_telepon = '" + noTelepon + '\'' +
                        "}";
    }

    public String getData_profile() {
        return data_profile;
    }

    public void setData_profile(String data_profile) {
        this.data_profile = data_profile;
    }
}