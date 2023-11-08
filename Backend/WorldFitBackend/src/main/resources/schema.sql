INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),('ROLE_COACH'),('ROLE_CUSTOMER'),('ROLE_USER')
on conflict (name) do nothing;

INSERT INTO units (name)
VALUES ('Km'),('Kg'),('Minutos'),('None')
on conflict (name) do nothing;