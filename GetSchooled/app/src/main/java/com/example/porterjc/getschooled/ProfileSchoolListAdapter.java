package com.example.porterjc.getschooled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ProfileSchoolListAdapter extends ArrayAdapter<SchoolObject> {

    private Context mContext;
    private int mResource;
    private SchoolObject[] mSchoolObject;

    public ProfileSchoolListAdapter(Context context, int resource, SchoolObject[] objects) {
        super(context, resource, objects);

        mContext= context;
        mResource = resource;
        mSchoolObject = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((MainActivity) mContext).getLayoutInflater();
        View row = inflater.inflate(mResource,parent,false);

        //ListView Specifics Here


        return row;
    }
}

//    // Becomes the filename of the database
//    private static final String DATABASE_NAME = "tasks.db";
//    // Only one table in this database
//    private static final String TABLE_NAME = "Tasks";
//    // We increment this every time we change the database schema which will
//    // kick off an automatic upgrade
//    private static final int DATABASE_VERSION = 1;
//
//    public static final String KEY_ID = "_id"; // Android naming convention for
//    // IDs
//    public static final String KEY_ASSIGNMENT = "asignment";
//    public static final String KEY_CLASS = "class";
//
//    public static final String KEY_DAY = "day";
//    public static final String KEY_MONTH = "month";
//    public static final String KEY_YEAR = "year";
//    private static final String DROP_STATEMENT = "DROP TABLE IF EXISTS "
//            + TABLE_NAME;
//    private static final String CREATE_STATEMENT;
//    static {
//        StringBuilder sb = new StringBuilder();
//        sb.append("CREATE TABLE " + TABLE_NAME + " (");
//        sb.append(KEY_ID + " integer primary key autoincrement, ");
//        sb.append(KEY_ASSIGNMENT + " text, ");
//        sb.append(KEY_CLASS + " text, ");
//        sb.append(KEY_DAY + " integer, ");
//        sb.append(KEY_MONTH + " integer, ");
//        sb.append(KEY_YEAR + " inetger");
//        sb.append(")");
//        CREATE_STATEMENT = sb.toString();
//    }
//
//    private SQLiteOpenHelper mOpenHelper;
//    private SQLiteDatabase mDatabase;
//
//    public TaskDataAdapter(Context context) {
//        // Create a SQliteHelper
//        mOpenHelper = new TaskDBHelper(context);
//    }
//
//    public void open() {
//        // Opens the database
//        mDatabase = mOpenHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        // Closes the database
//        mDatabase.close();
//    }
//
//    private ContentValues getContentValuesFromTask(Task task) {
//        ContentValues row = new ContentValues();
//        row.put(KEY_ASSIGNMENT, task.getName());
//        row.put(KEY_CLASS, task.getCourse());
//        row.put(KEY_DAY, task.getDayOfMonthDue());
//        row.put(KEY_MONTH, task.getMonthDue());
//        row.put(KEY_YEAR, task.getYearDue());
//        return row;
//    }
//
//    public void addTask(Task task) {
//        ContentValues row = getContentValuesFromTask(task);
//        long newID = mDatabase.insert(TABLE_NAME, null, row);
//        task.setId(newID);
//    }
//
//    public void deleteTask(Task task) {
//        String whereClause = KEY_ID + "=" + task.getId();
//        mDatabase.delete(TABLE_NAME, whereClause, null);
//    }
//
//    public Task getTaskFromCursor(Cursor cursor) {
//        String name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ASSIGNMENT));
//        String course = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CLASS));
//        int year = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_YEAR));
//        int month = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_MONTH));
//        int day = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DAY));
//        GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
//        Task task = new Task(name, course, dueDate);
//        task.setId(cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)));
//        return task;
//
//    }
//
//    public void setAllTasks(ArrayList<Task> tasks) {
//        String[] columns = null; //gives all columns from table
//        Cursor cursor = mDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
//        if(cursor == null || !cursor.moveToFirst()) {
//            return;
//        }
//        tasks.clear();
//        do{
//            tasks.add(getTaskFromCursor(cursor));
//        } while (cursor.moveToNext());
//        Collections.sort(tasks);
//    }
//
//
//    private class TaskDBHelper extends SQLiteOpenHelper {
//
//        public TaskDBHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(CREATE_STATEMENT);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL(DROP_STATEMENT);
//            onCreate(db);
//        }
//    }
//}
//}
