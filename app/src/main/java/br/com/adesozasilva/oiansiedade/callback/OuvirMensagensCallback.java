package br.com.adesozasilva.oiansiedade.callback;

import br.com.adesozasilva.oiansiedade.activity.ChatBotActivity;
import br.com.adesozasilva.oiansiedade.model.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Message> {

    private final ChatBotActivity activity;

    public OuvirMensagensCallback(ChatBotActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Message> call, Response<Message> response) {
        if(response.isSuccessful()) {
            Message mensagem = response.body();

            activity.colocaNaLista(mensagem);
            activity.ouvirMensagem();
        }

    }

    @Override
    public void onFailure(Call<Message> call, Throwable t) {

        activity.ouvirMensagem();
    }
}