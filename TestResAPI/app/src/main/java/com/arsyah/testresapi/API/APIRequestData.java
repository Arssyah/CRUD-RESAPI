package com.arsyah.testresapi.API;

import com.arsyah.testresapi.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIRequestData {
    @GET("Mahasiswa")
    Call<ResponseModel> ardGetMahasiswa();

    @FormUrlEncoded
    @POST("mahasiswa/create")
    Call<ResponseModel> ardCreateMahasiswa(
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("angkatan") int angkatan
    );

    @FormUrlEncoded
    @PUT("mahasiswa/{id}")
    Call<ResponseModel> ardUpdateMahasiswa(
            @Path("id") int id,
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("angkatan") int angkatan
    );

    @DELETE("mahasiswa/{id}")
    Call<ResponseModel> ardDeleteMahasiswa(
            @Path("id") int id
    );
}
