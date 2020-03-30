package io.github.fabiantauriello.check;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * The Room Magic is in this file, where a Java method call is mapped to an SQL query.
 */

@Dao
public interface TaskDAO {
    /**
     * Inserts a single task row
     */
    @Insert
    void insert(Task task);

    /**
     * Updates a single task row
     */
    @Update
    void update(Task task);

    /**
     * Deletes a single task row
     */
    @Update
    void delete(Task task);

    /**
     * Deletes all tasks
     */
    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    /**
     * Selects all tasks
     */
    @Query("SELECT * from task_table")
    LiveData<List<Task>> getAllTasks();
}
