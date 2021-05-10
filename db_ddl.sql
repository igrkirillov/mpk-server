CREATE TABLE mpk (
     uid varchar(36) NOT NULL,
     field varchar(256) NULL,
     CONSTRAINT mpk_pkey PRIMARY KEY (uid)
);

CREATE TABLE mpk_address (
    uid varchar(36) NOT NULL,
    full_name varchar(1024) NOT NULL,
    fias_uid varchar(36) NOT NULL,
    zip varchar(6) NOT NULL,
    CONSTRAINT mpk_address_pkey PRIMARY KEY (uid)
);

CREATE UNIQUE INDEX mpk_address_unique_idx ON mpk_address (fias_uid);

CREATE TABLE abonent (
     uid varchar(36) NOT NULL,
     full_name varchar(1024) NOT NULL,
     CONSTRAINT abonent_pkey PRIMARY KEY (uid)
);

CREATE UNIQUE INDEX mpk_abonent_unique_idx ON abonent (full_name);