create table Company (
    companyName CHAR(30),
    id          INT(4) PRIMARY KEY
);
create table EmployeesHired (
id            INT(4),
numHired      INT(4),
fiscalQuarter INT(4),
FOREIGN KEY (id) REFERENCES Company
);
insert into EmployeesHired values (3,3,3);
insert into EmployeesHired values (3,2,4);
insert into EmployeesHired values (19,4,1);
insert into EmployeesHired values (6,2,1);

create table address (
    street CHAR(30) NOT NULL,
    apartment CHAR(10),
    city CHAR(40) NOT NULL,
)

