package mobileapps.talbia.Model;

import com.google.gson.annotations.SerializedName;

public class Layanan_m {

    @SerializedName("id_kategori_layanan")
    private int idKategoriLayanan;

    @SerializedName("foto")
    private Object foto;

    @SerializedName("status_tampil")
    private String statusTampil;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("konten")
    private String konten;

    @SerializedName("kategori_layanan")
    private String kategoriLayanan;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("judul")
    private String judul;

    public int getIdKategoriLayanan() {
        return idKategoriLayanan;
    }

    public void setIdKategoriLayanan(int idKategoriLayanan) {
        this.idKategoriLayanan = idKategoriLayanan;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public String getStatusTampil() {
        return statusTampil;
    }

    public void setStatusTampil(String statusTampil) {
        this.statusTampil = statusTampil;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getKategoriLayanan() {
        return kategoriLayanan;
    }

    public void setKategoriLayanan(String kategoriLayanan) {
        this.kategoriLayanan = kategoriLayanan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    @Override
    public String toString() {
        return
                "Layanan_m{" +
                        "id_kategori_layanan = '" + idKategoriLayanan + '\'' +
                        ",foto = '" + foto + '\'' +
                        ",status_tampil = '" + statusTampil + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",konten = '" + konten + '\'' +
                        ",kategori_layanan = '" + kategoriLayanan + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",judul = '" + judul + '\'' +
                        "}";
    }
}