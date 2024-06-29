package projeto.piloto.orgs.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import projeto.piloto.orgs.R;
import projeto.piloto.orgs.model.Produto;

public class ListaProdutosAdapter extends RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder> {

  private Context context;
  private List<Produto> produtos;

  public ListaProdutosAdapter(Context context, List<Produto> produtos) {
    this.context = context;
    this.produtos = produtos;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(this.context);
    View view = layoutInflater.inflate(R.layout.produto_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ListaProdutosAdapter.ViewHolder holder, int position) {
    Produto produto = produtos.get(position);
    holder.vincula(produto);
  }

  @Override
  public int getItemCount() {
    return produtos.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView nome;
    private TextView descricao;
    private TextView preco;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      nome = itemView.findViewById(R.id.nome);
      descricao = itemView.findViewById(R.id.descricao);
      preco = itemView.findViewById(R.id.preco);
    }

    public void vincula(Produto produto) {
      nome.setText(produto.getNome());
      descricao.setText(produto.getDescricao());
      preco.setText(produto.getPreco().toString());
    }
  }
}
