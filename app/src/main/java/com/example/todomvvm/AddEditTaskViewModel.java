package com.example.todomvvm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todomvvm.database.AppDatabase;
import com.example.todomvvm.database.TaskEntry;

class AddEditTaskViewModel extends AndroidViewModel {
    public LiveData<TaskEntry> getTask() {
        return task;
    }

    private LiveData<TaskEntry> task;

    AppDatabase database;

    public AddEditTaskViewModel(Application application, int taskId) {
        super(application);
        database = AppDatabase.getInstance(application);
        if (taskId != -1)
            task = database.taskDao().loadTaskById(taskId);
    }

    public void insertTask (final TaskEntry task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.taskDao().insertTask(task);
            }
        });
    }

    public void updateTask (final TaskEntry task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.taskDao().update(task);
            }
        });
    }


}
