README - Aplicação Arquivo Digital

Este projeto é implementado usando o framework Spring.
Primeiros Passos

Para começar a usar a aplicação, siga as instruções abaixo.
Pré-requisitos

Certifique-se de ter os seguintes itens instalados:

    Java 17
    PostgreSQL
    Docker (necessário para rodar o container do KeyCloak)

Configurando o Container do KeyCloak:

    Inicie o contêiner Docker executando o comando docker-compose -f docker-compose-keycloak.yml up.
    Após inicializar o container, basta acessar o endereço: http://localhost:9090/ e acessar a página "Administration Console" com o usuário e senha definido na aplicação (verificar no application-security.yml)
    Ao fazer login, deve-se configurar os usuários, basta seguir o passo a passo conforme o exemplo deste vídeo: https://youtu.be/vmEWywGzWbA?t=974


Configurando o Banco de Dados

Se estiver usando o PostgreSQL diretamente, crie um banco de dados de acordo com a configuração no arquivo docker-compose-postgres.yml. Alternativamente, se estiver usando o contêiner Docker, siga estas etapas:

    Instale o DBeaver ou qualquer outro cliente PostgreSQL.
    Inicie o contêiner Docker executando o comando docker-compose -f docker-compose-postgres.yml up.

Executando a Aplicação

Para executar a aplicação, siga as etapas abaixo:

    Clone o repositório para a sua máquina local.
    Abra o projeto na sua IDE Java preferida.
    Configure a conexão com o banco de dados no arquivo application.yml, se necessário.
    Compile e execute o projeto.

Acessando o Actuator

O Actuator está configurado para fornecer informações e métricas sobre a aplicação. Você pode acessá-lo usando as seguintes URLs:

    Informações gerais: http://localhost:8081/actuator
    Status da aplicação: http://localhost:8081/actuator/health
    Informações detalhadas de endpoints: http://localhost:8081/actuator/info
    Métricas da aplicação: http://localhost:8081/actuator/metrics

Acessando a Documentação Swagger

A aplicação fornece documentação Swagger para as APIs. Para acessá-la, siga estas etapas:

    Inicie a aplicação.
    Abra seu navegador da web e navegue até http://localhost:8080/swagger-ui/index.html#/.

Contribuindo

Se você deseja contribuir para este projeto, siga estas diretrizes:

    Faça um fork do repositório.
    Crie um novo branch para sua funcionalidade ou correção de bugs.
    Commit suas alterações com mensagens de commit descritivas.
    Envie suas alterações para o repositório forked.
    Envie um pull request para o repositório principal.
    Certifique-se de que seu código siga o estilo de codificação estabelecido e que você tenha adicionado testes adequados para suas alterações.
