package io.github.fabiantauriello.check;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Unique tag required for the intent extra
    public static final String EXTRA_MENU_SELECTION = "io.github.fabiantauriello.check.extra.MENU_SELECTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleMainMenuSelection(View view) {

        // Create intent to display tasks in new activity
        Intent intent = new Intent(this, TasksActivity.class);

        // Check which main menu option was selected
        switch (view.getId()) {
            case R.id.text_today:
                intent.putExtra(EXTRA_MENU_SELECTION, MenuSelection.TODAY);
                break;
            case R.id.text_starred:
                intent.putExtra(EXTRA_MENU_SELECTION, MenuSelection.STARRED);
                break;
            case R.id.text_all_tasks:
                intent.putExtra(EXTRA_MENU_SELECTION, MenuSelection.ALL);
                break;
            default:
                // Do nothing.
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

}
