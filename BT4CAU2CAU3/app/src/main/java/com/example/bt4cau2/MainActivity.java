package com.example.bt4cau2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyen sang web browser voi duong dan la https://www.google.com
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));

                // Goi so dien thoai, nho ghi user-permission ben /manifest/AndroidManifest.xml va cap quyen cho thiet bi
                // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: (+84)1234657"));

                // Chi hien thi so dien thoai, chua goi
                // Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: (+84)1234657"));

                // Hien thi danh ba dien thoai
                // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));

                // Hien thi giao dien nhan tin voi nguoi nhan tin la 123456
                // Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms: 123456"));
                // Hien thi noi dung tin nhan
                // intent.putExtra("sms_body", "Thu bay nay di choi khong?");

                // Xem anh
                // Intent intent = new Intent();
                // intent.setType("image/picture/*");
                // intent.setAction(Intent.ACTION_GET_CONTENT);

                // Nghe nhac
                // Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");

                // su dung gg map
                // String url = "https://maps.google.com/maps?" + "saddr=9.938083, -84.054430&daddr=9.926392, -84.055964";
                // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                startActivity(intent);
            }
        });
    }
}