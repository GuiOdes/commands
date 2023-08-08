CREATE TABLE produto
(
    id      UUID           NOT NULL PRIMARY KEY,
    nome    VARCHAR        NOT NULL,
    preco   DECIMAL(10, 2) NOT NULL,
    estoque INT            NOT NULL
);

COMMENT ON TABLE produto IS 'Tabela de produtos';
COMMENT ON COLUMN produto.id IS 'Identificador único do produto';
COMMENT ON COLUMN produto.nome IS 'Nome do produto';
COMMENT ON COLUMN produto.preco IS 'Preço do produto';
COMMENT ON COLUMN produto.estoque IS 'Quantidade em estoque do produto';