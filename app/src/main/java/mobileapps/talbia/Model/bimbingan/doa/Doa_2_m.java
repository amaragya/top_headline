package mobileapps.talbia.Model.bimbingan.doa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doa_2_m {

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("kategori")
    private String kategori;

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


}