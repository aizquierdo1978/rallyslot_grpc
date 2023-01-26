CREATE TABLE IF NOT EXISTS CATEGORIA
(
   categoriaId IDENTITY  NOT NULL,
   categoriaName varchar(255) NOT NULL,
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(categoriaId)
);

CREATE TABLE IF NOT EXISTS CLUB
(
   clubId IDENTITY  NOT NULL,
   clubName varchar(255) NOT NULL,
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(clubId)
);

CREATE TABLE IF NOT EXISTS PILOTO
(
   pilotoId IDENTITY  NOT NULL,
   pilotoName varchar(255) NOT NULL,
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(pilotoId)
);

CREATE TABLE IF NOT EXISTS PRUEBA
(
   pruebaId IDENTITY  NOT NULL,
   ano INTEGER NOT NULL,
   clubId BIGINT NOT NULL,
   pruebaName varchar(255) NOT NULL,
   secciones INTEGER NOT NULL DEFAULT 0,
   tramos INTEGER NOT NULL DEFAULT 0,
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(pruebaId),  
   foreign key (clubId) references club(clubId)
);

CREATE TABLE IF NOT EXISTS INSCRITO
(
   inscritoId IDENTITY  NOT NULL,
   pruebaId BIGINT  NOT NULL,
   pilotoId BIGINT NOT NULL, 
   categoriaId BIGINT NOT NULL,
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(inscritoId),  
   foreign key (pruebaId) references prueba(pruebaId),
   foreign key (categoriaId) references categoria(categoriaId)
);

CREATE TABLE IF NOT EXISTS TRAMO
(
   tramoId IDENTITY  NOT NULL,
   tramoPruebaId BIGINT  NOT NULL,
   inscritoId BIGINT  NOT NULL,   
   tiempo NUMERIC(10,3),
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(tramoId),  
   foreign key (inscritoId) references inscrito(inscritoId)
);