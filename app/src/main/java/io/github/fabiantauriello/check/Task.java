package io.github.fabiantauriello.check;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Represents a Task entity for the Room database.
 * To make the Task class meaningful to a Room database, it must be annotated.
 * Annotations identify how each part of the Task class relates to an entry in the database.
 * Room uses this information to generate code.
 */

@Entity(tableName = "task_table")
public class Task implements Parcelable {

    // Member variables

    @PrimaryKey(autoGenerate = true)
    private int id = 1;

    @NonNull
    private String title;

    private String note;

    @ColumnInfo(name = "due_date_day")
    private int dueDateDay;

    @ColumnInfo(name = "due_date_month")
    private int dueDateMonth;

    @ColumnInfo(name = "due_date_year")
    private int dueDateYear;

    private boolean repeat;

    private boolean isStarred;

    public int getMyDayDay() {
        return myDayDay;
    }

    public int getMyDayMonth() {
        return myDayMonth;
    }

    public int getMyDayYear() {
        return myDayYear;
    }

    // These value will be used to see if this task should be shown in the "My Day" list.
    // Each time the app is opened and data is loaded, these values will be checked with the current date
    // and if they match, the task will be included in the "My Day" section. If these values are null
    // or don't match the current date, the task will NOT be included in the "My Day" section.
    private int myDayDay;
    private int myDayMonth;
    private int myDayYear;

    /**
     * Constructor with everything
     * Creates a new io.github.fabiantauriello.check.Task with the given arguments
     */
    public Task(String title, String note, int dueDateDay, int dueDateMonth, int dueDateYear, boolean repeat, boolean isStarred,
                int myDayDay, int myDayMonth, int myDayYear) {
        this.title = title;
        this.note = note;
        this.dueDateDay = dueDateDay;
        this.dueDateMonth = dueDateMonth;
        this.dueDateYear = dueDateYear;
        this.repeat = repeat;
        this.isStarred = isStarred;
        this.myDayDay = myDayDay;
        this.myDayMonth = myDayMonth;
        this.myDayYear = myDayYear;
    }


    public Task(Parcel source) {
        // the order needs to be the same as in writeToParcel() method
        id = source.readInt();
        title = source.readString();
        note = source.readString();
        dueDateDay = source.readInt();
        dueDateMonth = source.readInt();
        dueDateYear = source.readInt();
        repeat = source.readBoolean();
        isStarred = source.readBoolean();
        myDayDay = source.readInt();
        myDayMonth = source.readInt();
        myDayYear = source.readInt();
    }

    // Parcelable is faster than Serialization
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(note);
        dest.writeInt(dueDateDay);
        dest.writeInt(dueDateMonth);
        dest.writeInt(dueDateYear);
        dest.writeBoolean(repeat);
        dest.writeBoolean(isStarred);
        dest.writeInt(myDayDay);
        dest.writeInt(myDayMonth);
        dest.writeInt(myDayYear);
    }

    // Getters

    /**
     * Gets the id of this task
     *
     * @return this task's id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of this task
     *
     * @return this task's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the note of this task
     *
     * @return this task's note
     */
    public String getNote() {
        return note;
    }

    /**
     * Gets the day of the month this task is due
     *
     * @return this task's due date day
     */
    public int getDueDateDay() {
        return dueDateDay;
    }

    /**
     * Gets the month this task is due
     *
     * @return this task's due date month
     */
    public int getDueDateMonth() {
        return dueDateMonth;
    }

    /**
     * Gets the year this task is due
     *
     * @return this task's due date year
     */
    public int getDueDateYear() {
        return dueDateYear;
    }

    /**
     * Gets the repeat flag of this task
     *
     * @return this task's repeat flag
     */
    public boolean isRepeat() {
        return repeat;
    }

    public boolean isStarred() {
        return isStarred;
    }

    // Setters

    /**
     * Changes the id of the task (will only be used by Room. Required to compile.) // TODO: Check if this is the right implementation
     *
     * @param id This task's new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Changes the title of this task
     *
     * @param title This task's new title
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     * Changes the note of this task
     *
     * @param note This task's new note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Changes the due date day of this task
     *
     * @param dueDateDay This task's new due date day
     */
    public void setDueDateDay(int dueDateDay) {
        this.dueDateDay = dueDateDay;
    }

    /**
     * Changes the due date month of this task
     *
     * @param dueDateMonth This task's new due date month
     */
    public void setDueDateMonth(int dueDateMonth) {
        this.dueDateMonth = dueDateMonth;
    }

    /**
     * Changes the due date year of this task
     *
     * @param dueDateYear This task's new due date year
     */
    public void setDueDateYear(int dueDateYear) {
        this.dueDateYear = dueDateYear;
    }

    /**
     * Changes the repeat flag of this task
     *
     * @param repeat This task's new repeat flag
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void setIsStarred(boolean isStarred) {
        this.isStarred = isStarred;
    }

    public void setMyDayDay(int myDayDay) {
        this.myDayDay = myDayDay;
    }

    public void setMyDayMonth(int myDayMonth) {
        this.myDayMonth = myDayMonth;
    }

    public void setMyDayYear(int myDayYear) {
        this.myDayYear = myDayYear;
    }

}
