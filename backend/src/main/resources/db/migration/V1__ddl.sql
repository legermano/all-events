CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL,
    endereco TEXT,
    telefone TEXT,
    email TEXT UNIQUE NOT NULL,
    senha TEXT,
    fl_admin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABlE evento (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    descricao TEXT,
    dt_inicio TIMESTAMP NOT NULL,
    dt_fim TIMESTAMP NOT NULL,
    maximo_vagas INTEGER
);

CREATE TABLE inscricao (
    id SERIAL PRIMARY KEY,
    ref_usuario INTEGER NOT NULL,
    ref_evento INTEGER NOT NULL,
    dt_inscricao TIMESTAMP NOT NULL,
    dt_cancelamento TIMESTAMP,
    dt_presenca TIMESTAMP,
    FOREIGN KEY(ref_usuario) REFERENCES usuario(id),
    FOREIGN KEY(ref_evento)  REFERENCES evento(id)
);

CREATE TABLE inscricao_certificado (
    id SERIAL PRIMARY KEY,
    ref_inscricao INTEGER NOT NULL,
    codigo TEXT,
    dt_envio TIMESTAMP,
    FOREIGN KEY(ref_inscricao) REFERENCES inscricao(id)
);
