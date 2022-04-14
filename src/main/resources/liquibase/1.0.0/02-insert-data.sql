insert into role_entity(role_name) values ('ROLE_ADMIN');
insert into role_entity(role_name) values ('ROLE_MODERATOR');
insert into role_entity(role_name) values ('ROLE_USER');

insert into  account(email,username,password) values ('gshahrza@gmail.com','gshahrza','$2a$12$9H11FCPNC7Z8fgiRQxQ6rOXs0JhAKpVZeJ6YJW/uEBidae7mpnmKC');
insert into  account(email,username,password) values ('smikayilov08@gmail.com','smikayilov08','$2a$12$LhtzOsF8cU/QmyKRwZXlhOU6I4SINMDWnRL6ubZU/r.zyW0uaXeZW');
insert into  account(email,username,password) values ('maliknuriyev@mail.ru','malikk','$2a$12$/ObWa0eNEKefiqugRSKpJuEq.kURggY.tqKJ9iyEH2gPownXDaHby');

insert into account_role(account_id, role_id) values (1,1);
insert into account_role(account_id, role_id) values (2,2);
insert into account_role(account_id, role_id) values (2,3);
insert into account_role(account_id, role_id) values (3,3);

insert into user_entity(first_name, last_name, account_id) values ('Shahrza','Gahramanov',1);
insert into user_entity(first_name, last_name, account_id) values ('Seymur','Mikayilov',2);
insert into user_entity(first_name, last_name, account_id) values ('Malik','Nuriyev',3);