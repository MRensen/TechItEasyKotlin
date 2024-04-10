insert into public.roles (active, created_date, edited_date, description, role_name) values (true,now(),now(),'administrator roles' , 'ROLE_ADMIN');
insert into public.roles (active, created_date, edited_date, description, role_name) values (true,now(),now(),'user roles' , 'ROLE_USER');
-- password: vlekkie
insert into users (created_date, edited_date,  password, user_name) values (now(),now(),'$2a$10$bJxwWc3A3DBzke7Gnb/MZ.lLXmvOIE/DFAd6QUnBvWhn7c7D1zY4C','mark');
insert into users (created_date, edited_date,  password, user_name) values (now(),now(),'$2a$10$bJxwWc3A3DBzke7Gnb/MZ.lLXmvOIE/DFAd6QUnBvWhn7c7D1zY4C','frans');
insert into user_role (role_id, user_id) values (1,1);
insert into user_role (role_id, user_id) values (2,2);