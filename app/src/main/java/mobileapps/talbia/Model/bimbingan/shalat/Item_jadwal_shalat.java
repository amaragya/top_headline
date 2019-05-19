package mobileapps.talbia.Model.bimbingan.shalat;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Item_jadwal_shalat {

    @SerializedName("country")
    private String country;

    @SerializedName("status_description")
    private String statusDescription;

    @SerializedName("address")
    private String address;

    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("method")
    private int method;

    @SerializedName("prayer_method_name")
    private String prayerMethodName;

    @SerializedName("city")
    private String city;

    @SerializedName("timezone")
    private int timezone;

    @SerializedName("query")
    private String query;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("for")
    private String jsonMemberFor;

    @SerializedName("link")
    private String link;

    @SerializedName("qibla_direction")
    private String qiblaDirection;

    @SerializedName("title")
    private String title;

    @SerializedName("status_valid")
    private int statusValid;

    @SerializedName("sealevel")
    private String sealevel;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("daylight")
    private int daylight;

    @SerializedName("today_weather")
    private TodayWeather todayWeather;

    @SerializedName("state")
    private String state;

    @SerializedName("postal_code")
    private String postalCode;

    @SerializedName("items")
    private List<Jadwal_shalat> items;

    @SerializedName("map_image")
    private String mapImage;

    @SerializedName("longitude")
    private String longitude;


    public Item_jadwal_shalat(String country, String statusDescription, String address, int statusCode, int method, String prayerMethodName, String city, int timezone, String query, String latitude, String jsonMemberFor, String link, String qiblaDirection, String title, int statusValid, String sealevel, String countryCode, int daylight, TodayWeather todayWeather, String state, String postalCode, List<Jadwal_shalat> items, String mapImage, String longitude) {
        this.country = country;
        this.statusDescription = statusDescription;
        this.address = address;
        this.statusCode = statusCode;
        this.method = method;
        this.prayerMethodName = prayerMethodName;
        this.city = city;
        this.timezone = timezone;
        this.query = query;
        this.latitude = latitude;
        this.jsonMemberFor = jsonMemberFor;
        this.link = link;
        this.qiblaDirection = qiblaDirection;
        this.title = title;
        this.statusValid = statusValid;
        this.sealevel = sealevel;
        this.countryCode = countryCode;
        this.daylight = daylight;
        this.todayWeather = todayWeather;
        this.state = state;
        this.postalCode = postalCode;
        this.items = items;
        this.mapImage = mapImage;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getPrayerMethodName() {
        return prayerMethodName;
    }

    public void setPrayerMethodName(String prayerMethodName) {
        this.prayerMethodName = prayerMethodName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getJsonMemberFor() {
        return jsonMemberFor;
    }

    public void setJsonMemberFor(String jsonMemberFor) {
        this.jsonMemberFor = jsonMemberFor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQiblaDirection() {
        return qiblaDirection;
    }

    public void setQiblaDirection(String qiblaDirection) {
        this.qiblaDirection = qiblaDirection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(int statusValid) {
        this.statusValid = statusValid;
    }

    public String getSealevel() {
        return sealevel;
    }

    public void setSealevel(String sealevel) {
        this.sealevel = sealevel;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getDaylight() {
        return daylight;
    }

    public void setDaylight(int daylight) {
        this.daylight = daylight;
    }

    public TodayWeather getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(TodayWeather todayWeather) {
        this.todayWeather = todayWeather;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Jadwal_shalat> getItems() {
        return items;
    }

    public void setItems(List<Jadwal_shalat> items) {
        this.items = items;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return
                "Item_jadwal_shalat{" +
                        "country = '" + country + '\'' +
                        ",status_description = '" + statusDescription + '\'' +
                        ",address = '" + address + '\'' +
                        ",status_code = '" + statusCode + '\'' +
                        ",method = '" + method + '\'' +
                        ",prayer_method_name = '" + prayerMethodName + '\'' +
                        ",city = '" + city + '\'' +
                        ",timezone = '" + timezone + '\'' +
                        ",query = '" + query + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",for = '" + jsonMemberFor + '\'' +
                        ",link = '" + link + '\'' +
                        ",qibla_direction = '" + qiblaDirection + '\'' +
                        ",title = '" + title + '\'' +
                        ",status_valid = '" + statusValid + '\'' +
                        ",sealevel = '" + sealevel + '\'' +
                        ",country_code = '" + countryCode + '\'' +
                        ",daylight = '" + daylight + '\'' +
                        ",today_weather = '" + todayWeather + '\'' +
                        ",state = '" + state + '\'' +
                        ",postal_code = '" + postalCode + '\'' +
                        ",items = '" + items + '\'' +
                        ",map_image = '" + mapImage + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}