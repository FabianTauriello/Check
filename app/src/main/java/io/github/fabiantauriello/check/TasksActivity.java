package io.github.fabiantauriello.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TasksActivity extends AppCompatActivity {
    private static final String LOG_TAG = TasksActivity.class.getSimpleName();
    public static final int EDIT_TASK_REQUEST_CODE = 1;
    private FloatingActionButton fab;
    private Fragment addTaskFrag;
    private FragmentManager fragManager;
    private RecyclerView recyclerView;
    private TaskListAdapter adapter;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        fab = findViewById(R.id.fab);

        // add and set up fragment
        addTaskFrag = new AddTaskFragment();
        fragManager = getSupportFragmentManager();
        addFragment();

        // set up recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TaskListAdapter();
        recyclerView.setAdapter(adapter);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                Log.d(LOG_TAG, "onChanged");
                adapter.setTasks(tasks);
            }
        });

        adapter.setOnTaskClickListener(new TaskListAdapter.onTaskClickListener() {
            @Override
            public void onTaskClick(Task selectedTask) {
                // Log.d(LOG_TAG, "selectedTask selected: " + Integer.toString(selectedTask.getId()));
                Intent intent = new Intent(TasksActivity.this, EditTaskActivity.class);
                intent.putExtra(EditTaskActivity.EXTRA_TASK, selectedTask);
                startActivityForResult(intent, EDIT_TASK_REQUEST_CODE);
            }
        });

    }

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }

    private void addFragment() {
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        fragmentTransaction.add(R.id.add_task_fragment_placeholder, addTaskFrag, "addTaskFrag");
        fragmentTransaction.hide(addTaskFrag);
        fragmentTransaction.commit();
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
        if (show) {
            fragmentTransaction.show(addTaskFrag);
        } else {
            fragmentTransaction.hide(addTaskFrag);
        }

        fragmentTransaction.commit();

    }

    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(LOG_TAG, "result code... " + Integer.toString(resultCode));
        if(requestCode == EDIT_TASK_REQUEST_CODE) {
            Log.d(LOG_TAG, "Task changed");

            Task updatedTask = (Task) data.getExtras().getParcelable(EditTaskActivity.EXTRA_TASK);
            Log.d(LOG_TAG,"Update task: id = " + Integer.toString(updatedTask.getId()) + ". title = " + updatedTask.getTitle());
            Log.d(LOG_TAG, "--------------");
            showTasks();
            Log.d(LOG_TAG, "--------------");
            taskViewModel.delete(updatedTask);
            Log.d(LOG_TAG, "--------------");
            showTasks();
        } else {
            Log.d(LOG_TAG, "Task was deleted");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    public void showTasks() {
        Log.d(LOG_TAG, "Do shit");
        LiveData<List<Task>> stuff = taskViewModel.getAllTasks();
        Log.d(LOG_TAG, "Number of tasks: " + stuff.getValue().size());
        for (int i = 0; i < stuff.getValue().size(); i++){
            Log.d(LOG_TAG, "id: " + stuff.getValue().get(i).getId());
            Log.d(LOG_TAG, "title: " + stuff.getValue().get(i).getTitle());
        }
    }

}
