package com.example.androidprojekt;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @GET("ettermek")
    Call<List<Ettermek>> getEttermek();

    @GET("etelek/{eteltipus}")
    Call<List<Etelek>> getEtelek(@Path("eteltipus") String eteltipus);

    @GET("etel/{etelneve}")
    Call<List<Etelek>> getEtelMindenEtteremben(@Path("etelneve") String etelneve);


    @GET("ettermek/{etteremnev}/{eteltipusnev}")
    Call<List<Etelek>> getEteltipusEtteremben(@NonNull @Path("etteremnev") String etteremnev, @NonNull @Path("eteltipusnev") String eteltipusnev);


    @FormUrlEncoded
    @POST("ujetel")
    Call<Etelek> ujEtel(
            @Field("etterem") String etterem,
            @Field("eteltipus") String eteltipus,
            @Field("etelnev") String etelnev,
            @Field("ar") String ar,
            @Field("mennyiseg") String mennyiseg
    );

    @DELETE("etel/{id}")
    Call<Etelek> etelTorol(@Path("id") int id);

    @PUT("etel/{id}")
    Call<Etelek> etelModosit(@Path("id") int id, @Body Etelek etel);

}