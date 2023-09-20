CREATE TABLE meio_pagamento
(
    id   INTEGER        NOT NULL PRIMARY KEY,
    nome VARCHAR UNIQUE NOT NULL
);

COMMENT ON TABLE meio_pagamento IS 'Tabela que armazena os meios de pagamento disponíveis';
COMMENT ON COLUMN meio_pagamento.id IS 'Identificador único do meio de pagamento';
COMMENT ON COLUMN meio_pagamento.nome IS 'Nome do meio de pagamento';

CREATE TABLE compra
(
    id             UUID           NOT NULL,
    valor_final    DECIMAL(10, 2) NOT NULL,
    desconto       DECIMAL(3, 2) CHECK (desconto >= 0.0 AND desconto <= 1.0),
    data_compra    TIMESTAMP      NOT NULL,
    comanda_id     UUID           NOT NULL REFERENCES comanda (id)
);
CREATE TABLE pagamento
(
    id UUID NOT NULL,
    valor_pago DECIMAL(10, 2) CHECK(valor_pago > 0.0) NOT NULL,
    meio_pagamento_id INTEGER Not NULL REFERENCES meio_pagamento (id),
    compra_id UUID NOT NULL REFERENCES compra(id)
);

COMMENT ON TABLE compra IS 'Tabela que armazena as compras realizadas';
COMMENT ON COLUMN compra.id IS 'Identificador único da compra';
COMMENT ON COLUMN compra.valor_final IS 'Valor final da compra';
COMMENT ON COLUMN compra.desconto IS 'Desconto aplicado na compra';
COMMENT ON COLUMN compra.data_compra IS 'Data da compra';
COMMENT ON COLUMN compra.comanda_id IS 'Identificador único da comanda';
COMMENT ON COLUMN compra.meio_pagamento IS 'Identificador único do meio de pagamento';