package projeto.piloto.orgs.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import projeto.piloto.orgs.R;
import projeto.piloto.orgs.model.Produto;
import projeto.piloto.orgs.ui.recyclerview.adapter.ListaProdutosAdapter;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);

    List<Produto> adapter = new ArrayList<>();
    adapter.add(new Produto("Arroz", "Arroz da marca A", BigDecimal.valueOf(10.00)));
    adapter.add(new Produto("Macarrão 2", "Macarrão da marca B", BigDecimal.valueOf(20.0)));
    adapter.add(new Produto("Leite", "Leite da marca Z", BigDecimal.valueOf(30.0)));
    adapter.add(new Produto("Ovos", "Ovos 20 unidades", BigDecimal.valueOf(40.0)));

    ListaProdutosAdapter listaProdutosAdapter = new ListaProdutosAdapter(this, adapter);  // instancia o adapter
    recyclerView.setAdapter(listaProdutosAdapter);  // seta o adapter no recyclerView atraves do setAdapter

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }
}
