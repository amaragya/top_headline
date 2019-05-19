package mobileapps.talbia.Model.bimbingan.shalat;


import com.google.gson.annotations.SerializedName;


public class Jadwal_shalat {

    @SerializedName("asr")
    private String asr;

    @SerializedName("isha")
    private String isha;

    @SerializedName("shurooq")
    private String shurooq;

    @SerializedName("date_for")
    private String dateFor;

    @SerializedName("dhuhr")
    private String dhuhr;

    @SerializedName("fajr")
    private String fajr;

    @SerializedName("maghrib")
    private String maghrib;


    public Jadwal_shalat(String asr, String isha, String shurooq, String dateFor, String dhuhr, String fajr, String maghrib) {
        this.asr = asr;
        this.isha = isha;
        this.shurooq = shurooq;
        this.dateFor = dateFor;
        this.dhuhr = dhuhr;
        this.fajr = fajr;
        this.maghrib = maghrib;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getShurooq() {
        return shurooq;
    }

    public void setShurooq(String shurooq) {
        this.shurooq = shurooq;
    }

    public String getDateFor() {
        return dateFor;
    }

    public void setDateFor(String dateFor) {
        this.dateFor = dateFor;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    @Override
    public String toString() {
        return
                "Jadwal_shalat{" +
                        "asr = '" + asr + '\'' +
                        ",isha = '" + isha + '\'' +
                        ",shurooq = '" + shurooq + '\'' +
                        ",date_for = '" + dateFor + '\'' +
                        ",dhuhr = '" + dhuhr + '\'' +
                        ",fajr = '" + fajr + '\'' +
                        ",maghrib = '" + maghrib + '\'' +
                        "}";
    }
}