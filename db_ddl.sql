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

CREATE TABLE mpkaddress_abonent_link (
                                             uid varchar(36) NOT NULL,
                                             mpkaddress_uid varchar(36) NOT NULL,
                                             abonent_uid varchar(36) NOT NULL,
                                             CONSTRAINT mpkaddress_abonent_link_pkey PRIMARY KEY (uid)
);

CREATE UNIQUE INDEX mpkaddress_abonent_link_unique_idx ON mpkaddress_abonent_link (mpkaddress_uid, abonent_uid);

ALTER TABLE mpkaddress_abonent_link
    ADD CONSTRAINT fk_mpkaddress_abonent_link_mpkaddress FOREIGN KEY (mpkaddress_uid) REFERENCES mpk_address (uid);

ALTER TABLE mpkaddress_abonent_link
    ADD CONSTRAINT fk_mpkaddress_abonent_link_abonent FOREIGN KEY (abonent_uid) REFERENCES abonent (uid);