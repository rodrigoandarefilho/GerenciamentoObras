CREATE TABLE obra(

    id BINARY(16) NOT NULL,
	descricao VARCHAR(255),
	data_cadastro DATETIME,
	data_inicio DATETIME,
    data_fim DATETIME,
    zona VARCHAR(255),
    area_total BIGINT,
    dtype VARCHAR(255),
    numero BIGINT NOT NULL,
    tipo VARCHAR(255) NOT NULL,

	PRIMARY KEY(id)
);