CREATE TABLE item_comanda
(
    produto_id     UUID    NOT NULL REFERENCES produto (id),
    comanda_id     UUID    NOT NULL REFERENCES comanda (id),
    quantidade     INTEGER NOT NULL,
    deletado       BOOLEAN NOT NULL DEFAULT FALSE,
    funcionario_id UUID    NOT NULL REFERENCES funcionario (id)
);

COMMENT ON TABLE item_comanda IS 'Tabela que armazena os itens de uma comanda';
COMMENT ON COLUMN item_comanda.produto_id IS 'Id do produto';
COMMENT ON COLUMN item_comanda.comanda_id IS 'Id da comanda';
COMMENT ON COLUMN item_comanda.quantidade IS 'Quantidade do produto';
COMMENT ON COLUMN item_comanda.deletado IS 'Flag que indica se o item foi deletado';
COMMENT ON COLUMN item_comanda.funcionario_id IS 'Id do funcionário que realizou a operação';