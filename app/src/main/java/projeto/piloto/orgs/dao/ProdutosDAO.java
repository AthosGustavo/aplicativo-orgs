package projeto.piloto.orgs.dao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projeto.piloto.orgs.model.Produto;

public class ProdutosDAO {

  private static final List<Produto> produtos = new ArrayList<>();

  public void adicionar(Produto produto) {
    produtos.add(produto);
  }

  public static List<Produto> retornaTodosProdutos() {
    return produtos;
  }

  public void remove(Produto produto) {
    produtos.remove(produto);
  }

  public void edita(Produto produto) {
    if (produtos.contains(produto)) {
      remove(produto);
      adicionar(produto);
    }
  }

  public void removeTodos() {
    produtos.clear();
  }
}
