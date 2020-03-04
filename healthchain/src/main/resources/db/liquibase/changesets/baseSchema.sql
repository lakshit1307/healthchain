CREATE TABLE m_user
(
    user_id serial NOT NULL,
    password character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL,
    is_deleted integer NOT NULL,
    CONSTRAINT m_user_pk1 PRIMARY KEY (user_id)
)