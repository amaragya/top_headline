package mobileapps.talbia.Model.bimbingan;

import com.google.gson.annotations.SerializedName;


public class Almasurat_m {

    @SerializedName("file_audio")
    private String fileAudio;

    @SerializedName("status_tampil")
    private String statusTampil;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_at")
    private String createdAt;

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
                "Doa_m{" +
                        "file_audio = '" + fileAudio + '\'' +
                        ",status_tampil = '" + statusTampil + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",judul = '" + judul + '\'' +
                        ",terjemahan = '" + terjemahan + '\'' +
                        ",isi = '" + isi + '\'' +
                        "}";
    }
}