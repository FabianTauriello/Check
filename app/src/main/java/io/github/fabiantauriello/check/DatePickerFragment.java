package io.github.fabiantauriello.check;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String LOG_TAG = DatePickerFragment.class.getSimpleName();

    private AddTaskFragment fragment;

    // if fragment is passed null, then it's coming from edit task activity
    public DatePickerFragment(AddTaskFragment fragment) {
        this.fragment = fragment;

    }

    /**
     * Creates the date picker dialog with the current date from Calendar.
     *
     * @param savedInstanceState Saved instance state bundle
     * @return DatePickerDialog     The date picker dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    /**
     * Grabs the date and passes it to processDatePickerResult().
     *
     * @param datePicker The date picker view
     * @param year       The year chosen
     * @param month      The month chosen
     * @param day        The day chosen
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        // get current date as a Calendar object and set it to selected date.
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        // format date for output
        String[] dateArray = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime()).split(", ");
        for (int i = 0; i < dateArray.length; i++) {
            Log.d(LOG_TAG, dateArray[i]);
        }
        Log.d(LOG_TAG, dateArray.toString());
        String dayOfWeek = dateArray[0].substring(0, 3);
        String dateOfMonth = dateArray[1].split(" ")[0];
        String dateOfMonthName = dateArray[1].split(" ")[1].substring(0, 3);
        StringBuilder dateString = new StringBuilder();
        dateString.append("Due ").append(dayOfWeek).append(", ").append(dateOfMonth).append(" ").append(dateOfMonthName);

        if (fragment != null) {
            fragment.processDatePickerResult(day, month, year, dateString.toString());
        } else {
            // Set the activity to the Tasks Activity.
            EditTaskActivity activity = (EditTaskActivity) getActivity();
            // Invoke Tasks Activity's processDatePickerResult() method.
            activity.processDatePickerResult(day, month, year, "dateString.toString()");
        }
    }


}
