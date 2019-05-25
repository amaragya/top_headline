package amaragya.bahaso.Network;

public class BaseApiService {
    private static String BASE_URL_API = "http://api.domain.com/";

    public static ApiInterface getAPIService() {
        return ApiClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }

}
