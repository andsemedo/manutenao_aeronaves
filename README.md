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

**Execução**
1. docker compose build --no-cache
2. docker compose up

# Documentação da API <h6>
**Aceder:** <http://localhost:8080/swagger-ui/index.html>


