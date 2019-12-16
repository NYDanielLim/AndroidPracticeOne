package daniel.app.test.respositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import daniel.app.test.requests.ServiceGenerator;
import daniel.app.test.requests.UrbanDictionaryApi;
import daniel.app.test.models.WordAndDefinition;
import daniel.app.test.requests.UrbanDictionaryApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Retrieve data from online source
public class WordsAndDefinitionsRepository {

    private static final String TAG = "WordsAndDefinitionsRepo";
    private static WordsAndDefinitionsRepository instance;
    private UrbanDictionaryApiClient mUrbanDictionaryApiClient;

    /*Singleton Pattern used because we don't want to have
    a bunch of open connectiosn to web service/caches so we have one instance
     */

    public static WordsAndDefinitionsRepository getInstance() {
        if (instance == null) {
            instance = new WordsAndDefinitionsRepository();
        }
        return instance;
    }
    private WordsAndDefinitionsRepository(){
        mUrbanDictionaryApiClient = UrbanDictionaryApiClient.getInstance();
    }
    public LiveData<List<WordAndDefinition>> getWordsAndDefinitions(){
        return mUrbanDictionaryApiClient.getWordsAndDefinitions();
    }

    public void searchUrbanDictionaryApi(String query){
        mUrbanDictionaryApiClient.searchUrbanDictionaryApi(query);
    }

    /*public MutableLiveData<List<WordAndDefinition>> getWordAndDefinitions() {
        setWordAndDefinitions();

        MutableLiveData<List<WordAndDefinition>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }
*/
   /* public void setWordAndDefinitions() {
        UrbanDictionaryApi urbanDictionaryApi = ServiceGenerator.getUrbanDictionaryApi();

        Call<daniel.app.test.requests.List> responseCall = urbanDictionaryApi.getWords("epic");
        responseCall.enqueue(new Callback<daniel.app.test.requests.List>() {
            @Override
            public void onResponse(Call<daniel.app.test.requests.List> call, Response<daniel.app.test.requests.List> response) {
                if (response.code() == 200) {
                    Log.d("daniel", "Success " + response.body().toString());
                    daniel.app.test.requests.List list = response.body();
                    for (int i = 0; i < list.getList().size(); i++) {
                        LinkedTreeMap<Object, Object> words = (LinkedTreeMap) list.getList().get(i);
                        String definition = words.get("definition").toString();
                        String word = words.get("word").toString();
                        dataSet.add(new WordAndDefinition(word, definition));
                        Log.d(TAG, "onResponse: " + word);
                    }
                    return;
                }

            }

            @Override
            public void onFailure(Call<daniel.app.test.requests.List> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }*/
}
