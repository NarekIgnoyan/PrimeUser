package com.example.narek.primeuserloginregister.MainPage;

/////// android:windowSoftInputMode="adjustNothing|adjustPan"

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narek.primeuserloginregister.Common.RequesttClasses.Registration;
import com.example.narek.primeuserloginregister.CustomSpeenerAdapter;
import com.example.narek.primeuserloginregister.Home.HomeActivity;
import com.example.narek.primeuserloginregister.R;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.Login;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.IPC_Application;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.Responses;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener  {
    private Button loginText,registerText,forgotText;
    private ShimmerTextView myShimmerTextView;
    private int myColorValue;
    private LinearLayout loginLayout,registerLayout,mainLayout;
    private TextInputLayout editMail,editPass,editRePass,editName,editLName,editPhone , logEditUserName , logEditPassword;
    private Button logBut,regBut;
    private RadioButton maleBut , femaleBut;
    private Shimmer shimmer;
    private   Spinner spin;
    private  CustomSpeenerAdapter customAdapter;
    public static int SuccessToast = 0;
    private SharedPreferences pref_token,pref_card,pref_language;
    private SharedPreferences.Editor editor_token,editor_card,editor_language;
    public  static final String  PREFERENCE_TOKEN = "com.prime.primeuser.TOKEN";
    public  static final String  PREFERENCE_CARD_DETAILS = "com.prime.primeuser.CARD";
    public  static final String  PREFERENCE_LANGUAGE = "language";
    private MyCustomDialog myCustomDialog;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String gender;
    private  Button button;
    public static String testToken;

    public static String language="a";
    public static String sharedLanguage  = "";
    public static boolean registerSuccess = false;

    String[] countryNames={"English","Russian","Armenian"};
    int flags[] = {R.drawable.us, R.drawable.ru,R.drawable.am};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managePreferences();
        initLayouts();
        initEditTexts();
        initTextFields();
        initButtons();
        makeShimmer();


        passChanged();

        radioGroup = (RadioGroup)findViewById(R.id.radioGend);
        myCustomDialog = new MyCustomDialog();
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);


            customAdapter = new CustomSpeenerAdapter(getApplicationContext(), flags, countryNames);
            spin.setAdapter(customAdapter);





        registrationSuccess();
        justRegisterBitch();



       // spinetLanguage();
