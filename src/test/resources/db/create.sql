Create Table Document
      (Iddocument integer Not Null,
       Name varchar(100),
       DocumentDate date,
       Cost double ,
       Flagactive varchar(1));

-- Primary Key Document
Alter Table Document Add Constraint document_pk Primary Key (Iddocument);

insert into Document Values (1, 'Prova1', PARSEDATETIME('01-01-2022','dd-MM-yyyy'), 55.24, 'S');
insert into Document Values (2, 'Prova2', PARSEDATETIME('01-01-2022','dd-MM-yyyy'), 55.24, 'S');
insert into Document Values (3, 'Prova3', PARSEDATETIME('01-01-2022','dd-MM-yyyy'), 55.24, 'S');
