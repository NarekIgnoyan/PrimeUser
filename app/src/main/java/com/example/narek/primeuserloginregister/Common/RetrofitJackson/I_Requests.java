package com.example.narek.primeuserloginregister.Common.RetrofitJackson;




import android.support.annotation.Nullable;

import com.example.narek.primeuserloginregister.Common.RequesttClasses.Login;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.News;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.Registration;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.RegistrationComfirm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface I_Requests {

    static final String address = "http://85.143.210.101";

    @FormUrlEncoded
    @POST("/LoginRegistration")
    Call<Responses<Login>> login(
            @Field("requestName") String requestName,
            @Field("mail") String userMail,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/LoginRegistration")
    Call<Responses<Registration>> register(
            @Field("requestName") String requestName,
            @Field("mail") String userMail,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("sex") String sex,
            @Field("phoneNumber") String phoneNumber,
            @Field("birthday") String birthday,
            @Field("postalCode") String postalCode,
            @Field("adress") String adress,
            @Field("country") String country,
            @Field("language") String language

            );

    @FormUrlEncoded
    @POST("/LoginRegistration")
    Call<Responses<RegistrationComfirm>> registerComfirm(
            @Field("requestName") String requestName,
            @Field("mail") String userMail,
            @Field("confirmCode") String confirmCode

    );



    @FormUrlEncoded
    @POST("/UserService")
    Call<Responses<News>> news(
            @Field("requestName") String requestName,
            @Field("date") @Nullable String  date

    );




}
