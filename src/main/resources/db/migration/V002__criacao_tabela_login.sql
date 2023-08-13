CREATE TABLE login
(
    id      UUID           NOT NULL PRIMARY KEY,
    usuario VARCHAR UNIQUE NOT NULL,
    senha   VARCHAR        NOT NULL,
    deletado BOOLEAN NOT NULL
);

COMMENT ON TABLE login IS 'Tabela de logins do sistema';
COMMENT ON COLUMN login.id IS 'Identificador do login';
COMMENT ON COLUMN login.usuario IS 'Usuario que será inserido para logar no sistema';
COMMENT ON COLUMN login.senha IS 'Senha do usuário que vai se logar no sistema';
COMMENT ON COLUMN login.deletado IS 'Campo que diz se o login está ou não deletado pelo soft delete da aplicação';