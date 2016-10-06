package com.example.narek.primeuserloginregister.MainPage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.example.narek.primeuserloginregister.PasswordForget;
import com.example.narek.primeuserloginregister.R;

public class MyCustomDialog extends AppCompatActivity {
    public static Dialog fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_dialog);
    }
    public static void displayDialogWindow(final Context context)
    {
        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, android.R.style.Theme_DeviceDefault_Light_Dialog));
        final LayoutInflater factory = LayoutInflater.from(context);
        final View f = factory.inflate(R.layout.activity_my_custom_dialog, null);


        loginDialog.setView(f);

        fs = loginDialog.create();
        fs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        fs.setCancelable(false);




        Button submit = (Button)f.findViewById(R.id.submit);
        Button cancel = (Button)f.findViewById(R.id.cancel);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                EditText email_address = (EditText)f.findViewById(R.id.follower_email);

                String follower_email = email_address.getText().toString();



                if (!MainActivity.validate(follower_email)){
                    email_address.setError("** Please enter a valid email address");


                }else {

                    Intent intent = new Intent(context, PasswordForget.class);
                    context.startActivity(intent);

                }

            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fs.dismiss();
            }
        });
        fs.show();


       Window window = fs.getWindow();
       window.setLayout(1200, ActionBar.LayoutParams.WRAP_CONTENT);

    }
}
