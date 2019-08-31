package br.com.adesozasilva.acalmase.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.adesozasilva.acalmase.R;
import br.com.adesozasilva.acalmase.model.Message;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMessage extends BaseAdapter {

    private List<Message> messages;
    private final Activity activity;
    private Integer clientId;
    @BindView(R.id.tv_texto)
    TextView texto;

    @BindView(R.id.iv_avatar_mensagem)
    ImageView avatar;

    public AdapterMessage(Integer clientId, List<Message> messages, Activity activity) {
        this.clientId = clientId;
        this.messages = messages;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View linha = activity.getLayoutInflater().inflate(R.layout.message, viewGroup, false);

        ButterKnife.bind(this, linha);

        Message mensagem = getItem(i);

        int iconMessage = R.drawable.eu;

        if (clientId != mensagem.getId()) {
            linha.setBackgroundColor(Color.CYAN);
            iconMessage = R.drawable.icons_robotic;
        }

        texto.setText(mensagem.getText());

        Picasso.with(activity)
                .load(iconMessage)
                .into(avatar);

        return linha;
    }
}