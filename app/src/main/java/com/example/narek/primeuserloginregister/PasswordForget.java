package com.example.narek.primeuserloginregister;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.narek.primeuserloginregister.MainPage.MainActivity;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class PasswordForget extends AppCompatActivity {
    private Shimmer shimmer;
    private ShimmerTextView myShimmerTextView;
    private TextInputLayout newPass , reenterNewPass , comfirmCode;
    private TextView forgotMainTextView;
    private Button comfirmChanges;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_forget);
        initViews();
        makeShimmer();
        changeLanguage();

    }

    public void successComfirm(View view){
        MainActivity.SuccessToast++;


        Intent intent = new Intent(PasswordForget.this,MainActivity.class);
        startActivity(intent);
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

    public void changeLanguage(){
        switch (MainActivity.language){
            case "English":
                forgotMainTextView.setText("Confirm new password");
                newPass.setHint("New Password");
                reenterNewPass.setHint("Reenter Password");
                comfirmCode.setHint("Confirmation Code");
                comfirmChanges.setText("Confirm");
                break;
            case "Russian":
                forgotMainTextView.setText("Подтверждение пароля");
                newPass.setHint("Новый Пароль");
                reenterNewPass.setHint("Повторите Пароль");
                comfirmCode.setHint("Код подтверждения");
                comfirmChanges.setText("Подтвердить");
                break;
            case "Armenian":

                forgotMainTextView.setText("Հաստատում");
//                forgotMainTextView.setTextSize(25);
                newPass.setHint("Նոր Գաղտնաբառ");
                reenterNewPass.setHint("Կրկնեք Գաղտնաբառը");
                comfirmCode.setHint("Հաստատման Կոդը");
                comfirmChanges.setText("Հաստատել");
                break;
        }
    }

    public void initViews(){
        forgotMainTextView = (TextView)findViewById(R.id.forgotMorgotText);
        newPass = (TextInputLayout)findViewById(R.id.forgotPass);
        reenterNewPass = (TextInputLayout)findViewById(R.id.forgotNewPass);
        comfirmCode = (TextInputLayout)findViewById(R.id.forgotComfirmCode);
        comfirmChanges = (Button)findViewById(R.id.forgotComfirmBut);

    }
}
