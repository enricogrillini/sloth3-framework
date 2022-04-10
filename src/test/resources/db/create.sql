Create Table Document
      (Iddocument integer Not Null,
       Name varchar(100),
       DocumentDate timestamp,
       Cost double ,
       Flagactive varchar(1));

-- Primary Key Document
Alter Table Document Add Constraint document_pk Primary Key (Iddocument);