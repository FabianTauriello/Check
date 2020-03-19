package io.github.fabiantauriello.check;

import java.util.Date;

/**
 * Represents a single task.
 */
public class Task {

    private String title;
    private String note;
    private Date dueDate;
    private boolean repeat;

    /**
     * Creates a new io.github.fabiantauriello.check.Task with the given arguments
     */
    public Task(String title, String note, Date dueDate, boolean repeat) {
        this.title = title;
        this.note = note;
        this.dueDate = dueDate;
        this.repeat = repeat;
    }

    // Setters

    /**
     * Gets the title of this task
     * @return this task's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the note of this task
     * @return this task's note
     */
    public String getNote() {
        return note;
    }

    /**
     * Gets the due date of this task
     * @return this task's due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Gets the repeat flag of this task
     * @return this task's repeat flag
     */
    public boolean isRepeat() {
        return repeat;
    }

    // Setters

    /**
     * Changes the title of this task
     * @param title This task's new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Changes the note of this task
     * @param note This task's new note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Changes the due date of this task
     * @param dueDate This task's new due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Changes the repeat flag of this task
     * @param repeat This task's new repeat flag
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
