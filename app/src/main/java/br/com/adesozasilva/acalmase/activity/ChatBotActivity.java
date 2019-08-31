package br.com.adesozasilva.acalmase.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.adesozasilva.acalmase.R;
import br.com.adesozasilva.acalmase.adapter.AdapterMessage;
import br.com.adesozasilva.acalmase.callback.EnviarMensagemCallback;
import br.com.adesozasilva.acalmase.callback.OuvirMensagensCallback;
import br.com.adesozasilva.acalmase.model.Message;
import br.com.adesozasilva.acalmase.service.IChatService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotActivity extends AppCompatActivity {

    private Integer clienteId = 2;
    private Button button;
    private EditText editText;
    private List<Message> messages;
    private ListView messageList;
    private IChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        messageList = (ListView) findViewById(R.id.lv_message);
        messages = new ArrayList<>();

        AdapterMessage adapter = new AdapterMessage(clienteId, messages, this);
        messageList.setAdapter(adapter);

        button = (Button) findViewById(R.id.btn_enviar);
        editText = (EditText) findViewById(R.id.et_texto);

        Retrofit retrofit = new Retrofit.Builder()
                // Altere para o seu IP
                .baseUrl("http://172.16.70.211:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatService = retrofit.create(IChatService.class);
        Message message = new Message(1, "Olá posso te ajudar?");
        chatService.send(message).enqueue(new EnviarMensagemCallback());

        Call<Message> call = chatService.listenToMessages();
        call.enqueue(new OuvirMensagensCallback(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    chatService.send(new Message(clienteId, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
                    editText.setText("");
            }
        });

    }

    public void ouvirMensagem() {
        Call<Message> call = chatService.listenToMessages();
        call.enqueue(new OuvirMensagensCallback(this));
    }

    public void colocaNaLista(Message message) {
        messages.add(message);
        AdapterMessage adapter = new AdapterMessage(clienteId, messages, this);
        messageList.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}
