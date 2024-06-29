# Aprendendo Android Nativo com Java e Kotlin.

*Aprendizagem e documentação do projeto*

<details>
 <summary>Introdução e conceitos</summary>

 # Introdução e conceitos básicos

 ## Montagem e exibição de uma tela

 ### Activity
  - Activity representa uma tela com  qual o usuário pode interagir
  - Uma classe genérica é criada e extende a classe Activity, dessa forma a classe recebe os métodos e propriedades de uma activity.
  - Toda classe que extende de Activity possui um arquivo xml onde será declarado os componentes.
  - O ciclo de vida de uma atividade inclui vários estados, como onCreate, onStart, onResume, onPause, onStop, onDestroy,
 
 ### View
  - O objeto View no Android é a base para a construção de qualquer elemento de interface do usuário.
  - Cada componente de UI, como botões, campos de texto, layouts, etc., são subclasses de View.
 
 ### Layout
  - Layouts são contêineres especiais que organizam outros elementos de interface do usuário (como botões, campos de texto, etc.) em uma determinada disposição na tela.
  - Alguns exemplos de layouts incluem LinearLayout, RelativeLayout, FrameLayout, ConstraintLayout,

 ### Método setContentView
  - Método da classe Activity que define o que será exibido na interface para o usuário de UI.
  - O método aceita em seu parâmetro um argumento que seja uma subclasse de View.

 ### R
  - Objeto que da acesso a arquivos de layout, strings, imagens, cores, estilos e outros tipos de recursos.
  - A classe R é dividida em várias subclasses internas, cada uma correspondendo a um tipo específico de recurso. Por exemplo, R.layout contém identificadores para todos os arquivos de layout, R.string e etc.

    
*EXEMPLO*
```java
public class MainActivity extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View view = new View(this);
    setContentView(R.layout.activity_main);
  }
}
```

 ## Namespace usados no arquivo xml
  - `xmlns:android`: Dar acesso a todos os atributos do sistema operacional do android.
  - `xmlns:tools`: Usado para acessar as ferramentas de design do Android Studio que podem ajudar a melhorar a aparência do layout no editor de layout, mas não afetam o layout em tempo de execução.
  - `xmlns:app`: Usado para acessar atributos personalizados que você definiu em seu aplicativo
</details>

<details>
 <summary>Componentes</summary>

 # Componentes
  
  <details>
   <summary>Containers</summary>
   
   # Containers
   - Assim como os Layouts, os Containers também foram feitos para comportar outras views.
   
  <details>
   <summary>RecyclerView</summary>

   # RecyclerView
   - É um tipo de ViewGroup usado para comportar `Views` no formato de lista.
   - Comporta listas horizontais e verticais.

   ## Exibição de itens dinâmicos com RecyclerView

   ### Classe Adapter
   ```java
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

   ```
   
   ### ViewHolder 
   - ViewHolderé uma classe que contém referências para as views que compõem cada item de uma lista.Exemplo, uma lista de cards onde cada card possui três textos.Cada card será uma instância de ViewHolder e os texto serão seus atributos.

   *EXEMPLO*
   - O construtor da classe atribui as propriedades da classe aos id's do `xml` correspondentes aos TextViews
   - O método vincula é chamado no método onBindViewHolder e recebe um produto, esse produto é do tipo ViewHolder.Após isso o método atribui os valores das propriedade Produto as propriedades da classe ViewHolder
   ```java
   class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nome;
        private TextView descricao;
        private TextView valor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            descricao = itemView.findViewById(R.id.descricao);
            valor = itemView.findViewById(R.id.preco);
        }

        public void vincula(Produto produto) {
            nome.setText(produto.getNome());
            descricao.setText(produto.getDescricao());
            valor.setText(produto.getValor().toPlainString());
        }
    }
   ```

   ### Método onCreateViewHolder
   - Este método é chamado internamente por RecyclerView para criar um novo ViewHolder que precisa ser representado na tela.
   - O método aceita dois parâmetros, uma ViewGroup e a vista.

   *Inflando Layouts*
   - primeiro, uma variavel do tipo LayoutInflater é instânciada com um contexto escolhido
   - O método `inflate` retorna uma View com base em um contexto
   - contexto pode ser uma classe que extende de activity onde é executada a lógica de um layout xml
   - A classe LayoutInflater da a possibilidade de retornar qualquer view com base em qualquer contexto
   
   ```java
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
	LayoutInflater inflater = LayoutInflater.from(contexto);
	View view = inflater.inflate(R.layout.produto_item, parent, false);
        return new ViewHolder(view);
    }
   ```

   ### Método onBindViewHolder
   - O método recebe um Produto e chama o método vincula que atribui os valores do Produto as propriedades do ViewHolder.

   ```java
    @Override
    public void onBindViewHolder(@NonNull ListaProdutosAdapter.ViewHolder holder, int position) {
      Produto produto = produtos.get(position);
      holder.vincula(produto);
    }

   ```
   ### MainActivity
   - O LinearLayoutManager é um tipo de LayoutManager que posiciona os itens em uma lista vertical ou horizontal.
   - Quando você cria uma nova instância de LinearLayoutManager, você precisa passar um Context
   - Sem um LayoutManager, o RecyclerView não saberia como organizar e exibir os itens.
   - O layoutManager também pode ser declarado no activity_main na tag do RecyclerView `app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"`
   
   ```java
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

   ```
   
   
  </details>

  </details>
  
  <details> 
  <summary>ViewGroups</summary>

   # ViewGroups
   - É um tipo de `View` feita para comportar outras views, como textos, botões, imagens e etc.

   
  </details>
   
   
</details>

<details>
 <summary>Posicionamento</summary>

 # Posicionamento
 - `match_parent:` Faz com que a `View` ocupe todo o espaço disponível do seu elemento pai NÃO respeitando as restrições do `ConstraintLayout`
 - `wrap_content:` Faz com que a `View` seja grande o suficiente para acomodar seu conteúdo.
 - `match_constraint ou 0dp:` Faz com que a `View` ocupe todo o espaço disponível do seu elemento pai respeitando as restrições do `ConstraintLayout`
</details>
