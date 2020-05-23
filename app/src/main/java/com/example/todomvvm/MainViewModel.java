package com.example.todomvvm;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todomvvm.database.AppDatabase;
import com.example.todomvvm.database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    AppDatabase database;
    private  final static String TAG = MainViewModel.class.getSimpleName();
    private LiveData<List<TaskEntry>> tasks;
    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "actively reteriving data form database");
        database = AppDatabase.getInstance(application);
        tasks = database.taskDao().loadAllTasks();
    }
    public LiveData<List<TaskEntry>> getTasks(){
        return tasks;
    }


    public void deleteTask(final TaskEntry task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {

                database.taskDao().deleteTask(task);

            }
        });
    }
}
