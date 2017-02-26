insert into grupo (id, nome, descricao)
select 1, 'PARTICIPANTES', 'Alunos' WHERE NOT EXISTS (SELECT 1 FROM grupo WHERE id = 1);

insert into grupo (id, nome, descricao)
select 2, 'EMPRESAS', 'Cria eventos' WHERE NOT EXISTS (SELECT 1 FROM grupo WHERE id = 2);

insert into grupo (id, nome, descricao)
select 3, 'ADMINISTRADORES', 'Gerencia' WHERE NOT EXISTS (SELECT 1 FROM grupo WHERE id = 3);