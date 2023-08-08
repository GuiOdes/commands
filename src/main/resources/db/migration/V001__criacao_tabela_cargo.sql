CREATE TABLE cargo
(
    id   INT     NOT NULL PRIMARY KEY,
    nome VARCHAR NOT NULL
);

COMMENT ON TABLE cargo IS 'Tabela de cargos';
COMMENT ON COLUMN cargo.id IS 'Identificador do cargo';
COMMENT ON COLUMN cargo.nome IS 'Nome do cargo';