create table despesas (
  id integer not null primary key autoincrement,
  nome varchar(255) not null,
  categoria varchar(255) not null,
  data integer not null,
  valor real not null
);