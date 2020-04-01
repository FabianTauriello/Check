package io.github.fabiantauriello.check;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

                Task newTask = new Task(title, "", 9, 12, 1990, false, true,
                1, 23, 1990);

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
