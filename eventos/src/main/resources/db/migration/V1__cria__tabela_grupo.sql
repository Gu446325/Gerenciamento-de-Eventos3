--DROP TABLE IF EXISTS grupo;

CREATE TABLE IF NOT EXISTS grupo
(
  id bigint NOT NULL,
  descricao character varying(80),
  nome character varying(20) NOT NULL,
  CONSTRAINT grupo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE grupo
  OWNER TO postgres;
  
  
CREATE TABLE IF NOT EXISTS usuario
(
  id bigint NOT NULL,
  email character varying(70) NOT NULL,
  nome character varying(80) NOT NULL,
  senha character varying(30) NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (id),
  CONSTRAINT uk_5171l57faosmj8myawaucatdw UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;  

CREATE TABLE IF NOT EXISTS usuario_grupo
(
  usuario_id bigint NOT NULL,
  grupo_id bigint NOT NULL,
  CONSTRAINT fk_5p20y5panoea7wc040qm6eemd FOREIGN KEY (grupo_id)
      REFERENCES grupo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_m32it4c8rkf6t8nno481k43q4 FOREIGN KEY (usuario_id)
      REFERENCES usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario_grupo
  OWNER TO postgres;
  
  