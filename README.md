<h1 align="center">GitHub-TechChallenge</h1>


<h2 ">Descrição do Projeto</h2>
<p>Aplicação Android desenvolvida utilizando Kotlin e arquitetura MVVM, com a finalidade de pesquisar e listar os repositório públicos do gitHub e fornecer detalhes sobre os repositórios.</p>

<h2 ">Como executar</h2>
<p>1. Instale o Android Studio</p>
<p>2. Através do terminal, clone o repositório com o seguinte comando:
$ git clone https://github.com/Felipe041198/GitHub-TechChallenge</p>
<p>3. Abre o projeto utilizando o Android Studio e aguarde a indexação</p>
<p>4. Execute o projeto</p>


<h2>Utilizações</h2>

Os componentes, bibliotescas e técnicas utilizadas foram pensadas para dar a aplicação fluidez, organização e escalabilidade.

<b>MVVM</b>

Projeto divido em 3 camadas:
A camada de Model, onde estão presentes a origem dos dados apresentados na aplicação. (Por exemplo classes de banco de dados).
A camada de View, onde estão presentes as activitys/fragments responsaveís por receber e apresentar os dados.
E a camada ViewModel, que vai abstrair a view e ser responsável pelo tratamento das informações apresentadas.

<b>Material Design</b>

As interfaces foram desenvolvidas utilizando componentes do Material Desing, para melhorar a experiência do usuário.

<b>Coroutines</b>

Courotines foram utilizadas para conseguir fazer um bom uso das threads disponível e potencializar a performance da aplicação, sendo possível executar rotinas em cooperação.

<b>Paging Library 3.0</b>

A Paging Library 3.0 permite que os dados carregados da Api sejam salvos no banco de dados e carregados de acordo com a página em questão. O Remote Mediador age como uma camada que verifica a página atual, próxima e anterior para determinar a próxima chamada à Api, assim como a operação de inserção dos dados no Banco de Dados.

<b>Koin</b>

Biblioteca utilizada para injeção de dependências por ser simples e pratica, além de funcionar bem.

<b>Glide</b>

Biblioteca utilizada para utilização de imagens.

<b>Android Jetpack Nabigation</b>

Componente utilizada para facilitar a navegação entre as telas.

<h2>Possibilidades Futuras</h2>

<p>Devido a falta de prazo, a implementação dos testes unitários, que seria feita utuilizando o JUnit para automatização dos testes e o Mockito para isolar e injetar as dependências, não foram implementadas no projeto. </p>
