CREATE TABLE obra(

    id BIGINT NOT NULL AUTO_INCREMENT,
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