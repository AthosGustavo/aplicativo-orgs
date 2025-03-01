package projeto.piloto.orgs.model;

import android.widget.TextView;

import java.math.BigDecimal;

public class Produto {
  private String nome;
  private String descricao;
  private BigDecimal preco;

  public Produto(String nome, String descricao, BigDecimal preco) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }


}
