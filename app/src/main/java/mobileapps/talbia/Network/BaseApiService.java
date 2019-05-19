package mobileapps.talbia.Network;

public class BaseApiService {
//    private static String GITHUB_BASE_URL_API = "https://api.github.com/";
//    public static ApiInterface getGithubAPIService(){
//        return ApiClient.getClient(GITHUB_BASE_URL_API).create(ApiInterface.class);
//    }

    //    private static String BASE_URL_API = "http://192.168.1.14:8000/";
    private static String BASE_URL_API = "http://api.apk-talbia.com/";

    public static ApiInterface getAPIService() {
        return ApiClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }

//
//    private static String MUSLIMSHALAT_BASE_URL_API = "http://muslimsalat.com/";
//    public static ApiInterface getMuslimShalatAPIService(){
//        return ApiClient.getClient(MUSLIMSHALAT_BASE_URL_API).create(ApiInterface.class);
//    }

}
