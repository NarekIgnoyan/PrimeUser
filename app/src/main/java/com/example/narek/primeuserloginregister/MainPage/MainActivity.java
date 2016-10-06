package com.example.narek.primeuserloginregister.MainPage;

/////// android:windowSoftInputMode="adjustNothing|adjustPan"

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narek.primeuserloginregister.AfterLogin;
import com.example.narek.primeuserloginregister.CustomSpeenerAdapter;
import com.example.narek.primeuserloginregister.PasswordForget;
import com.example.narek.primeuserloginregister.R;
import com.example.narek.primeuserloginregister.RequesttClasses.Login;
import com.example.narek.primeuserloginregister.RetrofitJackson.IPC_Application;
import com.example.narek.primeuserloginregister.RetrofitJackson.Responses;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener  {
    private TextView loginText,registerText;
    private ShimmerTextView myShimmerTextView;
    private int myColorValue;
    private LinearLayout loginLayout,registerLayout,mainLayout;
    private EditText dateText,editMail,editPass,editRePass,editName,editLName,editPhone,editAddress,editPostalCode,editCountry;
    private Shimmer shimmer;
    public static int SuccessToast = 0;

    String[] countryNames={"Armenian","Russian","English"};
    int flags[] = {R.drawable.am, R.drawable.ru,R.drawable.us};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initLayouts();
        initEditTexts();
        initTextFields();
        makeShimmer();


        if (SuccessToast == 1 ){
            Toast.makeText(this,
                    "Password changed", Toast.LENGTH_LONG).show();
            SuccessToast--;
        }


        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        CustomSpeenerAdapter customAdapter=new CustomSpeenerAdapter(getApplicationContext(),flags,countryNames);
        spin.setAdapter(customAdapter);
    }




    public void loginSuccess(View view){

        login();
    }



    public void checkReg(View view){

//        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
//                .setTitleText("Oops...")
//                .setContentText("Something went wrong!")
//                .show();

    }

    public void initEditTexts(){


        editMail        = (EditText)findViewById(R.id.mail);
        editPass        =  (EditText)findViewById(R.id.pass);
        editRePass      =  (EditText)findViewById(R.id.passRe);
        editName        =  (EditText)findViewById(R.id.f_name);
        editLName       =  (EditText)findViewById(R.id.l_name);
        editPhone       =  (EditText)findViewById(R.id.phone);
    }

    public void initTextFields(){
        loginText = (TextView)findViewById(R.id.logText) ;
        registerText = (TextView)findViewById(R.id.regText) ;
    }



    public void forgotButton(View view){


        MyCustomDialog.displayDialogWindow(this);




    }

    public void initLayouts(){
        loginLayout = (LinearLayout)findViewById(R.id.logLayout);
        registerLayout = (LinearLayout)findViewById(R.id.regLayout);
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
    }

    public void makeShimmer(){
        myShimmerTextView = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        shimmer = new Shimmer();
        changeShimmer(shimmer);
    }
    public void changeShimmer(final Shimmer s){
        s.start(myShimmerTextView);
        s.setRepeatCount(0)
                .setDuration(2000)
                .setStartDelay(500)
                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR)
                .setAnimatorListener(new Animator.AnimatorListener(){
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        s.start(myShimmerTextView);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    public void loginClick(View view){

        loginText.setTextColor(getIntColor("#1cbe9e"));
        registerText.setTextColor(getIntColor("#ffffff"));
        mainLayout.setBackgroundResource(R.drawable.logback2);
        loginLayout.setVisibility(View.VISIBLE);
        registerLayout.setVisibility(View.GONE);





    }

    public void registerClick(View view){
        loginText.setTextColor(getIntColor("#ffffff"));
        registerText.setTextColor(getIntColor("#1cbe9e"));
        mainLayout.setBackgroundResource(R.drawable.regback2);
        loginLayout.setVisibility(View.GONE);
        registerLayout.setVisibility(View.VISIBLE);



    }

    public int getIntColor(String s){
        myColorValue = Color.parseColor(s);
        return myColorValue;
    }



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public void login(){
        IPC_Application.i().w().login("login","nareksench@yandex.ru","qw123456").enqueue(new Callback<Responses<List<Login>>>() {

            @Override
            public void onResponse(Call<Responses<List<Login>>> call, retrofit2.Response<Responses<List<Login>>> response) {
                if(response.code() == 200){

                    if (response.body().status==200){
                        String miban = response.body().message;
                        Toast.makeText(MainActivity.this, miban, Toast.LENGTH_SHORT).show();
                        Log.d("req", "onResponse: ");
//                        Intent show = new Intent(MainActivity.this, AfterLogin.class);
//                        startActivity(show);
                    }



                }else{
                   Toast.makeText(MainActivity.this,"Something goes WRONG",Toast.LENGTH_LONG).show();
                }

            }



            @Override
            public void onFailure(Call<Responses<List<Login>>> call, Throwable t) {

                if(t.getMessage().startsWith("Unable to resolve host")){
                   Toast.makeText(MainActivity.this,"No Internet" , Toast.LENGTH_LONG).show();
                }
                else if(t.getMessage().startsWith("Can not deserialize")){
                    Toast.makeText(MainActivity.this,"Wrong Username or Password" , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Somethingg went wrong" , Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }




}





