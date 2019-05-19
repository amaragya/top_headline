package mobileapps.talbia.Model.bimbingan.shalat;


import com.google.gson.annotations.SerializedName;


public class TodayWeather {

    @SerializedName("temperature")
    private String temperature;

    @SerializedName("pressure")
    private int pressure;


    public TodayWeather(String temperature, int pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return
                "TodayWeather{" +
                        "temperature = '" + temperature + '\'' +
                        ",pressure = '" + pressure + '\'' +
                        "}";
    }
}