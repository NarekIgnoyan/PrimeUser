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
import android.widget.TextView;
import android.widget.Toast;

import com.example.narek.primeuserloginregister.Common.RequesttClasses.ForgotPass;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.RegistrationComfirm;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.IPC_Application;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.Responses;
import com.example.narek.primeuserloginregister.PasswordForget;
import com.example.narek.primeuserloginregister.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCustomDialog extends AppCompatActivity {
    public static Dialog fs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_dialog);


    }

    public void displayDialogWindow(final Context context)
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


       EditText dialogMail = (EditText)f.findViewById(R.id.follower_email);
       TextView dialogTitle  =(TextView)f.findViewById(R.id.dialogTitle);
        TextView dialogTitle2  =(TextView)f.findViewById(R.id.myTittle);

        cancel.setVisibility(View.VISIBLE);
        dialogTitle2.setVisibility(View.GONE);
        dialogTitle.setTextColor(Color.BLACK);
        if (MainActivity.language.equals("Armenian")) {

            dialogTitle.setText("Մուտքագրեք ձեր իմեյլը");
            dialogMail.setHint("Իմեյլ");
            cancel.setText("Վերացնել");
            submit.setText("Հաստատել");
        }
        if (MainActivity.language.equals("English")) {

            dialogTitle.setText("Please enter your email");
            dialogMail.setHint("Email");
            cancel.setText("Cancel");
            submit.setText("Submit");
        }
        if (MainActivity.language.equals("Russian")){

            dialogTitle.setText("Введите ваш имейл");
            dialogMail.setHint("Имейл");
            cancel.setText("Назад");
            submit.setText("Далее");

        }


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                EditText email_address = (EditText)f.findViewById(R.id.follower_email);

                String follower_email = email_address.getText().toString();



                if (!MainActivity.validate(follower_email)){

                    if (MainActivity.language.equals("English")){
                        email_address.setError("** Please enter a valid email address");
                    }
                    if (MainActivity.language.equals("Russian")){
                        email_address.setError("** Пожалуйста введите форматом Имейл ");
                    }
                    if (MainActivity.language.equals("Armenian")){
                        email_address.setError("** Չկպավ ախպերս , նոռմալ բան գրի ");
                    }


                }else {

                    mailForgot(follower_email , context);

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

       window.setLayout( ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

    }
    public void displayDialogWindowForComfirm(final Context context, final String mail)
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
        TextView dialogText = (TextView)f.findViewById(R.id.myTittle);

        EditText dialogMail = (EditText)f.findViewById(R.id.follower_email);
        TextView dialogTitle  =(TextView)f.findViewById(R.id.dialogTitle);
        cancel.setVisibility(View.GONE);
        dialogText.setVisibility(View.VISIBLE);
        if (MainActivity.language.equals("Armenian")) {

            dialogTitle.setText("Վավերականցման կոդը");
            dialogText.setText("ուղղարկված է ձեր Էլ․հասցեին");
            dialogMail.setHint("Կոդ");
            submit.setText("Հաստատել");
        }
        if (MainActivity.language.equals("English")) {

            dialogTitle.setText("Comfirmation code was sent");
            dialogText.setText("to your Mail");
            dialogMail.setHint("Code from Mail");
            submit.setText("Submit");
        }
        if (MainActivity.language.equals("Russian")){

            dialogTitle.setText("Код поддтверждения отправлен на ваш Имейл");
            dialogText.setText("отправлен на ваш Имейл");
            dialogMail.setHint("Код с Мейла");
            submit.setText("Далее");

        }



        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                EditText comfCode = (EditText)f.findViewById(R.id.follower_email);

                String follower_email = comfCode.getText().toString();



                    sendComfirmRequest(mail, follower_email,context);

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

    public  void sendComfirmRequest(String mail , String comfirmCode,final Context context){
        IPC_Application.i().w().registerComfirm("mailConfirm",mail , comfirmCode).enqueue(new Callback<Responses<RegistrationComfirm>>() {
            @Override
            public void onResponse(Call<Responses<RegistrationComfirm>> call, Response<Responses<RegistrationComfirm>> response) {

                if(response.code() == 200) {

                    if (response.body().status == 200) {

                        String miban = response.body().message;
                        if (miban.equals("success")){
                            MainActivity.registerSuccess = true;

                                Intent i = new Intent(context, MainActivity.class);
                                context.startActivity(i);


                        }



                    }
                }
            }

            @Override
            public void onFailure(Call<Responses<RegistrationComfirm>> call, Throwable t) {

            }
        });
    }

    public void mailForgot(final String mail, final Context context){
        IPC_Application.i().w().forgot("forgotPassSendCode",mail).enqueue(new Callback<Responses<ForgotPass>>() {
            @Override
            public void onResponse(Call<Responses<ForgotPass>> call, Response<Responses<ForgotPass>> response) {
                if (response.code() == 200){
                    String responseMessege;
                    if (response.body().status==200){
                        responseMessege = response.body().message;
                        if (responseMessege.equals("success")){
                            Intent intent = new Intent(context, PasswordForget.class);
                            intent.putExtra("comfMail",mail);
                            context.startActivity(intent);
                        }
                        if (responseMessege.equals("wrong mail")){
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Wrong mail")
                                   // .setContentText("Cant find mail")
                                    .show();
                            // TODO: 06-Nov-16
                        }

                    }

                    if (response.body().status==500){
                        responseMessege = response.body().message;
                        if (responseMessege.equals("sql exception")){
                            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Sorry, try again")
                                     .setContentText("Something goes wrong")
                                    .show();
                            // TODO: 06-Nov-16
                        }
                        if (responseMessege.equals("send Mail error")){
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Sorry , try later")
                                    .setContentText("We have problems")
                                    .show();
                            // TODO: 06-Nov-16
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<Responses<ForgotPass>> call, Throwable t) {

            }
        });
    }


}
