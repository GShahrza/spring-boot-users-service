
create table account
(
    id bigserial PRIMARY KEY,
    email      varchar(40) NOT NULL,
    username   varchar(15) NOT NULL,
    password   varchar NOT NULL,
    is_active  boolean   DEFAULT true,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

create table account_role
(
    account_id bigint NOT NULL,
    role_id    bigint NOT NULL,
    FOREIGN key (account_id)
        references account (id),
    FOREIGN key (role_id)
        references role_entity (id)
);

create table user_entity
(
    id bigserial PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name  varchar(20) NOT NULL,
    account_id bigint      NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    FOREIGN KEY (account_id)
        references account (id)
);