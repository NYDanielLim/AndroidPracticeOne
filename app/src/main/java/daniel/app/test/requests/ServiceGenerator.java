package daniel.app.test.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

   private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static UrbanDictionaryApi urbanDictionaryApi = retrofit.create(UrbanDictionaryApi.class);

    public static UrbanDictionaryApi getUrbanDictionaryApi(){
        return urbanDictionaryApi;
    }
}
