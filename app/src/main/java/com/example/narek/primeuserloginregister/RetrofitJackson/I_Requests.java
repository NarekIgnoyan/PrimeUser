package com.example.narek.primeuserloginregister.RetrofitJackson;




import com.example.narek.primeuserloginregister.RequesttClasses.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface I_Requests {

    static final String address = "http://85.143.210.101";

    @FormUrlEncoded
    @POST("/LoginRegistration")
    Call<Responses<List<Login>>> login(
            @Field("requestName") String requestName,
            @Field("mail") String userMail,
            @Field("password") String password
    );



}
