package projeto.piloto.orgs.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

import projeto.piloto.orgs.dao.ProdutosDAO;

import projeto.piloto.orgs.R;
import projeto.piloto.orgs.model.Produto;

public class FormularioProdutoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_formulario_produto);

    View btnSalvar = findViewById(R.id.botao_salvar);

    btnSalvar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        TextView campoNome = findViewById(R.id.nome);
        String nome = campoNome.getText().toString();

        TextView campoValor = findViewById(R.id.valor);
        BigDecimal valor = new BigDecimal(campoValor.getText().toString());
        TextView campoDescricao = findViewById(R.id.descricao);
        String descricao = campoDescricao.getText().toString();

        ProdutosDAO produtosDAO = new ProdutosDAO();
        produtosDAO.adicionar(new Produto(nome,descricao, valor));
        finish();
      }

    });





  }
}