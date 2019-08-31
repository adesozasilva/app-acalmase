package br.com.adesozasilva.acalmase.service;

import br.com.adesozasilva.acalmase.model.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IChatService {

    @POST("polling")
    Call<Void> send(@Body Message mensagem);
    @GET("polling")
    Call<Message> listenToMessages();
}
