package io.github.fabiantauriello.check;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This adapter caches data and populates the RecyclerView in content_tasks with it.
 * The inner class TaskViewHolder holds and manages a view for one list item.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private static final String LOG_TAG = TaskListAdapter.class.getSimpleName();
    private List<Task> dataSet;
//    private final LayoutInflater inflater;
    private onTaskClickListener listener;

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private ToggleButton taskCheckCircle, taskStar;
        private TextView taskTitle;

        public TaskViewHolder(View card) {
            super(card);
            taskCheckCircle = card.findViewById(R.id.task_tick);
            taskStar = card.findViewById(R.id.task_star);
            taskTitle = card.findViewById(R.id.task_title);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onTaskClick(dataSet.get(position));
                    }
                }
            });
        }

        public TextView getTaskTitle() {
            return taskTitle;
        }
    }

    public interface onTaskClickListener {
        void onTaskClick(Task task);
    }

    public void setOnTaskClickListener(onTaskClickListener listener) {
        this.listener = listener;
    }

//    public TaskListAdapter(Context context, onTaskClickListener listener) {
//        inflater = LayoutInflater.from(context);
//        this.listener = listener;
//    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(card);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder");
        // - get element from the dataset at this position
        // - replace the contents of the view with that element (just title for now...)
//        holder.getTaskCheckCircle()
        holder.getTaskTitle().setText(dataSet.get(position).getTitle());
//        holder.getTaskStar()
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    public void setTasks(List<Task> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

}
