package com.example.benwr.reevelaapp.Chat;

import com.example.benwr.reevelaapp.Notifictations.MyResponse;
import com.example.benwr.reevelaapp.Notifictations.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    /**
     * Implement API key from Firebase database: AAAAlbruqWk:APA91bFQ-4dsLxIvkNJ_oDQvxRiEEDTpFeUmqO_ClDtyTdiVw5W0cLt-hYdL9NTkDUpZKo6_7l-a94aEPt9Ora1zJ5QoGpOoFhCWx-e-QPz7wbAHqphvtakrC_LuP5rg2HiArf0fwQ14
     */

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAlbruqWk:APA91bFQ-4dsLxIvkNJ_oDQvxRiEEDTpFeUmqO_ClDtyTdiVw5W0cLt-hYdL9NTkDUpZKo6_7l-a94aEPt9Ora1zJ5QoGpOoFhCWx-e-QPz7wbAHqphvtakrC_LuP5rg2HiArf0fwQ14"
            }
    )


    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);


}




