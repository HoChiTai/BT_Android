package com.example.bt4cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewContactInfoActivity extends AppCompatActivity {

    TextView nameInfo, emailInfo, projectInfo;
    Button btfinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact_info);

        nameInfo = (TextView) findViewById(R.id.nameInfo);
        emailInfo = (TextView) findViewById(R.id.emailInfo);
        projectInfo = (TextView) findViewById(R.id.projectInfo);

        btfinish = (Button) findViewById(R.id.btFinish);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        String project = bundle.getString("project");

        nameInfo.setText(name);
        emailInfo.setText(email);
        projectInfo.setText(project);

        btfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}