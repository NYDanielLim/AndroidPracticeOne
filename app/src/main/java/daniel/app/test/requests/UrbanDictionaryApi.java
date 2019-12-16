package daniel.app.test.requests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UrbanDictionaryApi {

    @Headers({
            "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
            "x-rapidapi-key: a4b454c20emsh8933cc8da96831dp11398djsn924e80751877"
    })

    @GET("define")
    Call<List> getWords(@Query("term") String word);
}
