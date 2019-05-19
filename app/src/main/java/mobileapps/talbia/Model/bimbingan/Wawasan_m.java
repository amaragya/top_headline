package mobileapps.talbia.Model.bimbingan;

public class Wawasan_m {
    private Object foto;
    private String updatedAt;
    private String konten;
    private String createdAt;
    private int id;
    private String judul;

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
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
                "Wawasan{" +
                        "foto = '" + foto + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",konten = '" + konten + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",judul = '" + judul + '\'' +
                        "}";
    }
}
