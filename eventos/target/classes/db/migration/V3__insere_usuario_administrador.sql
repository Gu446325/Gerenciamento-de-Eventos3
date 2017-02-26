insert into usuario (id, email, nome, senha)
select 9999, 'user9999@trisoft.com', 'Usuario 0','@tsfevt4577' 
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE id = 1);

insert into usuario_grupo values(9999, 1);
insert into usuario_grupo values(9999, 2);
insert into usuario_grupo values(9999, 3);