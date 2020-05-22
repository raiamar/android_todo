package com.example.todomvvm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("select * from task order by priority")
    List<TaskEntry> loadAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insertTasks(TaskEntry task);

    @Update
    Void update (TaskEntry task);

    Void deleteTask(TaskEntry task);
}
