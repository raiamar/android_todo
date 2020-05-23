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

    public AddEditTaskViewModel(Application application, int taskId) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        task = database.taskDao().loadTaskById(taskId);
    }
}
