CREATE TABLE m_user
(
    user_id serial NOT NULL,
    password character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL,
    is_deleted integer NOT NULL,
    CONSTRAINT m_user_pk1 PRIMARY KEY (user_id)
)

CREATE TABLE m_user_detail
(
    user_detail_id serial NOT NULL ,
    user_id integer NOT NULL,
    first_name character varying(255)  NOT NULL,
    last_name character varying(255)  NOT NULL,
    middle_name character varying(255),
    ph_number character varying(255),
    email character varying(255) ,
    CONSTRAINT m_user_detail_uk1 PRIMARY KEY (user_detail_id),
    CONSTRAINT m_user_detail_fk1 FOREIGN KEY (user_id) REFERENCES m_user(user_id)
)

CREATE TABLE m_role
(
    role_id serial NOT NULL,
    name character varying(255)  NOT NULL,
    description character varying(255) ,
    CONSTRAINT m_role_pk1 PRIMARY KEY (role_id)
)


CREATE TABLE m_user_role_mapping
(
    user_role_id serial NOT NULL,
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT m_user_role_mapping_pk1 PRIMARY KEY (user_role_id),
    CONSTRAINT m_user_role_mapping_fk1 FOREIGN KEY (user_id) REFERENCES m_user(user_id),
    CONSTRAINT m_user_role_mapping_fk2 FOREIGN KEY (role_id) REFERENCES m_role(role_id)
)

INSERT INTO public.m_user(
    password, user_name,is_deleted)
    VALUES ('[3DES]qh06V+0jBTVYUXH45SmWZA==', 'admin', 0);

INSERT INTO public.m_user(
    password, user_name,is_deleted)
    VALUES ('[3DES]qh06V+0jBTVYUXH45SmWZA==', 'member', 0);

INSERT INTO public.m_user(
    password, user_name,is_deleted)
    VALUES ('[3DES]qh06V+0jBTVYUXH45SmWZA==', 'provider', 0);

INSERT INTO public.m_user_detail(
 user_id, first_name, last_name, middle_name, ph_number, email)
 VALUES ((select user_id from public.m_user where user_name = 'admin'), 'admin', 'healthedge', 'user', '809767876567', 'admin@gmail.com');

 INSERT INTO public.m_user_detail(
 user_id, first_name, last_name, middle_name, ph_number, email)
 VALUES ((select user_id from public.m_user where user_name = 'member'), 'member', 'healthedge', 'user', '23431234532', 'member@gmail.com');

 INSERT INTO public.m_user_detail(
 user_id, first_name, last_name, middle_name, ph_number, email)
 VALUES ((select user_id from public.m_user where user_name = 'provider'), 'provider', 'healthedge', 'user', '12690654321', 'provider@gmail.com');


 INSERT INTO public.m_user_role_mapping(
   user_id, role_id)
  VALUES ( (select user_id from public.m_user where user_name = 'admin'), (select role_id from public.m_role where name = 'ROLE_ADMIN'));

INSERT INTO public.m_user_role_mapping(
   user_id, role_id)
  VALUES ( (select user_id from public.m_user where user_name = 'member'), (select role_id from public.m_role where name = 'ROLE_MEMBER'));

INSERT INTO public.m_user_role_mapping(
   user_id, role_id)
  VALUES ( (select user_id from public.m_user where user_name = 'provider'), (select role_id from public.m_role where name = 'ROLE_PROVIDER'));


INSERT INTO public.m_role(name, description)
    VALUES ('ROLE_ADMIN', 'admin role');

INSERT INTO public.m_role(name, description)
    VALUES ('ROLE_MEMBER', 'member role');

INSERT INTO public.m_role(name, description)
    VALUES ('ROLE_PROVIDER', 'provider role');

