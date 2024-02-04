# Subsistema Manutenção de Aeronaves - Equipa Seul <h1>

# Participantes No Projeto <h5>
>* Anderson Semedo, nº 10315
>* Silvino Gonçalves, nº 10436
>* Veronica Andrade, nº 10571

# Objetivo do Projeto <h5>
O objetivo do sistema de gestão de manutenção de aeronaves é otimizar e eficientizar as atividades 
de manutenção, assegurando a segurança, a confiabilidade e a conformidade regulamentar das 
aeronaves, ao mesmo tempo que minimiza o tempo de inatividade e maximiza a eficiência 
operacional.

# ERD do Subsistema <h5>
![ERD Manutenção Aeronave](https://github.com/andsemedo/manutenao_aeronaves/assets/84507074/d8b81ccd-4c42-4790-89d4-8a798de79562)

# Configurção e Execução da Aplicação <h5>
**Pré-requesito: Ter o **Docker** instalado**

>**Recomendação caso estiver rodando a aplicação pela 1ª vez**
>Caso estiver rodando a aplicação pela 1ª vez é recomendado ter a lista das aeronaves para o GestaoVooSimulate.
>No pacote GestaoVooSimulate, criamos um Webclient para simular a integração com o subsistema de gestão de voos de modo a ver se o aeronave está disponivel ou não para a atividade >manutenão
>**Para ter a lista com as aeronaves, segue os seguintes passos:**
>**Antes de executar a aplicação ou dar docker-compose up**
>1. Vá no pacote GestaoVooSimulate
>2. Entre na classe *DisponibilidadeAeronaveConfig*
>3. Descomente o codigo que está comentado
>4. Depois, dê docker-compose up
>5. Quando todos os conteiner já forem executados com sucesso, inclusive o da aplicação
>6. Comente o codigo que descomentaste anteriormente, para não dar conflito de atributos unicos da base de dados quando for executar a aplicação novamente

# Endpoints <h6>
**Aeronave-Controller**
* Retorna todas as aeronaves - GET - http://localhost:8080/api/manutencao/aeronave
* Retorna uma aeronave pelo id - GET - http://localhost:8080/api/manutencao/aeronave/id/{id}
* Retorna uma aeronave pela matricula - GET - http://localhost:8080/api/manutencao/aeronave/matricula/{matricula}
* Deleta uma aeronave - DELETE - http://localhost:8080/api/manutencao/aeronave/{id}
* Adiciona uma nova aeronave - POST - http://localhost:8080/api/manutencao/aeronave
><pre><strong>Request body</strong>
>  {
>    "matricula": "string",
>    "modelo": "string",
>    "ano_fabrico": 2024,
>    "capacidade": 0
>  }
></pre>

**Atividade-Manutencao-Controller**
* Listar todas as manutenções - GET - http://localhost:8080/api/manutencao/atividade/
* Retorna uma manutenção pelo id - GET - http://localhost:8080/api/manutencao/atividade/{id}
* Listar o historico de manutenções de uma aeronave - GET - http://localhost:8080/api/manutencao/atividade/aeronave/{id}/historico
* Deleta uma manutenção - DELETE - http://localhost:8080/api/manutencao/atividade/{id}
* Adiciona uma nova manutenção - POST - http://localhost:8080/api/manutencao/atividade/
>** Tipo Manutencao **
> * preventiva
> * corretiva,
> * emergencial
>** Status Manutencao **
> * pendente
> * emProgresso,
> * concluido
><pre><strong>Request body</strong>
>  {
>    "manutencaoid": 0,
>    "tipoManutencao": "preventiva",
>    "descricao": "string",
>    "statusManutencao": "pendente",
>    "aeronaveid": 0,  
>    "data": "2024-02-04",
>  }
></pre>
* Atualizar apenas o status e a descrição de uma manutenção - PUT - http://localhost:8080/api/manutencao/atividade/{id}
><pre><strong>Request body</strong>
>  {
>    "descricao": "string",
>    "statusManutencao": "pendente",
>  }
></pre>

**Atividade-Manutencao-Controller**
* Listar todas as manutenções - GET - http://localhost:8080/api/manutencao/atividade/
* Retorna uma manutenção pelo id - GET - http://localhost:8080/api/manutencao/atividade/{id}
* Listar o historico de manutenções de uma aeronave - GET - http://localhost:8080/api/manutencao/atividade/aeronave/{id}/historico
* Deleta uma manutenção - DELETE - http://localhost:8080/api/manutencao/atividade/{id}
* Adiciona uma nova manutenção - POST - http://localhost:8080/api/manutencao/atividade/
><pre><strong>Request body</strong>
>  {
>    "manutencaoid": 0,
>    "tipoManutencao": "preventiva",
>    "descricao": "string",
>    "statusManutencao": "pendente",
>    "aeronaveid": 0,  
>    "data": "2024-02-04",
>  }
></pre>

**Uso-Peca-Controller**
* Retorna todos os uso-peças - GET - http://localhost:8080/api/manutencao/usopeca
* Retorna um uso peça por id - GET - http://localhost:8080/api/manutencao/usopeca/{id}
* Deleta um uso peça - DELETE - http://localhost:8080/api/manutencao/usopeca/{id}
* Atualiza a quantidade de peça no uso peça - PUT - http://localhost:8080/api/manutencao/usopeca/{id}
><pre><strong>Request body</strong>
>  {
>    "quantidade": 0
>  }
></pre>
* Adiciona um novo uso peça - POST - http://localhost:8080/api/manutencao/usopeca
><pre><strong>Request body</strong>
>  {
>    "tarefaId": 0,
>    "pecaId": 0,
>    "quantidade": 0
>  }
></pre>

# Documentação da API <h6>
**Aceder:** <http://localhost:8080/swagger-ui/index.html>


