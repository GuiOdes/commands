CREATE TABLE funcionario
(
    id         UUID           NOT NULL PRIMARY KEY,
    matricula  VARCHAR UNIQUE NOT NULL,
    nome       VARCHAR        NOT NULL,
    cpf        VARCHAR UNIQUE NOT NULL,
    telefone   VARCHAR UNIQUE NOT NULL,
    email      VARCHAR        NOT NULL,
    deletado   BOOLEAN        NOT NULL,
    cargo_id   UUID           NOT NULL REFERENCES cargo (id),
    privilegio VARCHAR        NOT NULL,
    login_id   UUID           NOT NULL REFERENCES login (id)
);

COMMENT ON TABLE funcionario IS 'Tabela de funcionários do sistema';
COMMENT ON COLUMN funcionario.id IS 'Identificador do funcionario';
COMMENT ON COLUMN funcionario.matricula IS 'Matricula do funcionario';
COMMENT ON COLUMN funcionario.nome IS 'Nome do funcionário';
COMMENT ON COLUMN funcionario.cpf IS 'CPF do funcionário';
COMMENT ON COLUMN funcionario.telefone IS 'Telefone do funcionário';
COMMENT ON COLUMN funcionario.email IS 'Email do funcionário';
COMMENT ON COLUMN funcionario.deletado IS 'Flag de exclusão lógica do funcionário';
COMMENT ON COLUMN funcionario.cargo_id IS 'Identificador do cargo do funcionário';
COMMENT ON COLUMN funcionario.privilegio IS 'Nível de acesso do funcionário';
COMMENT ON COLUMN funcionario.login_id IS 'Identificador do login do funcionário';