package com.example.user.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseManager {

    private final SQLiteDatabase mDatabase;

    public DatabaseManager(Context context){
        DatabaseOpenHelper helper = new DatabaseOpenHelper(context);
        mDatabase = helper.getWritableDatabase();
    }

    public long insert(GymMember member){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseOpenHelper.COL_NAME,member.getmName());
        contentValues.put(DatabaseOpenHelper.COL_ADDRESS,member.getmAddress());
        contentValues.put(DatabaseOpenHelper.COL_PHONE_NUMBER,member.getmPhoneNumber());
        contentValues.put(DatabaseOpenHelper.COL_AGE,member.getmAge());
        //contentValues.put(DatabaseOpenHelper.COL_EMAIL,member.);

        return mDatabase.insert(DatabaseOpenHelper.TABLE_NAME_GYM_MEMBERS,null,contentValues);

    }

    public int updateGymMemberPhoneNumber(GymMember member,String phoneNumber){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseOpenHelper.COL_NAME,member.getmName());
        contentValues.put(DatabaseOpenHelper.COL_ADDRESS,member.getmAddress());
        contentValues.put(DatabaseOpenHelper.COL_PHONE_NUMBER,member.getmPhoneNumber());
        contentValues.put(DatabaseOpenHelper.COL_AGE,member.getmAge());
        //contentValues.put(DatabaseOpenHelper.COL_EMAIL,member.);

        String where = DatabaseOpenHelper.COL_NAME + "=?";
        //String where = DatabaseOpenHelper.COL_NAME + "=? " + DatabaseOpenHelper.COL_ADDRESS + "=?";
        String[] whereArgs = {member.getmName()};
        //String[] whereArgs = {member.getmName(),member.getmAddress()};
        return mDatabase.update(DatabaseOpenHelper.TABLE_NAME_GYM_MEMBERS,contentValues,where,whereArgs);
    }

    public int delete(GymMember member){
        String where = DatabaseOpenHelper.COL_NAME + "=?";
        //String where = DatabaseOpenHelper.COL_NAME + "=? " + DatabaseOpenHelper.COL_ADDRESS + "=?";
        String[] whereArgs = {member.getmName()};
        //String[] whereArgs = {member.getmName(),member.getmAddress()};

        return mDatabase.delete(DatabaseOpenHelper.TABLE_NAME_GYM_MEMBERS,where,whereArgs);
    }

    public int delete(String name){
        String where = DatabaseOpenHelper.COL_NAME + "=?";
        String[] whereArgs = {name};
        return mDatabase.delete(DatabaseOpenHelper.TABLE_NAME_GYM_MEMBERS,where,whereArgs);
    }

    public List<GymMember> getAllGymMembers(){
        Cursor cursor = mDatabase.query(DatabaseOpenHelper.TABLE_NAME_GYM_MEMBERS,null,null,null,null,null,null);

        List<GymMember> allGymMembers = Collections.emptyList();

        if(cursor.getColumnCount()>0){

            allGymMembers = new ArrayList<>();

            while (cursor.moveToNext()){
                int indexName = cursor.getColumnIndex(DatabaseOpenHelper.COL_NAME);
                int indexAddress = cursor.getColumnIndex(DatabaseOpenHelper.COL_ADDRESS);
                int indexPhoneNumber = cursor.getColumnIndex(DatabaseOpenHelper.COL_PHONE_NUMBER);
                int indexAge = cursor.getColumnIndex(DatabaseOpenHelper.COL_AGE);

                String name = cursor.getString(indexName);
                String address = cursor.getString(indexAddress);
                String phoneNumber = cursor.getString(indexPhoneNumber);
                float age = cursor.getFloat(indexAge);

                GymMember member = new GymMember(name,address,phoneNumber,age);

                allGymMembers.add(member);
            }
        }

        cursor.close();

        return allGymMembers;

    }


    private static class DatabaseOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "GymDatabase";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME_GYM_MEMBERS = "GymMembers";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "Name";
        private static final String COL_ADDRESS = "Address";
        private static final String COL_PHONE_NUMBER = "PhoneNumber";
        private static final String COL_AGE = "Age";
        private static final String COL_EMAIL ="Email";


        private static final String CREATE_TABLE_GYM_MEMBERS = "create table " + TABLE_NAME_GYM_MEMBERS
                + " ("
                + COL_ID + " integer primary key autoincrement, "
                + COL_NAME + " text, "
                + COL_ADDRESS + " text, "
                + COL_PHONE_NUMBER + " text, "
                + COL_AGE + " numeric, "
                + COL_EMAIL +" text"
                + ");";

        //private static final SELECT_ALL_GYM_MEMBERS = ""


        private static final String DROP_TABLE_GYM_MEMBERS = "drop table if exists " + TABLE_NAME_GYM_MEMBERS +";";

        private final Context mContext;

        public DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_GYM_MEMBERS);
            Toast.makeText(mContext, "onCreate", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE_GYM_MEMBERS);
            onCreate(db);
            Toast.makeText(mContext, "onUpgrade", Toast.LENGTH_SHORT).show();

        }
    }

}
