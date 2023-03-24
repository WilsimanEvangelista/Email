package silva.evangelista.santos.wilsiman.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEviar = (Button) findViewById(R.id.btnEnviar);
        // Definicao da acao do click do botao
        btnEviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // Criando uma nova intencao para enviar dados para um app que o usuario desejar
                Intent i = new Intent(Intent.ACTION_SENDTO);

                // Definindo que queremos enviar para um app que trate de receber e enviar emails
                i.setData(Uri.parse("mailto:"));

                // Preenchendo intent com os dados que queremos enviar para app externa que vai enviar o email
                String[] emails = new String[]{email};
                // O campo extra_email corresponde a uma lista de strings onde cada uma é um emial de destinatario
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                // Campo extra_subject é referente ao campo de assunto
                i.putExtra(Intent.EXTRA_SUBJECT,assunto);
                // Campo extra_text é referente ao corpo de texto do e-mail.
                i.putExtra(Intent.EXTRA_TEXT, texto);

                // Abaixo tenta-se executar o intent dentro do try. Usa-se o método Intent.createChoose para aparecer vários apps capazes de enviar mensagem de email
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                // Abaixo, caso não haja nenhum app capaz de enviar email, aparecerá uma mensagem de erro
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}