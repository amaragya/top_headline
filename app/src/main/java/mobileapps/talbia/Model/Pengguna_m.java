package mobileapps.talbia.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengguna_m {

    @SerializedName("berat_badan")
    @Expose
    private String beratBadan;

    @SerializedName("namalengkap")
    @Expose
    private String namalengkap;

    @SerializedName("status_player")
    @Expose
    private String statusPlayer;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("kata_sandi")
    @Expose
    private String kataSandi;

    @SerializedName("block_user")
    @Expose
    private String blockUser;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("google_auth_key")
    @Expose
    private String googleAuthKey;

    @SerializedName("club")
    @Expose
    private String club;

    @SerializedName("data_lengkap")
    @Expose
    private String dataLengkap;

    @SerializedName("tinggi_badan")
    @Expose
    private String tinggiBadan;

    @SerializedName("id")
    private int id;

    @SerializedName("primary_position")
    @Expose
    private String primaryPosition;

    @SerializedName("domisili")
    @Expose
    private String domisili;

    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;

    @SerializedName("secondary_position")
    @Expose
    private String secondaryPosition;

    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggalLahir;

    @SerializedName("email")
    @Expose
    private String email;

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getStatusPlayer() {
        return statusPlayer;
    }

    public void setStatusPlayer(String statusPlayer) {
        this.statusPlayer = statusPlayer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String getBlockUser() {
        return blockUser;
    }

    public void setBlockUser(String blockUser) {
        this.blockUser = blockUser;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGoogleAuthKey() {
        return googleAuthKey;
    }

    public void setGoogleAuthKey(String googleAuthKey) {
        this.googleAuthKey = googleAuthKey;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getDataLengkap() {
        return dataLengkap;
    }

    public void setDataLengkap(String dataLengkap) {
        this.dataLengkap = dataLengkap;
    }

    public String getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(String tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimaryPosition() {
        return primaryPosition;
    }

    public void setPrimaryPosition(String primaryPosition) {
        this.primaryPosition = primaryPosition;
    }

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getSecondaryPosition() {
        return secondaryPosition;
    }

    public void setSecondaryPosition(String secondaryPosition) {
        this.secondaryPosition = secondaryPosition;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                "Pengguna_m{" +
                        "berat_badan = '" + beratBadan + '\'' +
                        ",namalengkap = '" + namalengkap + '\'' +
                        ",status_player = '" + statusPlayer + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",kata_sandi = '" + kataSandi + '\'' +
                        ",block_user = '" + blockUser + '\'' +
                        ",foto = '" + foto + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",google_auth_key = '" + googleAuthKey + '\'' +
                        ",club = '" + club + '\'' +
                        ",data_lengkap = '" + dataLengkap + '\'' +
                        ",tinggi_badan = '" + tinggiBadan + '\'' +
                        ",id = '" + id + '\'' +
                        ",primary_position = '" + primaryPosition + '\'' +
                        ",domisili = '" + domisili + '\'' +
                        ",jenis_kelamin = '" + jenisKelamin + '\'' +
                        ",secondary_position = '" + secondaryPosition + '\'' +
                        ",tanggal_lahir = '" + tanggalLahir + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }
}