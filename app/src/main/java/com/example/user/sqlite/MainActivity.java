package com.example.user.sqlite;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mButtonInsert, mButtonUpdate, mButtonDelete, mButtonSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Don't forget...
        final DatabaseManager databaseManager = new DatabaseManager(this);

        //Buttons
        mButtonInsert = (Button)findViewById(R.id.button_insert);
        mButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Insert", Toast.LENGTH_SHORT).show();
            }
        });

        mButtonUpdate = (Button)findViewById(R.id.button_update);
        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
            }
        });
        mButtonDelete = (Button)findViewById(R.id.button_delete);
        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Title");

                Button button = (Button) dialog.findViewById(R.id.dialog_ok);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        EditText edit=(EditText)dialog.findViewById(R.id.dialog_edit);
                        String text=edit.getText().toString();
                        int result = databaseManager.delete(text);
                        if(result==0){
                            Toast.makeText(MainActivity.this, "Not deleted...", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, text+" deleted", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();


                    }
                });


                dialog.show();
            }
        });
        mButtonSelect = (Button)findViewById(R.id.button_select);
        mButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Select", Toast.LENGTH_SHORT).show();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Select * from GymMembers\n");
                List<GymMember> allGymMembers = databaseManager.getAllGymMembers();
                for(GymMember member:allGymMembers){
                    stringBuilder.append("Name: "+ member.getmName()+" Phone: "+member.getmPhoneNumber()+" Address: "+member.getmAddress()+"Age: "+member.getmAge()+"\n");
                }
                Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();

            }
        });





        GymMember gymMember0 = new GymMember("Kobi","Hertzel 33","077-2131231",29.8f);
        GymMember gymMember1 = new GymMember("Yair","Ellie 24","056-2321211",21);
        GymMember gymMember2 = new GymMember("Yael","Amit 8","053-12144444",34);
        GymMember gymMember3 = new GymMember("Yair","Ellie 24","056-2321211",21);
        GymMember gymMember4 = new GymMember("Yair","Ellie 24","056-2321211",21);

        databaseManager.insert(gymMember0);
        databaseManager.insert(gymMember1);
        databaseManager.insert(gymMember2);
        databaseManager.insert(gymMember3);
        databaseManager.insert(gymMember4);

        databaseManager.updateGymMemberPhoneNumber(gymMember0,"050-1234567");

        databaseManager.delete(gymMember1);




        

    }
}
