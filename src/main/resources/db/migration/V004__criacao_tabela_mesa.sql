CREATE TABLE mesa
(
    id INT NOT NULL PRIMARY KEY
);

COMMENT ON TABLE mesa IS 'Tabela que armazena as mesas do restaurante';
COMMENT ON COLUMN mesa.id IS 'Identificador unico da mesa';