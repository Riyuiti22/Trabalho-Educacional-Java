create table disciplinas (
    id int not null primary key auto_increment,
    professor_id int,
    curso_id int,
    nome varchar(100),
    codigo varchar(100),
    foreign key (curso_id) references cursos(id),
    foreign key (professor_id) references professores(id)
);