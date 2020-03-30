package io.github.fabiantauriello.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private static final String LOG_TAG = TasksActivity.class.getSimpleName();

    private TaskViewModel taskViewModel;

    private FloatingActionButton fab;

    private Fragment addTaskFrag;

    private FragmentManager fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        fab = findViewById(R.id.fab);

        addTaskFrag = new AddTaskFragment();
        fragManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        fragmentTransaction.add(R.id.add_task_fragment_placeholder, addTaskFrag, "addTaskFrag");
        fragmentTransaction.hide(addTaskFrag);
        fragmentTransaction.commit();

        // Create recycler view.
        RecyclerView recyclerView = findViewById(R.id.recycler);
        // Create an adapter and supply the data to be displayed.
        final TaskListAdapter adapter = new TaskListAdapter(this);
        // Connect the adapter with the recycler view.
        recyclerView.setAdapter(adapter);
        // Give the recycler view a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        // Observer stuff
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                // Update the cached copy of the words in the adapter.
                adapter.setTasks(tasks);
            }
        });

        buildTaskList(getIntent());

    }

    private void buildTaskList(Intent intent) {
        MenuSelection menuSelection = (MenuSelection) intent.getSerializableExtra(MainActivity.EXTRA_MENU_SELECTION);

        // TODO: get different tasks from Room depending on main menu selection
        switch (menuSelection) {
            case TODAY:

                break;
            case STARRED:

                break;
            case ALL:

                break;
            default:
                // Do nothing.
                break;
        }
    }

    public void displayAddTaskFragment(View view) {

        toggleVisibilityOfFragment(true); // display fragment

        EditText addTaskFragTextTitle = findViewById(R.id.frag_task_title); // get task title view

        addTaskFragTextTitle.requestFocus(); // place cursor in task title view

        // show keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(addTaskFragTextTitle, InputMethodManager.SHOW_IMPLICIT);

        fab.setVisibility(View.GONE); // hide fab

    }

    public void toggleVisibilityOfFragment(boolean show) {

        FragmentTransaction fragmentTransaction = fragManager.beginTransaction(); // start fragment transaction

        // show/hide fragment
        if(show) {
            fragmentTransaction.show(addTaskFrag);
        } else {
            fragmentTransaction.hide(addTaskFrag);
        }

        fragmentTransaction.commit();

    }

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }

    public FloatingActionButton getFloatingActionButton() {
        return fab;
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

//    public void tickTask(View view) {
//        ImageButton circle = (ImageButton)view;
//        if(circle.getTag() == "unchecked" || circle.getTag() == "") {
//            circle.setImageResource(R.drawable.ic_task_checked_circle);
//            circle.setTag("checked");
//        } else {
//            circle.setImageResource(R.drawable.ic_task_empty_circle);
//            circle.setTag("unchecked");
//        }
//    }

    public void editTask(View view) {

    }

    public void starTask(View view) {

    }
}
