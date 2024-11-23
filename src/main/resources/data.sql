INSERT INTO project (name_project, description, created_date)
VALUES
    ('email', 'e-mail for corporative', '2018-01-02'),
    ('icq', 'messenger for my friends', '2021-08-27'),
    ('game Klad', 'a game project with Lib-GDX', '2024-03-01');

INSERT INTO users (username, password, email, role)
VALUES
    ('Andrey', '12345', 'andnot@yandex.ru', 'admin'),
    ('Ivan', '12345', 'ogk@penzmash.ru', 'programmer'),
    ('Sergey', '12345', 'otd@penzmash.ru', 'tester'),
    ('Nikita', '12345', 'ogk@penzmash.ru', 'programmer');

INSERT INTO users_projects (project_id, user_id)
VALUES
    (1, 1), (1, 2), (1, 3),
    (2, 1), (2, 4),
    (3, 1), (3, 3);
