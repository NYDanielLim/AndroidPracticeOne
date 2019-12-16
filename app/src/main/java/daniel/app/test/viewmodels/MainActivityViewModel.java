package daniel.app.test.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import daniel.app.test.models.WordAndDefinition;
import daniel.app.test.respositories.WordsAndDefinitionsRepository;

public class MainActivityViewModel extends ViewModel {

    private WordsAndDefinitionsRepository mRepo;

    public MainActivityViewModel(){
        mRepo = WordsAndDefinitionsRepository.getInstance();
    }

    //Live data is immutable
    public LiveData<List<WordAndDefinition>> getWordsAndDefinitions(){
        return mRepo.getWordsAndDefinitions();
    }

    public void searchUrbanDictionaryApi(String query){
        mRepo.searchUrbanDictionaryApi(query);
    }

}
