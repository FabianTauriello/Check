package io.github.fabiantauriello.check;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Calendar;

public class EditTaskActivity extends AppCompatActivity {
    private static final String LOG_TAG = EditTaskActivity.class.getSimpleName();

    public static final String EXTRA_TASK = "io.github.fabiantauriello.check.EXTRA_TASK";

    private Task taskToEdit;

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
        taskToEdit = bundle.getParcelable(EXTRA_TASK);
        taskTitle.setText(taskToEdit.getTitle());

        // set up widgets for state of task
        if (checkIfDatesMatch(taskToEdit.getMyDayValues(), getCurrentDateValues())) {
            taskMyDay.setText(R.string.edit_added_to_my_day);
        } else {
            taskMyDay.setText(R.string.edit_add_to_my_day);
        }
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
    // (up arrow)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(LOG_TAG, "onOptionsItemSelected");
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateTask() {
        Log.d(LOG_TAG, "updateTask");
        taskToEdit.setTitle(taskTitle.getText().toString());
        taskToEdit.setNote(taskNote.getText().toString());

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TASK, taskToEdit);

        setResult(RESULT_OK, intent);
        finish();
    }

    // back button
    @Override
    public void onBackPressed() {
        updateTask();
        super.onBackPressed();
        Log.d(LOG_TAG, "onBackPressed");
    }

    private boolean checkIfDatesMatch(int[] arg1, int[] arg2) {
        for (int i = 0; i < arg1.length; i++) {
            if (arg1[i] != arg2[i]) {
                return false;
            }
        }
        return true;
    }

    // TODO: consider putting this (and one above) into a Utility class if it's needed in other classes.
    // get current date values
    private int[] getCurrentDateValues() {
        Calendar currentDate = Calendar.getInstance();
        int[] result = {
                currentDate.get(Calendar.DAY_OF_MONTH),
                currentDate.get(Calendar.MONTH) + 1,
                currentDate.get(Calendar.YEAR)
        };
        return result;
    }

    public void updateMyDay(View view) {
        Log.d(LOG_TAG, "updateMyDay");

        // get current date values
        int[] currentDayValues = getCurrentDateValues();

        if (checkIfDatesMatch(taskToEdit.getMyDayValues(), currentDayValues)) {
            taskToEdit.setMyDayDay(0);
            taskToEdit.setMyDayMonth(0);
            taskToEdit.setMyDayYear(0);
            ((TextView) view).setText(R.string.edit_add_to_my_day);
        } else {
            taskToEdit.setMyDayDay(currentDayValues[0]);
            taskToEdit.setMyDayMonth(currentDayValues[1]);
            taskToEdit.setMyDayYear(currentDayValues[2]);
            ((TextView) view).setText(R.string.edit_added_to_my_day);
        }
//        Log.d(LOG_TAG, ((TextView) view).getText().toString());
    }

    public void processDatePickerResult(int day, int month, int year, String dateString) {
        taskToEdit.setDueDateDates(day, month, year);
    }
}
