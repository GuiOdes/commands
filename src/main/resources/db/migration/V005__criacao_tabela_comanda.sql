CREATE TABLE comanda
(
    id               UUID    NOT NULL PRIMARY KEY,
    nome_responsavel VARCHAR NOT NULL,
    numero_mesa      INT     NOT NULL REFERENCES mesa (id),
    ativa            BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao     TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE comanda IS 'Tabela que armazena as comandas do restaurante';
COMMENT ON COLUMN comanda.id IS 'Identificador unico da comanda';
COMMENT ON COLUMN comanda.nome_responsavel IS 'Nome do responsável pela comanda';
COMMENT ON COLUMN comanda.numero_mesa IS 'Numero da mesa que a comanda está associada';
COMMENT ON COLUMN comanda.ativa IS 'Flag que indica se a comanda está ativa ou não';
COMMENT ON COLUMN comanda.data_criacao IS 'Data de criação da comanda';