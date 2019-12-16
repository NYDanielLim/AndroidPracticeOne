package daniel.app.test.requests;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class List {

    @SerializedName("list")
    private ArrayList list;

    public ArrayList getList() {
        return list;
    }
}
