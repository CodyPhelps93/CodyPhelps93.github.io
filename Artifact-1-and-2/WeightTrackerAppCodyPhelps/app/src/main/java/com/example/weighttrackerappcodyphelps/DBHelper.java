package com.example.weighttrackerappcodyphelps;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    private final Context context;
    private static final String DATABASE_NAME = "Weight_Tracker.db";
    private static final int DATABASE_VERSION = 4;
    public static final String TABLE_USER_LOGIN = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";


    public static final String TABLE_USER_INFO = "user_info";
    public static final String COLUMN_INFO_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CURRENT_WEIGHT = "current_weight";
    public static final String COLUMN_PREVIOUS_WEIGHT = "previous_weight";
    public static final String COLUMN_GOAL_WEIGHT = "goal_weight";
    public static final String CALORIE_TABLE = "calorie_table";
    public static final String CALORIE_ID = "calorie_id";
    public static final String COLUMN_CALORIE_INTAKE = "calorie_intake";
    public static final String COLUMN_BURNED_CALORIE = "burned_calorie";
    public static final String COLUMN_CALORIE_DEFICIT = "calorie_deficit";
    public static final String COLUMN_CALORIE_DATE = "calorie_date";

  // get current logged in usr
    private SharedPreferences prefs;
    private String loggedInUser;

    // Create user-login table
    private static final String CREATE_USER_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER_LOGIN + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT UNIQUE, "
            + COLUMN_PASSWORD + " TEXT);";

    private static final String CREATE_USER_INFO_TABLE = "CREATE TABLE " + TABLE_USER_INFO + "("
            + COLUMN_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ID + " INTEGER, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_AGE + " INTEGER, "
            + COLUMN_DATE + " TEXT, "
            + COLUMN_CURRENT_WEIGHT + " INTEGER, "
            + COLUMN_PREVIOUS_WEIGHT + " INTEGER, "
            + COLUMN_GOAL_WEIGHT + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USER_LOGIN + "(" + COLUMN_USER_ID + "));";

    private static final String CREATE_CALORIE_TABLE = "CREATE TABLE " + CALORIE_TABLE +"("
            + CALORIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ID + " INTEGER, "
            + COLUMN_CALORIE_INTAKE + " INTEGER, "
            + COLUMN_CALORIE_DEFICIT + " INTEGER, "
            + COLUMN_BURNED_CALORIE + " INTEGER, "
            + COLUMN_CALORIE_DATE + " TEXT, "
            + "FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USER_LOGIN + "(" + COLUMN_USER_ID + "));";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.prefs = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        this.loggedInUser = prefs.getString("loggedInUser", null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_USER_LOGIN_TABLE);
            db.execSQL(CREATE_USER_INFO_TABLE);
            db.execSQL(CREATE_CALORIE_TABLE);
        } catch (Exception e) {
            Log.e(TAG, "Error creating tables: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_LOGIN);
            db.execSQL("DROP TABLE IF EXISTS " + CALORIE_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Log.e(TAG, "Error upgrading database: " + e.getMessage());
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        try {
            db.execSQL("PRAGMA foreign_keys=ON;");
        } catch (Exception e) {
            Log.e(TAG, "Error enabling foreign keys: " + e.getMessage());
        }
    }

    // Function to get User_ID number that matches username
    private String getUserIdForUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER_LOGIN + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = null;
        String userId = null;
        try {
            cursor = db.rawQuery(query, new String[]{username});
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(COLUMN_USER_ID);
                if (columnIndex != -1) {
                    userId = cursor.getString(columnIndex);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getUserIdForUsername: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return userId;
    }

    //Function used to add users on create_account
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = -1;
        try {
            result = db.insert(TABLE_USER_LOGIN, null, values);
            if (result == -1) {
                Toast.makeText(context, "Failed to create username/password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in addUser: " + e.getMessage());
            Toast.makeText(context, "Error creating account", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
        return result;
    }

    // Add user info using addWeight button
    public long addUserInfo(String username, String name, Integer currAge, Integer currWeight, String currDate, Integer goalWeight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userValue = new ContentValues();
        String userId = getUserIdForUsername(username);
        if (userId == null) {
            Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }
        userValue.put(COLUMN_USER_ID, userId);
        userValue.put(COLUMN_NAME, name);
        userValue.put(COLUMN_AGE, currAge);
        userValue.put(COLUMN_CURRENT_WEIGHT, currWeight);
        userValue.put(COLUMN_DATE, currDate);
        userValue.put(COLUMN_PREVIOUS_WEIGHT, 0);
        userValue.put(COLUMN_GOAL_WEIGHT, goalWeight);
        long userResult = -1;
        try {
            userResult = db.insert(TABLE_USER_INFO, null, userValue);
            if (userResult == -1) {
                Toast.makeText(context, "Failed to add user info", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "addUserInfo: Created");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in addUserInfo: " + e.getMessage());
            Toast.makeText(context, "Error adding user info", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
        return userResult;
    }

    public boolean usernameExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_LOGIN + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = null;
        boolean exists = false;
        try {
            cursor = db.rawQuery(query, new String[]{username});
            exists = cursor.moveToFirst();
        } catch (Exception e) {
            Log.e(TAG, "Error in usernameExist: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return exists;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_LOGIN + " WHERE "
                + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = null;
        boolean match = false;
        try {
            cursor = db.rawQuery(query, new String[]{username, password});
            match = cursor.moveToFirst();
        } catch (Exception e) {
            Log.e(TAG, "Error in checkUser: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return match;
    }

    public Cursor readWeightData() {
        String query = "SELECT ui." + COLUMN_INFO_ID + ", ui." + COLUMN_CURRENT_WEIGHT + ", ui."
                + COLUMN_PREVIOUS_WEIGHT + ", ui." + COLUMN_DATE + ", ui." + COLUMN_GOAL_WEIGHT
                + " FROM " + TABLE_USER_INFO + " ui"
                + " JOIN " + TABLE_USER_LOGIN + " ul ON ui." + COLUMN_USER_ID + " = ul." + COLUMN_USER_ID
                + " WHERE ul." + COLUMN_USERNAME + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null && loggedInUser != null) {
            try {
                cursor = db.rawQuery(query, new String[]{loggedInUser});
            } catch (Exception e) {
                Log.e(TAG, "Error in readWeightData: " + e.getMessage());
            }
        } else {
            Log.w(TAG, "No logged-in user or database unavailable");
        }
        return cursor;
    }

    public String getUserName() {
        String query = "SELECT ui." + COLUMN_NAME + " FROM " + TABLE_USER_INFO + " ui"
                + " JOIN " + TABLE_USER_LOGIN + " ul ON ui." + COLUMN_USER_ID + " = ul." + COLUMN_USER_ID
                + " WHERE ul." + COLUMN_USERNAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String userName = null;

        if (db != null && loggedInUser != null) {
            try {
                cursor = db.rawQuery(query, new String[]{loggedInUser});
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(COLUMN_NAME);
                    if (columnIndex != -1) {
                        userName = cursor.getString(columnIndex);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in getUserName: " + e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return userName;
    }

    public int getUserAge() {
        String query = "SELECT ui." + COLUMN_AGE + " FROM " + TABLE_USER_INFO + " ui"
                + " JOIN " + TABLE_USER_LOGIN + " ul ON ui." + COLUMN_USER_ID + " = ul." + COLUMN_USER_ID
                + " WHERE ul." + COLUMN_USERNAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int userAge = 0;

        if (db != null && loggedInUser != null) {
            try {
                cursor = db.rawQuery(query, new String[]{loggedInUser});
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(COLUMN_AGE);
                    if (columnIndex != -1) {
                        userAge = cursor.getInt(columnIndex);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in getUserAge: " + e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return userAge;
    }

    public int getUserGoal() {
        String query = "SELECT ui." + COLUMN_GOAL_WEIGHT + " FROM " + TABLE_USER_INFO + " ui"
                + " JOIN " + TABLE_USER_LOGIN + " ul ON ui." + COLUMN_USER_ID + " = ul." + COLUMN_USER_ID
                + " WHERE ul." + COLUMN_USERNAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int userGoal = 0;

        if (db != null && loggedInUser != null) {
            try {
                cursor = db.rawQuery(query, new String[]{loggedInUser});
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(COLUMN_GOAL_WEIGHT);
                    if (columnIndex != -1) {
                        userGoal = cursor.getInt(columnIndex);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in getUserGoal: " + e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return userGoal;
    }

    public int getCurrentWeight() {
        String query = "SELECT ui." + COLUMN_CURRENT_WEIGHT + " FROM " + TABLE_USER_INFO + " ui"
                + " JOIN " + TABLE_USER_LOGIN + " ul ON ui." + COLUMN_USER_ID + " = ul." + COLUMN_USER_ID
                + " WHERE ul." + COLUMN_USERNAME + " = ? ORDER BY ui." + COLUMN_INFO_ID + " DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int userCurrentWeight = 0;

        if (db != null && loggedInUser != null) {
            try {
                cursor = db.rawQuery(query, new String[]{loggedInUser});
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(COLUMN_CURRENT_WEIGHT);
                    if (columnIndex != -1) {
                        userCurrentWeight = cursor.getInt(columnIndex);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in getCurrentWeight: " + e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return userCurrentWeight;
    }

    public long updateUserInfo(String name, int age, int currWeight, String currDate, int goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userValue = new ContentValues();
        String userId = getUserIdForUsername(loggedInUser);
        if (userId == null) {
            Toast.makeText(context, "No logged-in user found", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }

        String prevWeight = "0";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + COLUMN_CURRENT_WEIGHT + " FROM " + TABLE_USER_INFO
                            + " WHERE " + COLUMN_USER_ID + " = ? ORDER BY " + COLUMN_INFO_ID + " DESC LIMIT 1",
                    new String[]{userId});
            if (cursor != null && cursor.moveToFirst()) {
                int prevWeightIndex = cursor.getColumnIndex(COLUMN_CURRENT_WEIGHT);
                if (prevWeightIndex != -1) {
                    prevWeight = cursor.getString(prevWeightIndex);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in updateUserInfo: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        userValue.put(COLUMN_USER_ID, userId);
        userValue.put(COLUMN_NAME, name);
        userValue.put(COLUMN_AGE, age);
        userValue.put(COLUMN_CURRENT_WEIGHT, currWeight);
        userValue.put(COLUMN_DATE, currDate);
        userValue.put(COLUMN_PREVIOUS_WEIGHT, prevWeight);
        userValue.put(COLUMN_GOAL_WEIGHT, goal);

        long userResult = -1;
        try {
            userResult = db.insert(TABLE_USER_INFO, null, userValue);
            if (userResult == -1) {
                Toast.makeText(context, "Failed to add Name or Weight", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Weight added successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in updateUserInfo: " + e.getMessage());
            Toast.makeText(context, "Error adding weight", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
        return userResult;
    }

    public long deleteWeightItem(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        int itemId = position;
        if (position == 1) {
            return -1;
        }
        String userId = getUserIdForUsername(loggedInUser);
        if (userId == null) {
            db.close();
            return -1;
        }
        long result = -1;
        try {
            result = db.delete(TABLE_USER_INFO, COLUMN_INFO_ID + " = ? AND " + COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(itemId), userId});
            Log.d("DeleteItem", "Deleting item with ID: " + itemId + " for user: " + loggedInUser);
        } catch (Exception e) {
            Log.e(TAG, "Error in deleteItem: " + e.getMessage());
        } finally {
            db.close();
        }
        return result;
    }


    public long insertCalorieInfo(int intakeCal, int burnedCal, int deficit, String calDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userValue = new ContentValues();
        String userId = getUserIdForUsername(loggedInUser);
        if (userId == null) {
            Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }
        userValue.put(COLUMN_USER_ID, userId);
        userValue.put(COLUMN_CALORIE_INTAKE, intakeCal);
        userValue.put(COLUMN_BURNED_CALORIE, burnedCal);
        userValue.put(COLUMN_CALORIE_DEFICIT, deficit);
        userValue.put(COLUMN_CALORIE_DATE, calDate);
        long userResult = -1;
        try {
            userResult = db.insert(CALORIE_TABLE, null, userValue);
            if (userResult == -1) {
                Toast.makeText(context, "Failed to add calorie info", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Calorie info added successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in insertCalorieInfo: " + e.getMessage());
        } finally {
            db.close();
        }
        return userResult;
    }

    public Cursor readCalorieData() {
        String query = "SELECT ui." + CALORIE_ID + ", ui." + COLUMN_CALORIE_INTAKE + ", ui."
                + COLUMN_BURNED_CALORIE + ", ui." + COLUMN_CALORIE_DEFICIT + ", ui." + COLUMN_CALORIE_DATE
                + " FROM " + CALORIE_TABLE + " ui"
                + " JOIN " + TABLE_USER_LOGIN + " ul ON ui." + COLUMN_USER_ID + " = ul." + COLUMN_USER_ID
                + " WHERE ul." + COLUMN_USERNAME + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null && loggedInUser != null) {
            try {
                cursor = db.rawQuery(query, new String[]{loggedInUser});
            } catch (Exception e) {
                Log.e(TAG, "Error in readWeightData: " + e.getMessage());
            }
        } else {
            Log.w(TAG, "No logged-in user or database unavailable");
        }
        return cursor;
    }

    public long deleteCalorieData(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        int itemId = position;
        if (position == 1) {
            return -1;
        }
        String userId = getUserIdForUsername(loggedInUser);
        if (userId == null) {
            db.close();
            return -1;
        }
        long result = -1;
        try {
            result = db.delete(CALORIE_TABLE, CALORIE_ID + " = ? AND " + COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(itemId), userId});
            Log.d("DeleteItem", "Deleting item with ID: " + itemId + " for user: " + loggedInUser);
        } catch (Exception e) {
            Log.e(TAG, "Error in deleteItem: " + e.getMessage());
        } finally {
            db.close();
        }
        return result;
    }


}
