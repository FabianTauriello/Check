package io.github.fabiantauriello.check;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is the backend. The database.
 * The class is set up as a singleton to prevent having multiple instances of the database opened at the same time.
 */

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDAO taskDAO();

    private static TaskRoomDatabase INSTANCE;

    public static synchronized TaskRoomDatabase getDatabase(final Context context) { // TODO: does this method need synchronized in the signature?
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskRoomDatabase.class, "task_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
