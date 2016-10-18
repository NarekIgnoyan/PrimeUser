package com.example.narek.primeuserloginregister.Home.Fragments;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narek.primeuserloginregister.Common.Initialization;
import com.example.narek.primeuserloginregister.R;

import static android.content.ContentValues.TAG;


public class SettingsFragment extends Fragment implements Initialization {
    private View v;
    private ImageView card;
    private Typeface cardFont, nameFont;
    private SharedPreferences pref_token,pref_card;
    private SharedPreferences.Editor editor_token,editor_card;
    public  static final String  PREFERENCE_TOKEN = "com.prime.primeuser.TOKEN";
    public  static final String  PREFERENCE_CARD_DETAILS = "com.prime.primeuser.CARD";
    private String cardNumber,userName;
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v=inflater.inflate(R.layout.fragment_settings,container,false);
            managePreferences();
            initFields();
            initViews();
            setFields();
            setViews();
            return v;
        }

    @Override
    public void initViews() {
        card = (ImageView) v.findViewById(R.id.card);
    }

    @Override
    public void initFields() {
        cardFont = Typeface.createFromAsset(getActivity().getAssets(),"cr.ttf");
        nameFont = Typeface.createFromAsset(getActivity().getAssets(),"mb.ttf");
        cardNumber = pref_card.getString("cardNumber","Something went wrong");
        userName  = pref_card.getString("userName","Something went wrong");

    }

    @Override
    public void setFields() {

    }

    @Override
    public void setViews() {

        card.setImageBitmap(drawTextToBitmap(getActivity(),R.drawable.card_yellow,userName.toUpperCase(),cardNumber.substring(0,4)+" " +cardNumber.substring(4,8)+" " + cardNumber.substring(8,12),cardNumber.substring(cardNumber.length()-4)));




    }

    private void managePreferences(){

        pref_token = getActivity().getApplicationContext().getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE);
        pref_card = getActivity().getApplicationContext().getSharedPreferences(PREFERENCE_CARD_DETAILS, Context.MODE_PRIVATE);
        editor_token = pref_token.edit();
        editor_card = pref_card.edit();
    }

    public Bitmap drawTextToBitmap(Context context, int resId,
                                   String userName,String cardNumberFristPart, String cardNumberSecondPart) {
        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap =
                BitmapFactory.decodeResource(resources, resId);
        android.graphics.Bitmap.Config bitmapConfig =
                bitmap.getConfig();
        if(bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setTextSize((int) (40 * scale));
        paint.setTypeface(nameFont);
        Rect bounds = new Rect();
        paint.getTextBounds(userName, 0, userName.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width())/2;
        int y = (bitmap.getHeight() + bounds.height())/2;
        Toast.makeText(context, String.valueOf(x) +"  " + String.valueOf(y),  Toast.LENGTH_SHORT).show();
        canvas.save();
        canvas.rotate((float) 90, x, y);
        canvas.drawText(userName,x-(x*3f), y+(y*20/100), paint);
        paint.setTypeface(cardFont);
        paint.setTextSize((int) (52 * scale));
        canvas.drawText(cardNumberFristPart,x-(x*3f), y-(y*25/100), paint);
        paint.setColor(Color.rgb(0, 0, 0));
        canvas.drawText(cardNumberSecondPart,x+(x*1.6f), y-(y*25/100), paint);
        canvas.restore();
        return bitmap;
    }
}
