package daniel.app.test.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import daniel.app.test.AppExecutors;
import daniel.app.test.models.WordAndDefinition;
import retrofit2.Call;
import retrofit2.Response;

/*
Responsible for retrieving data from Urban Dictionary API
 */
public class UrbanDictionaryApiClient {

    private static final String TAG = "UrbanDictionaryApiClien";
    private static UrbanDictionaryApiClient instance;
    private MutableLiveData<List<WordAndDefinition>> mWordsAndDefinitions;
    private RetrieveWordsAndDefinitionsRunnable mRetrieveWordsAndDefinitionsRunnable;

    public static UrbanDictionaryApiClient getInstance() {
        if (instance == null) {
            instance = new UrbanDictionaryApiClient();
        }
        return instance;
    }

    private UrbanDictionaryApiClient() {
        mWordsAndDefinitions = new MutableLiveData<>();
    }

    public LiveData<List<WordAndDefinition>> getWordsAndDefinitions() {
        return mWordsAndDefinitions;
    }

    public void searchUrbanDictionaryApi(String query) {
        if(mRetrieveWordsAndDefinitionsRunnable != null){
            mRetrieveWordsAndDefinitionsRunnable = null;
        }
        mRetrieveWordsAndDefinitionsRunnable = new RetrieveWordsAndDefinitionsRunnable(query);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveWordsAndDefinitionsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know it is timed out
                handler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    //responsible for query
    private class RetrieveWordsAndDefinitionsRunnable implements Runnable {

        private String query;
        boolean cancelRequest;

        @Override
        public void run() {
            try {
                Response response = getWordsAndDefinitions(query).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200){
                    List<WordAndDefinition> list = new ArrayList<>();
                    daniel.app.test.requests.List responseList = (daniel.app.test.requests.List) response.body();
                    for(int i =0; i < responseList.getList().size(); i++){
                        LinkedTreeMap<Object,Object> words = (LinkedTreeMap) responseList.getList().get(i);
                        String definition = words.get("definition").toString();
                        String word = words.get("word").toString();
                        String numberThumbsUp = words.get("thumbs_up").toString();
                        String numberThumbsDown = words.get("thumbs_down").toString();
                        list.add(new WordAndDefinition(word, definition, numberThumbsUp, numberThumbsDown));
                        Log.d(TAG, "onResponse: " + word);
                    }
                    mWordsAndDefinitions.postValue(list);
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public RetrieveWordsAndDefinitionsRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        private Call<daniel.app.test.requests.List> getWordsAndDefinitions(String query){
            return ServiceGenerator.getUrbanDictionaryApi().getWords(query);
        }
        private void cancelRequest(){
            cancelRequest = true;
        }
    }
}
