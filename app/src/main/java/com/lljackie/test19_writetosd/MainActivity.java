package com.lljackie.test19_writetosd;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String FileName = "myfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_write = (Button) findViewById(R.id.bt_write);
        Button bt_read = (Button) findViewById(R.id.bt_read);

        Toast.makeText(MainActivity.this,
                Environment.getExternalStorageDirectory().getAbsolutePath(),
                Toast.LENGTH_SHORT).show();
        bt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    OutputStream out = null;

                    try {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = null;
                        myfile = new File(file.getCanonicalPath() + "/" + FileName);
                        FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                        out = new BufferedOutputStream(fileOutputStream);
                        String content = "Stop!!!";
                        try {
                            out.write(content.getBytes(StandardCharsets.UTF_8));
                        } finally {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

        bt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {

                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/" + FileName);
                        FileInputStream fileInputStream = new FileInputStream(myfile);
                        in = new BufferedInputStream(fileInputStream);

                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, stringBuilder.toString(),
                                    Toast.LENGTH_LONG).show();
                        } finally {
                            in.close();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
