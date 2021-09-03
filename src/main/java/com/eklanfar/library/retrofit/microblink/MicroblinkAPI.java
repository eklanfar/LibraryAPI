package com.eklanfar.library.retrofit.microblink;

import com.eklanfar.library.retrofit.microblink.model.request.MRTDRequest;
import com.eklanfar.library.retrofit.microblink.model.response.MRTDResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MicroblinkAPI {

    @POST("v1/recognizers/mrtd")
    Call<MRTDResponse> mrtd(@Header("Authorization") String bearerToken, @Body MRTDRequest requestModel);
}
