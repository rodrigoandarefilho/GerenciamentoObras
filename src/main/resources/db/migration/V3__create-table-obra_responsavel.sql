CREATE TABLE obra_responsavel(

    responsavel_id BIGINT NOT NULL,
    obra_id BIGINT NOT NULL,

    CONSTRAINT fk_responsavel_id FOREIGN KEY(responsavel_id) REFERENCES responsavel(id),
    CONSTRAINT fk_obra_id FOREIGN KEY(obra_id) REFERENCES obra(id)
);