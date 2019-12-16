package daniel.app.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import daniel.app.test.adapters.RecyclerViewAdapter;
import daniel.app.test.models.WordAndDefinition;
import daniel.app.test.viewmodels.MainActivityViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //vars
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private ProgressBar mProgressBar;
    private EditText wordSearch;
    private Button search;
    private Button sortByThumbsUp;
    private Button sortByThumbsDown;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");

        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        wordSearch = findViewById(R.id.word_search);
        search = findViewById(R.id.search);
        sortByThumbsUp = findViewById(R.id.sort_by_thumbs_up);
        sortByThumbsDown = findViewById(R.id.sort_by_thumbs_down);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        initRecyclerView();
        subscribeObservers();
        searchUrbanDictionaryApi("epic");

    }

    private void subscribeObservers() {
        mMainActivityViewModel.getWordsAndDefinitions().observe(this, new Observer<List<WordAndDefinition>>() {
            //onChanged will be triggered when a change is made to LiveData/wordAndDefinition Objects
            //We observe LiveData and not MutableLiveData because the data will not be changed
            //by something like a screen rotation
            @Override
            public void onChanged(@Nullable List wordAndDefinitions) {
                if (wordAndDefinitions != null) {
                    Log.d(TAG, "word");
                    mAdapter.updateData(wordAndDefinitions);
                }
            }
        });
    }

    private void searchUrbanDictionaryApi(String query) {
        mMainActivityViewModel.searchUrbanDictionaryApi(query);
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: initRecyclerView");
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onSearchButtonClick(View v){
        searchUrbanDictionaryApi(wordSearch.getText().toString());
    }

    public void onSortByThumbsUpClick(View v){
        Log.d(TAG, "onSortByThumbsUpClick: Click");
        Collections.sort(mMainActivityViewModel.getWordsAndDefinitions().getValue(), RecyclerViewAdapter.sortByThumbsUp);
        mAdapter.notifyDataSetChanged();
    }

    public void onSortByThumbsDownClick(View v){
        Log.d(TAG, "onSortByThumbsUpClick: Click");
        Collections.sort(mMainActivityViewModel.getWordsAndDefinitions().getValue(), RecyclerViewAdapter.sortByThumbsDown);
        mAdapter.notifyDataSetChanged();
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
