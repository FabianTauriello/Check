package io.github.fabiantauriello.check;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.fabiantauriello.check.Task;
import io.github.fabiantauriello.check.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    /**
     * Constructor
     * @param application
     */
    public TaskViewModel (Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void update(Task task) {
        repository.update(task);
    }

    public void delete(Task task) {
        repository.delete(task);
    }

    public void deleteAll() {
        repository.deleteAllTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // OR (OR BOTH)

    LiveData<List<Task>> getTasks(Intent intent) {
        MenuSelection menuSelection = (MenuSelection) intent.getSerializableExtra(MainActivity.EXTRA_MENU_SELECTION);
        LiveData<List<Task>> tasks;
        if(menuSelection == MenuSelection.TODAY) {
            tasks = getAllTasks(); // TODO: this will need to be sorted out somehow. not sure what "TODAY" is exactly
        } else if(menuSelection == MenuSelection.STARRED) {
            tasks = repository.getStarredTasks();
        } else {
            tasks = getAllTasks();
        }
        return tasks;
    }

}
