package mobileapps.talbia.Network;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import mobileapps.talbia.Model.Informasi_m;
import mobileapps.talbia.Model.Konfigurasi_m;
import mobileapps.talbia.Model.Layanan_m;
import mobileapps.talbia.Model.ResponseRepos;
import mobileapps.talbia.Model.bimbingan.Almasurat_m;
import mobileapps.talbia.Model.bimbingan.Dzikir_m;
import mobileapps.talbia.Model.bimbingan.Sejarah_m;
import mobileapps.talbia.Model.bimbingan.Wawasan_m;
import mobileapps.talbia.Model.bimbingan.doa.Doa_2_m;
import mobileapps.talbia.Model.bimbingan.shalat.Item_jadwal_shalat;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("BerandaFragment/cek_versi")
    Call<ResponseBody> cekversi(@Field("versi") String versi);

    @GET("https://api.github.com/users/{username}/repos")
    Observable<List<ResponseRepos>> requestRepos(@Path("username") String username);

    @GET("http://muslimsalat.com/{lokasi}/daily.json")
    Observable<Item_jadwal_shalat> ambiljadwalshalat(@Path("lokasi") String lokasi);

    @GET("doa")
    Observable<ResponseBody> ambildoa();


    @GET("almasurat")
    Observable<List<Almasurat_m>> ambil_almasurat();

    @GET("dzikir/{jenis}")
    Observable<List<Dzikir_m>> ambil_dzikir(@Path("jenis") String id);

    @GET("doa/{jenis}")
    Observable<List<Doa_2_m>> ambil_doa(@Path("jenis") String id);
//    Observable<List<Doa_m>> ambil_doa(@Path("jenis") String id);

    @GET("sejarah-islam")
    Observable<List<Sejarah_m>> ambil_sejarahislam();

    @GET("wawasan-dunia")
    Observable<List<Wawasan_m>> ambilwawasandunia();

    @GET("layanan")
    Observable<List<Layanan_m>> ambil_layanan();

    @GET("layanan-unggulan")
    Observable<List<Layanan_m>> ambil_layanan_unggulan();


    @GET("informasi")
    Observable<List<Informasi_m>> ambil_informasi();


    @GET("konfigurasi")
    Observable<Konfigurasi_m> ambil_konfigurasi();


    @FormUrlEncoded
    @POST("login-pengguna")
    Observable<ResponseBody> login(@Field("email") String s,
                                   @Field("kata_sandi") String s1);


    @FormUrlEncoded
    @POST("daftar-pengguna")
    Observable<ResponseBody> daftar(
            @Field("email") String s,
            @Field("kata_sandi") String s1,
            @Field("nama_lengkap") String s2,
            @Field("jenis_kelamin") String s3,
            @Field("no_hp") String s4,
            @Field("tanggal_lahir") String s5
    );


    @FormUrlEncoded
    @PUT("update-pengguna/{id}")
    Observable<ResponseBody> update_akun(
            @Path("id") String id,
            @Field("email") String s,
            @Field("no_hp") String s1,
            @Field("namalengkap") String s2);

    @FormUrlEncoded
    @PUT("update-sandi/{id}")
    Observable<ResponseBody> update_sandi(
            @Path("id") String id,
            @Field("kata_sandi_lama") String s,
            @Field("kata_sandi") String s1);


    @Multipart
    @POST("input-umroh")
    Observable<ResponseBody> kirim_data_umroh(@PartMap Map<String, RequestBody> inputan);

    @Multipart
    @POST("input-haji")
    Observable<ResponseBody> kirim_data_haji(@PartMap Map<String, RequestBody> inputan);

    @FormUrlEncoded
    @POST("request-reset-password")
    Observable<ResponseBody> request_reset_password(
            @Field("email") String s
    );


    @FormUrlEncoded
    @POST("reset-password")
    Observable<ResponseBody> reset_password(
            @Field("password") String s,
            @Field("token") String s1
    );
}
