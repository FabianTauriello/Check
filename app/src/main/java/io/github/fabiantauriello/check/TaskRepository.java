package io.github.fabiantauriello.check;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class TaskRepository {

    private TaskDAO taskDAO;

    private LiveData<List<Task>> allTasks;

    /**
     * Constructor
     * @param application
     */
    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDAO = db.taskDAO();
        allTasks = taskDAO.getAllTasks();
    }

    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * @return all tasks
     */
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    /**
     * Use an AsyncTask to call insert() on a non-UI thread, or the app will crash.
     * Room ensures that there aren't any long-running operations on the main thread, which would block the UI.
     * @param task The new task to insert
     */
    public void insert(Task task) {
        new InsertAsyncTask(taskDAO).execute(task);
    }

    public void update(Task task) {
        new UpdateAsyncTask(taskDAO).execute(task);
    }

    public void delete(Task task) {
        new DeleteAsyncTask(taskDAO).execute(task);
    }

    public void deleteAllTasks() {
        new DeleteAllTasksAsyncTask(taskDAO).execute();
    }

    private static class InsertAsyncTask extends android.os.AsyncTask<Task, Void, Void> {

        private TaskDAO asyncTaskDao;

        /**
         * Constructor
         * @param dao
         */
        private InsertAsyncTask(TaskDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            asyncTaskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDAO asyncTaskDao;

        /**
         * Constructor
         * @param dao
         */
        private UpdateAsyncTask(TaskDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            asyncTaskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDAO asyncTaskDao;

        /**
         * Constructor
         * @param dao
         */
        private DeleteAsyncTask(TaskDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            asyncTaskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void> {

        private TaskDAO asyncTaskDao;

        /**
         * Constructor
         * @param dao
         */
        private DeleteAllTasksAsyncTask(TaskDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.deleteAllTasks();
            return null;
        }
    }
}
