package io.github.fabiantauriello.check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTasksForToday(View view) {
        Intent intent = new Intent(this, TasksActivity.class);
    }

    public void showTasksForStarred(View view) {
        Intent intent = new Intent(this, TasksActivity.class);

    }

    public void showAllTasks(View view) {
        Intent intent = new Intent(this, TasksActivity.class);

    }
}
