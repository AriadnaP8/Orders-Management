drop database if exists comenzi;
create database if not exists comenzi;
use comenzi;

create table Client (
	id int primary key,
    varsta int,
    nume varchar(45),
    email varchar(55)
);

create table Produs(
	id int, 
    nume_produs varchar(45),
    pret int, 
    stoc int
);

create table Comanda(
	id int,
    id_produs int,
    id_client int,
    cantitate int,
    pret int
);


