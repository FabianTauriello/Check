package io.github.fabiantauriello.check;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddTaskFragment extends Fragment {

    private static final String LOG_TAG = AddTaskFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        TasksActivity tasksActivity = (TasksActivity) getActivity();
        TaskViewModel taskViewModel = tasksActivity.getTaskViewModel();

        ImageButton addTaskImageBtn = view.findViewById(R.id.frag_task_add_btn);
        TextView dueDateTextView = view.findViewById(R.id.frag_task_due_date);
        TextView remindMeTextView = view.findViewById(R.id.frag_task_reminder);
        TextView repeatTextView = view.findViewById(R.id.frag_task_repeat);

        addTaskImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // grab new task title
                EditText addTaskFragTextTitle = view.findViewById(R.id.frag_task_title);
                String title = addTaskFragTextTitle.getText().toString();

                // TODO: get due date, reminder, and repeat values here

                Task newTask = new Task(title, "", 9, 12, 1990, false);

                taskViewModel.insert(newTask); // insert new task into database

                tasksActivity.toggleVisibilityOfFragment(false);
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), 0);
                tasksActivity.getFloatingActionButton().setVisibility(View.VISIBLE);
            }
        });
        dueDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        remindMeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        repeatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