//        if (language.equals("Russian")){
//            setLanguageRussian();
//        }






    }




    public void loginSuccess(View view){

//        if (!validate(logEditUserName.getEditText().getText().toString())){
//            logEditUserName.getEditText().setError("** Enter valid Email");
//            logEditUserName.getEditText().requestFocus();
//        }if (validate(logEditUserName.getEditText().getText().toString())){
            //login(logEditUserName.getEditText().getText().toString(),logEditPassword.getEditText().getText().toString());
            login("na.ignoyan@yandex.ru","Prime2009");

       // }


    }


    public void justRegisterBitch(){
        button = (Button)findViewById(R.id.registerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isGenderChoosen()){
                    //Toast.makeText(MainActivity.this , gender , Toast.LENGTH_SHORT).show();


                   checkReg(editMail.getEditText().getText().toString() , editPass.getEditText().getText().toString(),
                           editName.getEditText().getText().toString(),editLName.getEditText().getText().toString() ,
                           gender , editPhone.getEditText().getText().toString());
                    Toast.makeText(MainActivity.this , editMail.getEditText().getText().toString() , Toast.LENGTH_SHORT).show();

                }

            }
        });
    }



    public boolean isGenderChoosen(){


            try {
                int selectedId = radioGroup.getCheckedRadioButtonId();


                radioButton = (RadioButton) findViewById(selectedId);

                if ( radioButton.getText().equals("Male")) {
                    gender = "1";
                }

                if ( radioButton.getText().equals("Female")) {
                    gender="0";

                }
                return true;
            }catch (Exception e){
                Toast.makeText(MainActivity.this , "Choose Gender" , Toast.LENGTH_SHORT).show();
                return false;

            }


    }



    public void checkReg(final String eMail, String pass, String fName, String lName, String male, String phone){
       IPC_Application.i().w().register
       ("registration",eMail,pass,fName,lName,male,phone,"1986-07-25",null,null,null,null).enqueue(new Callback<Responses<Registration>>() {
           @Override
           public void onResponse(Call<Responses<Registration>> call, Response<Responses<Registration>> response) {
               final String messegeJson;

               if (response.code()==200){

                   if (response.body().status==200){
                       messegeJson = response.body().message;
                       if (messegeJson.equals("success")){
                           myCustomDialog.displayDialogWindowForComfirm(MainActivity.this ,eMail);
                       }
                   }
               }

           }

           @Override
           public void onFailure(Call<Responses<Registration>> call, Throwable t) {
               Toast.makeText(MainActivity.this , "JSon Failure" , Toast.LENGTH_LONG).show();

           }
       });

//        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
//                .setTitleText("Oops...")
//                .setContentText("Something went wrong!")
//                .show();

    }

    public void initEditTexts(){


        editMail        = (TextInputLayout) findViewById(R.id.regMail);
        editPass        =  (TextInputLayout) findViewById(R.id.regPass);
        editRePass      =  (TextInputLayout) findViewById(R.id.regRePass);
        editName        =  (TextInputLayout) findViewById(R.id.regFirstName);
        editLName       =  (TextInputLayout) findViewById(R.id.regLastName);
        editPhone       =  (TextInputLayout) findViewById(R.id.regPhone);
        logEditUserName = (TextInputLayout)findViewById(R.id.logUsername);
        logEditPassword = (TextInputLayout)findViewById(R.id.logPassword);

    }


    public void initTextFields(){
        loginText = (Button) findViewById(R.id.myLogButton) ;
        registerText = (Button) findViewById(R.id.myRegButton) ;
        forgotText = (Button) findViewById(R.id.myForgotButton);
    }

    public void initButtons(){
        logBut = (Button)findViewById(R.id.loginButton);
        regBut = (Button)findViewById(R.id.registerButton);
        maleBut = (RadioButton)findViewById(R.id.maleRad);
        femaleBut = (RadioButton)findViewById(R.id.femaleRad);
    }


    public void forgotButton(View view){


        myCustomDialog.displayDialogWindow(this);


    }

    private void managePreferences(){

        pref_token = getApplicationContext().getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE);
        pref_card = getApplicationContext().getSharedPreferences(PREFERENCE_CARD_DETAILS, Context.MODE_PRIVATE);
        pref_language = getApplicationContext().getSharedPreferences(PREFERENCE_LANGUAGE, Context.MODE_PRIVATE);
        editor_token = pref_token.edit();
        editor_card = pref_card.edit();
        editor_language = pref_language.edit();
        editor_language.putString(PREFERENCE_LANGUAGE , language);





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

    public void login(final String mail , String pass){
        //"nareksench@yandex.ru","qw123456"
        IPC_Application.i().w().login("login",mail,pass).enqueue(new Callback<Responses<Login>>() {

            @Override
            public void onResponse(Call<Responses<Login>> call, retrofit2.Response<Responses<Login>> response) {
                if(response.code() == 200){

                    String miban;
                    if (response.body().status==200){
                         miban = response.body().message;
                        Toast.makeText(MainActivity.this, miban, Toast.LENGTH_SHORT).show();
                        Log.d("req", "onResponse: ");
                        editor_token.putString("token",response.body().content.token);
                        testToken = response.body().content.token;
                        editor_card.putString("cardNumber",response.body().content.primeCardNumber);
                        editor_card.putString("userName",String.valueOf(response.body().content.firstName+" "+response.body().content.lastName));
                        editor_card.commit();
                        editor_token.commit();
                        Intent show = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(show);
                    }
                    String langTittle = "";
                    String lang = "";
                    if (response.body().status==401){
                        miban = response.body().message;
                        if (miban.equals("mail not confirmed")){
                            myCustomDialog.displayDialogWindowForComfirm(MainActivity.this , mail);
                        }
                        if (miban.equals("unauthorized user")){

                            if (MainActivity.language.equals("English")){
                                langTittle = "Oops...";
                                lang = "Unouthorized user!";
                            }
                            if (MainActivity.language.equals("Russian")){
                                langTittle = "Упс...";
                                lang = "Неопознанный пользователь!";
                            }
                            if (MainActivity.language.equals("Armenian")){
                                langTittle = "Օ ոչ...";
                                lang = "Անհայտ օգտատեր!";
                            }
                            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(langTittle)
                                    .setContentText(lang)
                                    .show();
                        }
                        if (miban.equals("wrong mail or password")){

                            if (MainActivity.language.equals("English")){
                                langTittle = "Oops...";
                                lang = "Wrong mail or password!";
                            }
                            if (MainActivity.language.equals("Russian")){
                                langTittle = "Упс...";
                                lang = "Неправильный логин или пароль";
                            }
                            if (MainActivity.language.equals("Armenian")){
                                langTittle = "Օ ոչ...";
                                lang = "Սխալ Էլ․Հասցե կամ գաղտնաբառ";
                            }
                            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(langTittle)
                                    .setContentText(lang)
                                    .show();
                        }
                   }
                    if (response.body().status==501){
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Sorry")
                                .setContentText("Something goes wrong")
                                .show();

                    }




                }else{
                   Toast.makeText(MainActivity.this,"Something goes WRONG",Toast.LENGTH_LONG).show();
                }

            }



            @Override
            public void onFailure(Call<Responses<Login>> call, Throwable t) {


                Toast.makeText(MainActivity.this, t.getMessage() + " : " + t.getCause(), Toast.LENGTH_SHORT).show();
                Log.d("MYPROJ", "onFailure: " + t.getMessage() + " : " + t.getCause());

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        //Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();
//        if (!language.equals("")){
//            countryNames[position] = language;
//        }else {
       // if (language.equals("")){
            language = countryNames[position];
      //  editor_language.putString("lang" , language);
      //  editor_language.commit();

       // }

      //  }




       spinetLanguage();
    }

    public void passChanged() {
        if (SuccessToast == 1) {
            Toast.makeText(this,
                    "Password changed", Toast.LENGTH_LONG).show();
            switch (language) {
                case "English":
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Success")
                            .setContentText("Password was changed!")
                            .show();
                    break;
                case "Russian":
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Готово")
                            .setContentText("Пароль был изменен!")
                            .show();
                    break;
                case "Armenian":
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Պատրաստ է")
                            .setContentText("Գաղտնաբառը փոխված է!")
                            .show();
                    break;

            }
            SuccessToast--;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void setLanguageEnglish(){
        loginText.setText("Login");
        registerText.setText("Register");
        registerText.setTextSize(25);
        logEditUserName.setHint("Email");
        logEditPassword.setHint("Password");
        logBut.setText("Login");
        forgotText.setText("Forgot Password?");
        editMail.setHint("Email");
        editPass.setHint("Password");
        editRePass.setHint("Reenter Password");
        editName.setHint("First Name");
        editLName.setHint("Last Name");
        maleBut.setText("Male");
        femaleBut.setText("Female");
        editPhone.setHint("Phone +374");
        regBut.setText("Register");

    }
    public void setLanguageRussian(){
        loginText.setText("Войти");
        registerText.setText("Регистрация");
        registerText.setTextSize(24);
        logEditUserName.setHint("Имейл");
        logEditPassword.setHint("Пароль");
        logBut.setText("Войти");
        forgotText.setText("Забыли Пароль?");
        editMail.setHint("Имейл");
        editPass.setHint("Пароль");
        editRePass.setHint("Повторите Пароль");
        editName.setHint("Имя");
        editLName.setHint("Фамилия");
        maleBut.setText("Муж.");
        femaleBut.setText("Жен.");
        editPhone.setHint("Телефон +374");
        regBut.setText("Регистрация");

    }
    public void setLanguageArmenian(){
        loginText.setText("Մուտք");
        registerText.setText("Գրանցում");
        registerText.setTextSize(25);
        logEditUserName.setHint("Իմեյլ");
        logEditPassword.setHint("Գաղտնաբառ");
        logBut.setText("Մուտք գործել");
        forgotText.setText("Մոռացել եք գաղտնաբառը՞");
        editMail.setHint("Իմեյլ");
        editPass.setHint("Գաղտնաբառ");
        editRePass.setHint("Կրկին Գաղտնաբառ");
        editName.setHint("Անուն");
        editLName.setHint("Ազգանուն");
        maleBut.setText("Արական");
        femaleBut.setText("Իգական");
        editPhone.setHint("Հեռախոսահամար +374");
        regBut.setText("Գրանցվել");

    }

    public void spinetLanguage(){

//            spin = (Spinner) findViewById(R.id.spinner);
//   spin.setOnItemSelectedListener(this);
//    customAdapter=new CustomSpeenerAdapter(getApplicationContext(),flags,countryNames);
       // language = pref_language.getString("lang", "DEFAULT");
        //spin.setAdapter(customAdapter);
   switch (language){
       case "English": setLanguageEnglish();
//           countryNames[0]= "English";
//           countryNames[1]= "Russian";
//           countryNames[2]= "Armenian";
//           flags[0] = R.drawable.us;
//           flags[1] = R.drawable.ru;
//           flags[2] = R.drawable.am;
           // spin.setAdapter(customAdapter);

           break;
       case "Russian": setLanguageRussian();
//           countryNames[0]= "Russia";
//           countryNames[1]= "Armenian";
//           countryNames[2]= "English";
//           flags[0] = R.drawable.ru;
//           flags[1] = R.drawable.am;
//           flags[2] = R.drawable.us;
          // spin.setAdapter(customAdapter);
           break;
       case "Armenian": setLanguageArmenian();
//           countryNames[0]= "Armenian";
//           countryNames[1]= "English";
//           countryNames[2]= "Russian";
//           flags[0] = R.drawable.am;
//           flags[1] = R.drawable.us;
//           flags[2] = R.drawable.ru;
           //spin.setAdapter(customAdapter);
           break;
   }


//   if (language.equals("a")){
//       spin.setAdapter(customAdapter);
//
//   }
    }



    public void registrationSuccess(){
        if (registerSuccess==true){

            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success")
                    .setContentText("Registration completed!")
                    .show();
            registerSuccess=false;
        }
    }



}





