package daniel.app.test.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import daniel.app.test.R;
import daniel.app.test.models.WordAndDefinition;

//Specifies type <> which is a view holder type
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<WordAndDefinition> mWordAndDefinitions = new ArrayList<>();
    private Context mContext;

    //Responsible for inflating the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.word.setText(mWordAndDefinitions.get(position).getWord());
        holder.word_definition.setText(mWordAndDefinitions.get(position).getDefinition());
        holder.btn_thumbs_up.setText(mWordAndDefinitions.get(position).getNumberThumbsUp());
        holder.btn_thumbs_down.setText(mWordAndDefinitions.get(position).getNumberThumbsDown());

        holder.word.setPaintFlags(holder.word.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    //Tells adapter how many list items are in the list
    @Override
    public int getItemCount() {
        if(mWordAndDefinitions!=null){
            return mWordAndDefinitions.size();
        }
        return 0;
    }

    public void updateData(List<WordAndDefinition> data){
        mWordAndDefinitions = data;
        notifyDataSetChanged();
    }

    public static Comparator<WordAndDefinition> sortByThumbsUp = new Comparator<WordAndDefinition>() {
        @Override
        public int compare(WordAndDefinition wordOne, WordAndDefinition wordTwo) {
            double doubleOne = Double.valueOf(wordOne.getNumberThumbsUp());
            double doubleTwo = Double.valueOf(wordTwo.getNumberThumbsUp());
            if(doubleOne > doubleTwo){
                return -1;
            }
            if(doubleOne == doubleTwo){
                return 0;
            }
            return 1;
        }
    };

    public static Comparator<WordAndDefinition> sortByThumbsDown = new Comparator<WordAndDefinition>() {
        @Override
        public int compare(WordAndDefinition wordOne, WordAndDefinition wordTwo) {
            double doubleOne = Double.valueOf(wordOne.getNumberThumbsUp());
            double doubleTwo = Double.valueOf(wordTwo.getNumberThumbsUp());
            if(doubleOne > doubleTwo){
                return -1;
            }
            if(doubleOne == doubleTwo){
                return 0;
            }
            return 1;
        }
    };

    /*
    View Holder holds the widgets in memory of each individual entry
    Each line basically is held in memory and each line will get recycled
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView word;
        TextView word_definition;
        Button btn_thumbs_up;
        Button btn_thumbs_down;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            word_definition = itemView.findViewById(R.id.word_definition);
            btn_thumbs_up = itemView.findViewById(R.id.btnThumbsUp);
            btn_thumbs_down = itemView.findViewById(R.id.btnThumbsDown);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
