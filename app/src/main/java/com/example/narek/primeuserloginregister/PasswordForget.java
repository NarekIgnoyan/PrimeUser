package com.example.narek.primeuserloginregister;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.narek.primeuserloginregister.MainPage.MainActivity;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class PasswordForget extends AppCompatActivity {
    private Shimmer shimmer;
    private ShimmerTextView myShimmerTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_forget);
        makeShimmer();

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
}
