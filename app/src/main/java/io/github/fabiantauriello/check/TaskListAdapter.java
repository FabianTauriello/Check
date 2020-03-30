package io.github.fabiantauriello.check;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

/**
 * This adapter caches data and populates the RecyclerView in content_tasks with it.
 * The inner class TaskViewHolder holds and manages a view for one list item.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final View taskItemView;

        private TaskViewHolder(View itemView) {
            super(itemView);
            taskItemView = itemView;
        }
    }

    private static final String LOG_TAG = TaskListAdapter.class.getSimpleName();
    private final LayoutInflater inflater;
    private List<Task> tasks; // cached copy of tasks

    /**
     * Constructor
     * @param context
     */
    public TaskListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
//        this.tasks = tasks;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to
     * represent an item.
     *
     * This new ViewHolder should be constructed with a new View that can
     * represent the items of the given type. A new View can be created
     * manually or inflate it from an XML layout file.
     *
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be reused to
     * display different items in the data set, it is a good idea to cache
     * references to sub views of the View to avoid unnecessary findViewById()
     * calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */
    @Override
    public TaskListAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View itemView = inflater.inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder.itemView to
     * reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(TaskListAdapter.TaskViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder called");
        if (tasks != null) {
            Task currentTask = tasks.get(position);
            TextView title = (TextView) holder.taskItemView.findViewById(R.id.task_title);
            title.setText(currentTask.getTitle());

        } else {
            // Covers the case of data not being ready yet.
            // TODO: write solution for no tasks
        }
    }

    // TODO: The getItemCount() method needs to account gracefully for the possibility that the data is not yet ready and mWords is still null.
    //  In a more sophisticated app, you could display placeholder data or something else that would be meaningful to the user.
    @Override
    public int getItemCount() {
        if (tasks != null) {
            return tasks.size();
        }
        else return 0;
    }

    void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }


}
