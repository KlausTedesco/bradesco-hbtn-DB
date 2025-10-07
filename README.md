[PARTE I] - Configurando o projeto


a) Utilizando sua IDE de preferência (Eclipse, STS, IntelliJ, entre outros), crie um projeto Maven com as seguintes configurações

    New Maven project: File -> New -> Other -> Maven Project

    - **Group id:** com.techcamps.cadastros 
    - **Artifact id:** administrativo-api
    - **Name:** Projeto Admin Demo
    - **Description:** Projeto Administrativo para cadastros de Clientes e Produtos usando Hibernate e JPA  


b) Alterar o arquivo pom.xml. Para configurar o projeto Maven, usar o Java instalado na sua máquina.
- Dica: - Properties: maven.compiler.source

c) Adicionar no projetos as seguintes bibliotecas (dependências)

    hibernate-core (5.4.12.Final)
    hibernate-entitymanager (5.4.12.Final)
    sqlite-jdbc (3.36.0.3)
    sqlite-dialect (0.1.2)

d) Criar os pacotes demo, entities, models, dentro da pasta scr/main/java

e) Dentro do pacote entities criar as seguintes classes:

    Pessoa (id, nome, email, idade, cpf e data de nascimento)
    Produto (id, nome, quantidade, preço e status)

f) Fazer o mapeamento dos atributos das classes Pessoa e Produto usando as anotações do JPA.

g) Configure o JPA no seu projeto por meio do arquivo persistence.xml
- Crie uma pasta “META-INF” a partir da pasta “resources” - Dentro da pasta META-INF crie um arquivo “persistence.xml” - Conteúdo do arquivo persistence.xml

Exemplo: persistence.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd" 
    version="2.1">

    <persistence-unit name="admin-jpa" transaction-type="RESOURCE_LOCAL">
        <properties>

            <property name="javax.persistence.jdbc.url" value="jdbc:sqlite:database_admin.db" />
            <property name="javax.persistence.jdbc.driver" value="org.sqlite.JDBC" />

            <property name="hibernate.hbm2ddl.auto" value="update" />

            <property name="hibernate.dialect" value="org.sqlite.hibernate.dialect.SQLiteDialect" />

                <property name="show_sql" value ="true" />
                <property name="format_sql" value ="true" />

            </properties>
    </persistence-unit>

    </persistence>


[PARTE II] - Manipulando os dados no banco de dados usando JPA


a) Criar as classes ProdutoModel e PessoaModel no pacote models. As classes devem conter os seguintes metódos:

- create, findById, findAll, update e delete.  


Exemplo: ProdutoModel.java

public class ProdutoModel {

  public void create(Produto p) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
    EntityManager em = emf.createEntityManager();

    try {
     System.out.println("Iniciando a transação");
     em.getTransaction().begin();
     em.persist(p);
     em.getTransaction().commit();
     System.out.println("Produto criado com sucesso !!!");
    } catch (Exception e) {
     em.close();
     System.err.println("Erro ao criar o produto !!!" + e.getMessage());
    } finally {
     em.close();
     System.out.println("Finalizando a transação");
    }
   }

    public void update(Produto p) {
        //TODO
    }

    public void delete(Produto p) {
        //TODO
    }

    public Produto findById(Produto p) {
        //TODO
        return null;
    }

    public List<Produto> findAll() {

        List<Produto> produtos = new ArrayList<Produto>();
        //TODO
        return null;
}


b) Dentro do pacote demo criar uma classe chamada AdministrativoApp para fazer a criação das tabelas e operações CRUD das classes ProdutoModel e PessoaModel no banco de dado SQLite.

Exemplo: AdministrativoApp.java

    public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        // TODO - Testar os demais metódos das classes: ProdutoModel e PessoaModel

    }
}


c) Utilize a classe AdministrativoApp para testar todos os métodos das classes: ProdutoModel e PessoaModel.

[PARTE III] - Salvando o SQL Schema - SQLite


Instruções:

    Cada banco de dados SQLite contém uma única “tabela de esquema” que armazena o esquema desse banco de dados. O esquema de um banco de dados é uma descrição de todas as outras tabelas, índices, triggers e views contidos no banco de dados (dump).

    Abrir o banco de dados database_admin.db no SQLite Online.
        Crie uma conta no SQLite OnLine para poder importar o arquivo database_admin.db:
        Clique no botão mostrado na figura abaixo:



    - Clique em Sign in:



    - Preencha os campos e clique em Create an account:


No menu (com símbolo de +) selecione Open SQLite DB e exporte o Schema de cada tabela gerada, clicando com o botão direito sobre o nome da tabela e escolhendo a opção “SQL Schema”. Copie o Script gerado para cada uma das tabelas em um arquivo com o nome sql_schema_database_admin.sql.

Atenção:

    A estrutura de diretórios está especificada na descrição da task abaixo.

Repo:

    GitHub repository: bradesco-hbtn-DB
    Directory: jpa_hibernate
    File: `src/main/java/demo/AdministrativoApp.java`, `src/main/java/entities/Pessoa.java`, `src/main/java/entities/Produto.java`, `src/main/java/models/PessoaModel.java`, `src/main/java/models/ProdutoModel.java`, `database_admin.db`, `sql_schema_database_admin.sql`, `src\main\resources\META-INF\persistence.xml`, `pom.xml`

