CREATE TABLE cargo
(
    id   UUID    NOT NULL PRIMARY KEY,
    nome VARCHAR UNIQUE NOT NULL
);

COMMENT
ON TABLE cargo IS 'Tabela de cargos';
COMMENT
ON COLUMN cargo.id IS 'Identificador do cargo';
COMMENT
ON COLUMN cargo.nome IS 'Nome do cargo';