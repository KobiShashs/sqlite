package com.example.user.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText mEditTextName,mEditTextAddress,mEditTextPhone,mEditTextAge;
    private Button mButtonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final DatabaseManager databaseManager = new DatabaseManager(this);

        mEditTextName = (EditText)findViewById(R.id.edit_text_name);
        mEditTextAddress = (EditText)findViewById(R.id.edit_text_address);
        mEditTextPhone = (EditText)findViewById(R.id.edit_text_phone);
        mEditTextAge = (EditText)findViewById(R.id.edit_text_age);
        mButtonAdd = (Button)findViewById(R.id.button_add);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymMember newGymMember = new GymMember(mEditTextName.getText().toString(),
                                                        mEditTextAddress.getText().toString(),
                                                        mEditTextPhone.getText().toString(),
                                                        Float.valueOf(mEditTextAge.getText().toString()));
                long result = databaseManager.insert(newGymMember);
                if(result==-1){
                    Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(InsertActivity.this, "Insert Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
