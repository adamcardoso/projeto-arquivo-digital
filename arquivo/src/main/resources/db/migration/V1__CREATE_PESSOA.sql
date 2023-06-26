CREATE TABLE IF NOT EXISTS tb_pessoa (
    id SERIAL PRIMARY KEY,
    nome_cadastrado VARCHAR(255) NOT NULL,
    numero_da_identidade VARCHAR(255),
    posto_graduacao VARCHAR(255),
    descricao VARCHAR(255),
    numero_da_caixa VARCHAR(255) NOT NULL,
    categoria_da_pessoa VARCHAR(255) NOT NULL,
    situacao_do_documento VARCHAR(255),
    data_de_entrada TIMESTAMP,
    data_de_saida TIMESTAMP
);
