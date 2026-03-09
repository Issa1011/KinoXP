INSERT INTO movie (title, release_year, genre, duration_in_minutes, format, age_limit, language) VALUES
('Inception', 2010, 'SCIENCE_FICTION', 148, 'TWO_DIMENSIONAL', 'ELEVEN_PLUS', 'ENGLISH'),
('The Notebook', 2004, 'ROMANCE', 123, 'TWO_DIMENSIONAL', 'SEVEN_PLUS', 'ENGLISH'),
('Get Out', 2017, 'HORROR', 104, 'TWO_DIMENSIONAL', 'FIFTEEN_PLUS', 'ENGLISH'),
('Mad Max: Fury Road', 2015, 'ACTION', 120, 'THREE_DIMENSIONAL', 'FIFTEEN_PLUS', 'ENGLISH'),
('Badehotellet: Special', 2022, 'ROMANCE', 95, 'TWO_DIMENSIONAL', 'ALL', 'DANISH'),
('Planet X', 2024, 'SCIENCE_FICTION', 132, 'THREE_DIMENSIONAL', 'ELEVEN_PLUS', 'ENGLISH'),
('Night Train', 2021, 'ACTION', 110, 'TWO_DIMENSIONAL', 'SEVEN_PLUS', 'DANISH'),
('Haunted Hall', 2019, 'HORROR', 99, 'TWO_DIMENSIONAL', 'FIFTEEN_PLUS', 'ENGLISH'),
('Titanic', 1997, 'ROMANCE', 195, 'TWO_DIMENSIONAL', 'ELEVEN_PLUS', 'ENGLISH'),
('Druk', 2020, 'ACTION', 117, 'TWO_DIMENSIONAL', 'ELEVEN_PLUS', 'DANISH');

INSERT INTO showing (showing_id, movie_id, start_time)
VALUES (1, 1, '2026-03-09T20:00:00');

INSERT INTO users (name, date_of_birth, role, email, password)
VALUES
    ('Alice Jensen',      '1990-05-14', 'ADMIN',    'alice@kinoxp.dk',  'password123'),
    ('Bob Nielsen',       '1985-11-23', 'CUSTOMER', 'bob@kinoxp.dk',    'password123'),
    ('Clara Madsen',      '2000-03-07', 'CUSTOMER', 'clara@kinoxp.dk',  'password123'),
    ('David Larsen',      '1995-08-19', 'EMPLOYEE', 'david@kinoxp.dk',  'password123'),
    ('Emma Christensen',  '1988-12-01', 'CUSTOMER', 'emma@kinoxp.dk',   'password123');