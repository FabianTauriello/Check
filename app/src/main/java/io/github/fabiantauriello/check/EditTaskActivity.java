package io.github.fabiantauriello.check;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditTaskActivity extends AppCompatActivity {
    private static final String LOG_TAG = EditTaskActivity.class.getSimpleName();

    public static final String EXTRA_TASK = "io.github.fabiantauriello.check.EXTRA_TASK";
//    public static final String EXTRA_TITLE = "io.github.fabiantauriello.check.EXTRA_TITLE";
//    public static final String EXTRA_NOTE = "io.github.fabiantauriello.check.EXTRA_NOTE";
//    public static final String EXTRA_DUE_DATE_DAY = "io.github.fabiantauriello.check.EXTRA_DUE_DATE_DAY";
//    public static final String EXTRA_DUE_DATE_MONTH = "io.github.fabiantauriello.check.EXTRA_DUE_DATE_MONTH";
//    public static final String EXTRA_DUE_DATE_YEAR = "io.github.fabiantauriello.check.EXTRA_DUE_DATE_YEAR";
//    public static final String EXTRA_REMINDER_DATE_DAY = "io.github.fabiantauriello.check.EXTRA_REMINDER_DATE_DAY";
//    public static final String EXTRA_REMINDER_DATE_MONTH = "io.github.fabiantauriello.check.EXTRA_REMINDER_DATE_MONTH";
//    public static final String EXTRA_REMINDER_DATE_YEAR = "io.github.fabiantauriello.check.EXTRA_REMINDER_DATE_YEAR";
//    public static final String EXTRA_REPEAT = "io.github.fabiantauriello.check.REPEAT";

    private EditText taskTitle;
    private TextView taskMyDay;
    private TextView taskReminder;
    private TextView taskDueDate;
    private TextView taskRepeat;
    private EditText taskNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Initialize widgets
        taskTitle = findViewById(R.id.edit_task_title);
        taskMyDay = findViewById(R.id.edit_task_my_day);
        taskReminder = findViewById(R.id.edit_task_reminder);
        taskDueDate = findViewById(R.id.edit_task_due_date);
        taskRepeat = findViewById(R.id.edit_task_repeat);
        taskNote = findViewById(R.id.edit_task_note);

        // Get incoming task from intent
        Bundle bundle = getIntent().getExtras();
        Task taskToEdit = bundle.getParcelable(EXTRA_TASK);
        taskTitle.setText(taskToEdit.getTitle());
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

    // Send data back to TasksActivity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(LOG_TAG, "onOptionsItemSelected");
                updateTask();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateTask() {
        String title = taskTitle.getText().toString();
//                String myDay = taskMyDay TODO: this will need to be a toggle button i reckon
        // String dueDate TODO: this will need to be a spinner with a date i reckon
        // String reminder TODO: this will need to be a spinner with a date i reckon
        // String repeat TODO: not sure...
        String note = taskNote.getText().toString();
//        if(title.trim().isEmpty()) {
//            Log.d(LOG_TAG, "Title is empty");
//            Toast.makeText(this, "Please ensure title is not empty", Toast.LENGTH_LONG);
//            return false; // TODO: check this. might have to be true
//        }

        Task updatedTask = new Task(title, note, 9, 12, 1990, false, true,
                1, 23, 1990);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TASK, updatedTask);

        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        updateTask();
        super.onBackPressed();
        Log.d(LOG_TAG, "onBackPressed");
    }
}
