CREATE SCHEMA IF NOT EXISTS CONF;
CREATE SCHEMA IF NOT EXISTS PROFILE;
CREATE SCHEMA IF NOT EXISTS SYS;
CREATE SCHEMA IF NOT EXISTS PORANDU;

-- Esto debe existir, lo demás se crea por JPA
CREATE TABLE IF NOT EXISTS sys.security_profile (
    id bigserial NOT NULL,
    key character varying(50) NOT NULL,
    name character varying(50) NOT NULL,
    max_number_of_connections_per_user integer,
    max_number_of_connections integer,
    refresh_token_timeout_seconds integer,
    access_token_timeout_seconds integer,
    revocable boolean DEFAULT false NOT NULL,
    max_access_token_requests integer
);

--delete from sys.tokens;
--delete from sys.security_profile;
INSERT INTO sys.security_profile(
            key, name, max_number_of_connections_per_user, max_number_of_connections,
            refresh_token_timeout_seconds, access_token_timeout_seconds,
            revocable, max_access_token_requests)
    VALUES ( 'DEFAULT', 'Default configuration(testing purposes)', 1, null,
            14440, 600,
            false, 2);

