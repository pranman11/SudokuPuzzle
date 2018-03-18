package com.example.pranavmanglani.sudokupuzzle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pranav Manglani on 28-02-2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME="puzzles";

    // Table Name
    private static String TABLE_PUZZLE;

    // Column names
    private static final String TAG = "tag";
    private static final String LEVEL = "level";
    private static final String PUZZLE_NUMBERS="numbers";
    private static final String TIME="time";
    private static final String SOLVED="solved";

    // Table Create Statement
    private static String CREATE_TABLE_PUZZLE;

    public DatabaseHelper(Context context, String TABLE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.TABLE_PUZZLE=TABLE_NAME;
        CREATE_TABLE_PUZZLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_PUZZLE + "(" + TAG + " INTEGER PRIMARY KEY," + PUZZLE_NUMBERS
                + " TEXT(81)," + LEVEL + " TEXT," + SOLVED + " TEXT," + TIME + " TEXT);";
        Log.i("Constructor",CREATE_TABLE_PUZZLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUZZLE);
        db.execSQL(CREATE_TABLE_PUZZLE);
        Log.i("Database","created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUZZLE);

        // create new table
        onCreate(db);
    }
    public boolean createPuzzle(com.example.pranavmanglani.sudokupuzzle.Puzzle puzzle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TAG, puzzle.getTag());
        values.put(PUZZLE_NUMBERS, puzzle.getNumbers());
        values.put(LEVEL, puzzle.getLevel());
        values.put(TIME,puzzle.getTime());
        values.put(SOLVED,String.valueOf(puzzle.isSolved()));

        // insert row
        try {
            db.insert(TABLE_PUZZLE, null, values);
        }
        catch (Exception e){
            Log.i("error","database not created");
        }
        return true;
    }

    /*
 * Updating a Puzzle
 */
    public int updatePuzzle(com.example.pranavmanglani.sudokupuzzle.Puzzle puzzle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PUZZLE_NUMBERS, puzzle.getNumbers());
        values.put(LEVEL, puzzle.getLevel());
        values.put(TIME,puzzle.getTime());
        values.put(SOLVED,puzzle.isSolved());

        // updating row
        return db.update(TABLE_PUZZLE, values, TAG + " = ?",
                new String[] { String.valueOf(puzzle.getTag())});

    }
    /*
 * Deleting a Puzzle
 */
    public void deleteToDo(int tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PUZZLE, TAG + " = ?",
                new String[] { String.valueOf(tag) });
    }
    /*
 * get single puzzle
 */
    public com.example.pranavmanglani.sudokupuzzle.Puzzle getPuzzle(int tag) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PUZZLE + " WHERE "
                + TAG + "=" + tag;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        com.example.pranavmanglani.sudokupuzzle.Puzzle puzzle = new com.example.pranavmanglani.sudokupuzzle.Puzzle();
        puzzle.setTag(c.getInt(c.getColumnIndex(TAG)));
        puzzle.setNumbers(c.getString(c.getColumnIndex(PUZZLE_NUMBERS)));
        puzzle.setLevel(c.getString(c.getColumnIndex(LEVEL)));
        puzzle.setTime(c.getString(c.getColumnIndex(TIME)));
        puzzle.setSolved(c.getString(c.getColumnIndex(SOLVED)));

        return puzzle;
    }
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
