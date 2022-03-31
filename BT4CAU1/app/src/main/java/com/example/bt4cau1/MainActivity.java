package com.example.bt4cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btViewContactInfo;
    EditText name, email, project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        project = (EditText) findViewById(R.id.project);

        btViewContactInfo = (Button) findViewById(R.id.btViewContactInfo);
        btViewContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGetContactInfo = new Intent(getApplicationContext(), ViewContactInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name.getText().toString());
                bundle.putString("email", email.getText().toString());
                bundle.putString("project", project.getText().toString());
                iGetContactInfo.putExtras(bundle);
                startActivity(iGetContactInfo);
            }
        });
    }
}