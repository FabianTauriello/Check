package io.github.fabiantauriello.check;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskFragment extends Fragment {

    private static final String LOG_TAG = AddTaskFragment.class.getSimpleName();

    private Task newTask;

    private EditText addTaskFragTextTitle;
    private ImageButton addTaskImageBtn;
    private TextView dueDateTextView;
    private Spinner dueDateTextViewSpinner;
    private TextView remindMeTextView;
    private Spinner remindMeTextViewSpinner;
    private TextView repeatTextView;
    private Spinner repeatTextViewSpinner;

    private LinearLayout llDueDateContainer;
    private LinearLayout llReminderContainer;
    private LinearLayout llRepeatContainer;

    private int timesDueDateSpinnerHasBeenCalled = 0;

    private ImageButton btnClearDueDate;

    private ColorStateList oldColors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        newTask = new Task("", "", 0, 0, 0, false, false, 0, 0, 0);

        TasksActivity tasksActivity = (TasksActivity) getActivity();
        TaskViewModel taskViewModel = tasksActivity.getTaskViewModel();

        setUpViews(view);


        oldColors = dueDateTextView.getTextColors(); //save original colors

        CustomAdapter dueDateAdapter = new CustomAdapter(getContext(), generateList());
        dueDateTextViewSpinner.setAdapter(dueDateAdapter);

        ArrayAdapter<CharSequence> remindMeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.generic_remind_me_array, android.R.layout.simple_spinner_item);
        remindMeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remindMeTextViewSpinner.setAdapter(remindMeAdapter);

        ArrayAdapter<CharSequence> repeatAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.generic_repeat_array, android.R.layout.simple_spinner_item);
        repeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatTextViewSpinner.setAdapter(repeatAdapter);


        addTaskImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // set new title
                newTask.setTitle(addTaskFragTextTitle.getText().toString());

                // TODO: get reminder, and repeat values here

                taskViewModel.insert(newTask); // insert new task into database

                tasksActivity.toggleVisibilityOfFragment(false);
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), 0);
                tasksActivity.getFloatingActionButton().setVisibility(View.VISIBLE);
            }
        });
        dueDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dueDateTextViewSpinner.performClick();
            }
        });
        dueDateTextViewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "onItemSelected" + " position selected " + position);
                timesDueDateSpinnerHasBeenCalled++;
                if(timesDueDateSpinnerHasBeenCalled > 1) {
                    switch (position) {
//                    case 0:
//                        newTask.setDueDateDates(0, 0, 0);
//                        llDueDateContainer.setBackground(null);
//                        dueDateTextView.setText(getString(R.string.generic_set_due_date));
//                        dueDateTextView.setTextColor(oldColors);
//                        dueDateTextView.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_due_date), null, null, null);
                        case 0:
                            newTask.setDueDateDates(
                                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                                    Calendar.getInstance().get(Calendar.MONTH) + 1,
                                    Calendar.getInstance().get(Calendar.YEAR)
                            );
                            changeDateViewToSelected(llDueDateContainer, dueDateTextView, "Due Today", getResources().getDrawable(R.drawable.ic_due_date_selected), btnClearDueDate);
                            break;
                        case 1:
                            newTask.setDueDateDates(
                                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1,
                                    Calendar.getInstance().get(Calendar.MONTH) + 1,
                                    Calendar.getInstance().get(Calendar.YEAR)
                            );
                            changeDateViewToSelected(llDueDateContainer, dueDateTextView, "Due Tomorrow", getResources().getDrawable(R.drawable.ic_due_date_selected), btnClearDueDate);
                            break;
                        case 2:
                            break;
                        case 3:
                            DialogFragment newFragment = new DatePickerFragment(AddTaskFragment.this);
                            newFragment.show(getActivity().getSupportFragmentManager(), "date_picker");
                            break;
                        default:
                            // code to run if there is no case match
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {Log.d(LOG_TAG, "onNothingSelected");}
        });
        remindMeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remindMeTextViewSpinner.performClick();
                remindMeTextViewSpinner.setVisibility(View.VISIBLE);
            }
        });
        remindMeTextViewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        repeatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatTextViewSpinner.performClick();
                repeatTextViewSpinner.setVisibility(View.VISIBLE);
            }
        });
        repeatTextViewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnClearDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "clicked");
                newTask.setDueDateDates(0, 0, 0);
                llDueDateContainer.setBackground(null);
                dueDateTextView.setText(getString(R.string.generic_set_due_date));
                dueDateTextView.setTextColor(oldColors);
                dueDateTextView.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_due_date), null, null, null);
            }
        });

        return view;
    }

    private void setUpViews(View view) {
        // Initialize widgets
        addTaskFragTextTitle = view.findViewById(R.id.frag_task_title);
        addTaskImageBtn = view.findViewById(R.id.frag_task_add_btn);
        dueDateTextView = view.findViewById(R.id.frag_task_due_date);
        dueDateTextViewSpinner = view.findViewById(R.id.frag_task_due_date_spinner);
        remindMeTextView = view.findViewById(R.id.frag_task_reminder);
        remindMeTextViewSpinner = view.findViewById(R.id.frag_task_reminder_spinner);
        repeatTextView = view.findViewById(R.id.frag_task_repeat);
        repeatTextViewSpinner = view.findViewById(R.id.frag_task_repeat_spinner);
        btnClearDueDate = view.findViewById(R.id.btn_clear_due_date);
        llDueDateContainer = view.findViewById(R.id.due_date_container);
        llReminderContainer = view.findViewById(R.id.reminder_container);
        llRepeatContainer = view.findViewById(R.id.repeat_container);
    }

    public void processDatePickerResult(int day, int month, int year, String dateString) {
        newTask.setDueDateDates(day, month, year);
        changeDateViewToSelected(llDueDateContainer, dueDateTextView, dateString, getResources().getDrawable(R.drawable.ic_due_date_selected), btnClearDueDate);
    }

    /**
     * adds custom blue drawable and makes various changes to text colour and adds image button
     *
     * @param container
     * @param textView
     * @param string
     * @param leftDrawable
     * @param clearButton
     */
    private void changeDateViewToSelected(LinearLayout container, TextView textView, String string, Drawable leftDrawable, ImageButton clearButton) {
        // add custom blue drawable as background to view
        container.setBackground(getResources().getDrawable(R.drawable.task_field_selected));

        // change colour and text of view
        textView.setText(string);
        textView.setTextColor(Color.parseColor("#FFFFFF"));

        // change colour of left drawable to white
        textView.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);

        // add cross image button for clearing selection
        clearButton.setVisibility(View.VISIBLE);
    }

    private ArrayList<CustomSpinnerItem> generateList() {
        String today = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        String tomorrow = Calendar.getInstance().add(Calendar.DAY_OF_WEEK, 1);
        ArrayList<CustomSpinnerItem> items = new ArrayList<>();
        items.add(new CustomSpinnerItem("None", R.drawable.ic_cross));
        items.add(new CustomSpinnerItem(getString(R.string.spinner_today) + " (" + today + ")", R.drawable.ic_date_today));
        items.add(new CustomSpinnerItem(getString(R.string.spinner_tomorrow), R.drawable.ic_tomorrow));
        items.add(new CustomSpinnerItem(getString(R.string.spinner_next_week), R.drawable.ic_due_date));
        items.add(new CustomSpinnerItem(getString(R.string.spinner_pick_a_date), R.drawable.ic_date_today));
        return items;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG, "onDetach");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "onActivityResult");
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d(LOG_TAG, "onAttachFragment");
    }

    // TODO: CONSIDER GETTING ACCESS TO ACTIVITY UI ELEMENTS IN HERE INSTEAD OF onCreateView()
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG_TAG, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG_TAG, "onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }


}
