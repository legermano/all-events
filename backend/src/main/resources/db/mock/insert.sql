INSERT INTO usuario (id, nome, endereco, telefone, cpf, email, senha, fl_admin) VALUES (
    1,
    'Lucas Emanuel Germano',
    'Rua José Linhares',
    '(51)98256-2799',
    '034.935.970-96',
    'lucas.germano@universo.univates.br',
    'teste123',
    'f'
);

INSERT INTO evento (id, nome, descricao, dt_inicio, dt_fim, maximo_vagas) VALUES (
    1,
    'Evento da Sexta-Feira Santa',
    'Evento que irá acontecer na sexta-feira Santa',
    '2022-04-15 09:00:00',
    '2022-04-15 21:00:00',
    100
);

INSERT INTO evento (id, nome, descricao, dt_inicio, dt_fim, maximo_vagas) VALUES (
    2,
    'Evento Arquitetura de Software',
    'Evento que irá acontecer na última aula de Arquitetura de Software',
    '2022-04-20 19:10:00',
    '2022-04-20 22:30:00',
    100
);

INSERT INTO inscricao (id, ref_usuario, ref_evento, dt_inscricao) VALUES (
    1,
    1,
    1,
    '2022-04-13 11:00:00'
);