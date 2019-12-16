package daniel.app.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance ==null){
            instance = new AppExecutors();
        }
        return instance;
    }

    /*
    Allows us to add extra functionality to executors
    Executor service that can schedule commands to run after a given delay
    Executor: Used to execute runnable tasks on whichever thread
     */
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
}
