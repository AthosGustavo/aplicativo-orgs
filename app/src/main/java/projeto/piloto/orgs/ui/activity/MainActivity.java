package projeto.piloto.orgs.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import projeto.piloto.orgs.R;
import projeto.piloto.orgs.dao.ProdutosDAO;
import projeto.piloto.orgs.model.Produto;
import projeto.piloto.orgs.ui.recyclerview.adapter.ListaProdutosAdapter;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onResume() {
    super.onResume();
    RecyclerView recyclerView = findViewById(R.id.recyclerView);



    ListaProdutosAdapter listaProdutosAdapter = new ListaProdutosAdapter(this, ProdutosDAO.retornaTodosProdutos());  // instancia o adapter
    recyclerView.setAdapter(listaProdutosAdapter);  // seta o adapter no recyclerView atraves do setAdapter
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    View floatingActionButton = findViewById(R.id.floatingActionButton);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, FormularioProdutoActivity.class);
        startActivity(intent);
      }
    });
  }

}
