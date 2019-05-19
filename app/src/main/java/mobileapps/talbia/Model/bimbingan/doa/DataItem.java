package mobileapps.talbia.Model.bimbingan.doa;


import com.google.gson.annotations.SerializedName;


public class DataItem {

    @SerializedName("file_audio")
    private String fileAudio;

    @SerializedName("status_tampil")
    private String statusTampil;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("id")
    private int id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("terjemahan")
    private String terjemahan;

    @SerializedName("isi")
    private String isi;

    public String getFileAudio() {
        return fileAudio;
    }

    public void setFileAudio(String fileAudio) {
        this.fileAudio = fileAudio;
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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "file_audio = '" + fileAudio + '\'' +
                        ",status_tampil = '" + statusTampil + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",jenis = '" + jenis + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",kategori = '" + kategori + '\'' +
                        ",id = '" + id + '\'' +
                        ",judul = '" + judul + '\'' +
                        ",terjemahan = '" + terjemahan + '\'' +
                        ",isi = '" + isi + '\'' +
                        "}";
    }
}