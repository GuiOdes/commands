CREATE TABLE meio_pagamento
(
    id   INTEGER        NOT NULL PRIMARY KEY,
    nome VARCHAR UNIQUE NOT NULL
);

COMMENT ON TABLE meio_pagamento IS 'Tabela que armazena os meios de pagamento disponíveis';
COMMENT ON COLUMN meio_pagamento.id IS 'Identificador único do meio de pagamento';
COMMENT ON COLUMN meio_pagamento.nome IS 'Nome do meio de pagamento';


CREATE TABLE pagamento
(
    id UUID NOT NULL,
    valor_pago DECIMAL(10, 2) CHECK(valor_pago > 0.0) NOT NULL,
    meio_pagamento_id INTEGER Not NULL REFERENCES meio_pagamento (id),
    comanda_id UUID NOT NULL REFERENCES comanda(id)
);

COMMENT ON TABLE pagamento IS 'Tabela que armazena as pagamentos realizados para comanda especifica';
COMMENT ON COLUMN pagamento.id IS 'Identificador único de pagamento';
COMMENT ON COLUMN pagamento.valor_pago IS 'valor pago referente a comanda';
COMMENT ON COLUMN pagamento.meio_pagamento IS 'Forma de pagamento que foi recebido: PIX, DINHEIRO, CARTÃO e OUTROS';
COMMENT ON COLUMN pagamento.comanda_id IS 'Indentificar que armazena a comanda que está recebendo esse pagamento';