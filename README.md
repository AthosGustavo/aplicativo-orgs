# Aprendendo Android Nativo com Java e Kotlin.

*Aprendizagem e documentação do projeto*
<details>
 <summary>Android Studio</summary>
   
 - `Ctrl + F` Pesquisar uma palavra em um arquivo.
 - `Ctrl + N` Pesquisar classes
 - `Ctrl + Shift + F` Pesquisar uma palavra no projeto inteiro
 - `Alt + Shift + X` Fecha todas as abas
 - `Crlt + Shift + F12` Fecha todas as telas que não estão sendo usadas

 ## Adb connect
 Comandos para conectar o celular por wi-fi
 - 1- `cd C:/platform-tools`
 - 2- Comando para derrubar o server: `.\adb kill-server`
 - 3- Comando para levantar: `.\adb start-server`
 - 4- Comando para ver os dispositivos conectados: `.\adb devices`
 - 5- Comando para escolher a porta: `.\adb tcpip 5555`
 - 6- Comando para conectar: `.\adb connect ip_celular:5555`


 
   
</details>
<details>
 <summary>Introdução e conceitos</summary>

 # Introdução e conceitos básicos

 ## Debugg
  - https://developer.android.com/studio/inspect/database
 
 ## AndroidManifest
  - A Activity de entrada deve ser declarada nesse aquivo

 ## Diretórios
  - *res/drawable*: Pasta onde é guardada imagens que podem ser usadas no aplicativo
  - *res/mipmap* : Guarda ícones
  - *res/values* : Utils
 
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

 ## View Binding
  - View Binding é um recurso do Android que gera uma classe vinculada ao layout XML, permitindo acessar as views de forma segura e eficiente, sem findViewById().
  - Exemplo: Se o layout for activity_principal.xml, o ViewBinding gerará automaticamente a classe ActivityPrincipalBinding.
  - Uso: Os elementos do layout, como TextView, Button, etc., podem ser acessados diretamente como atributos da classe de binding.
  - https://www.alura.com.br/artigos/view-binding-android

 ## Interface Pacelable
  -  Permiti que objetos sejam passados entre Activities ou Fragments.
 
</details>

<details>
 <summary>Componentes</summary>

 # Componentes
  
   <details>
   <summary>Alert Dialog</summary>

   # Alert Dialog
   *Caixa de diálogo*
   - `AlertDialog.Builder`: Classe interna estática responsável por configurar a caixa de diálogo

   ### Forma curta
   - Útil quando não há necessidade de modificar o AlertDialog após a criação.

   ```java
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja confirmar esta ação?");

        // Botão de Confirmar
        builder.setPositiveButton("Confirmar", (dialog, which) -> 
            Toast.makeText(this, "Confirmado!", Toast.LENGTH_SHORT).show()
        );

        // Botão de Cancelar
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
   ```
   
   ### Forma completa
   - Precisa alterar dinamicamente o diálogo depois de criá-lo (ex.: habilitar/desabilitar botões)
   - Exemplo: Desativar o botão de confirmar até o usuário confirmar que leu os termos.
  
   ```java
     private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Termos de Uso");
        builder.setMessage("Você deve aceitar os termos antes de continuar.");

        // Criando um CheckBox programaticamente
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText("Li e aceito os termos");

        // Criando um layout para adicionar o CheckBox ao diálogo
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(50, 20, 50, 20);
        layout.addView(checkBox);

        builder.setView(layout);

        // Criando o diálogo, mas ainda não exibindo
        AlertDialog alertDialog = builder.create();

        // Adicionando os botões
        builder.setPositiveButton("Confirmar", (dialog, which) -> 
            Toast.makeText(this, "Termos aceitos!", Toast.LENGTH_SHORT).show()
        );
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        // Exibindo o diálogo após adicionar os botões
        alertDialog = builder.show();

        // Obtendo o botão "Confirmar" e desativando inicialmente
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setEnabled(false);

        // Ativar o botão "Confirmar" somente se o usuário marcar o CheckBox
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> 
            positiveButton.setEnabled(isChecked)
        );
    }
   ```

  ### Por que usar LinerLayout
  - Esse trecho é necessário porque o AlertDialog.Builder não possui suporte direto para adicionar um CheckBox no layout padrão do diálogo. Ele apenas permite definir título, mensagem e botões.
  - O método setView(View view) do AlertDialog.Builder permite definir um layout personalizado para o diálogo. No entanto, não podemos simplesmente passar um CheckBox diretamente. O Android exige que os componentes sejam organizados dentro de um container, como LinearLayout.

 
  </details>
  
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
   - `tools:listitem="@layout/produto_item"`: Atributo que recebe a View que será renderizada

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

  ## RecyclerView X ScrollView
   - `RecyclerView:` Usado para exibir uma lista grande e dinâmica de itens.
   - `ScrollView:` Usado para que o usuário role através de um layout simples que não muda.
     
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

<details>
 <summary>Invocando uma Activity através de outra Activity</summary>

 ## Método setOnClickListener
  - setOnClickListener adiciona um evento de click em uma view.
  - Em seu parâmetro, o método recebe uma classe anônima que da acesso ao método onClick
  - O objeto Intent é usado para transmitir informações entre componentes
  - `Intent intent = new Intent(ondeEstou, paraOndeIrei);`

 ```java
 View floatingActionButton = findViewById(R.id.floatingActionButton);

 floatingActionButton.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View v) {
    Intent intent = new Intent(MainActivity.this, FormularioProdutoActivity.class);
    startActivity(intent);
  }
});
 ```
 
</details>

<details>
 <summary>Cíclo de vida de um componente</summary>

  - `onCretate` : Chamado apenas uma vez
  - `onResume` : chamado todas as vezes que a tela está em execução
 
 ![Captura de tela de 2024-06-30 15-39-34](https://github.com/AthosGustavo/aplicativo-orgs/assets/112649935/7f061366-87a5-4581-b6cd-47bdc45e02ba)

 ![lifecycle-1](https://github.com/AthosGustavo/aplicativo-orgs/assets/112649935/de498277-2d29-490f-a290-0022bdc8b3af)
 
</details>

<details>
 <summary>Banco de dados</summary>

  # Banco de dados

  ## Room
  - https://developer.android.com/training/data-storage/room?hl=pt-br#java

  ## Conversão de tipos
  - Em algunas situações o SQLite não possui uma correspondência para um tipo do java e nesse caso deve haver uma conversão.

  *Classe conversora*
  ```java
  import androidx.room.TypeConverter;
  import java.math.BigDecimal;

  public class BigDecimalConverter {

    @TypeConverter
    public static BigDecimal fromDouble(Double value) {
        return value == null ? null : BigDecimal.valueOf(value);
    }

    @TypeConverter
    public static Double toDouble(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : bigDecimal.doubleValue();
    }
  }

  ```
 *Interface DAO*
 ```java
 import androidx.room.Dao;
 import androidx.room.Insert;
 import androidx.room.Query;
 import java.util.List;

 @Dao
 public interface ProdutoDao {

    @Insert
    void inserir(Produto produto);

    @Query("SELECT * FROM Produto")
    List<Produto> listarTodos();
 }


 ```

*Banco de dados*
```java
 import androidx.room.Database;
 import androidx.room.RoomDatabase;
 import androidx.room.TypeConverters;

 @Database(entities = {Produto.class}, version = 1)
 @TypeConverters({BigDecimalConverter.class}) // Adicionando o conversor
 public abstract class AppDatabase extends RoomDatabase {
    public abstract ProdutoDao produtoDao();
 }

```
</details>






