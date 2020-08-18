package com.example.celcomunistall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnOwner;
    private Button btnRemove;
    private Button btnUnInstall;
    private Button btnCommand;
    private EditText mExecuteCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOwner = (Button) findViewById(R.id.btnOwner);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnUnInstall = (Button) findViewById(R.id.btnUnInstall);
        btnCommand = (Button) findViewById(R.id.btnExecute);

        btnOwner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cmdline = "dpm set-device-owner com.cytophone.services/.DeviceAdministrator";
                executeCommand(cmdline, "set-device-owner -> ok");
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cmdline = "dpm remove-active-admin com.cytophone.services/.DeviceAdministrator";
                executeCommand(cmdline, "remove-active-admin -> ok");
            }
        });

        btnUnInstall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cmdline = "pm uninstall com.cytophone.services";
                executeCommand(cmdline, "uninstall -> ok");
            }
        });

        btnCommand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mExecuteCommand = (EditText) findViewById(R.id.txtcmdLine);
                String cmd = mExecuteCommand.getText().toString();

                if (cmd.length() > 0)
                    executeCommand(cmd, "command execute -> ok");
            }
        });

    }

    private void executeCommand(String cmdline, String msg){
        try {
            Runtime.getRuntime().exec(cmdline);

            Toast.makeText(MainActivity.this.getBaseContext(), msg, Toast.LENGTH_SHORT).
                    show();
        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(MainActivity.this.getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).
                    show();
        }
    }

}