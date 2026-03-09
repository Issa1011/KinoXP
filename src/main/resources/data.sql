INSERT INTO movie (title, release_year, genre, duration_in_minutes, format, age_limit, language)
VALUES ('Inception', 2010, 'SCIENCE_FICTION', 148, 'TWO_DIMENSIONAL', 'ELEVEN_PLUS', 'ENGLISH'),
       ('The Notebook', 2004, 'ROMANCE', 123, 'TWO_DIMENSIONAL', 'SEVEN_PLUS', 'ENGLISH'),
       ('Get Out', 2017, 'HORROR', 104, 'TWO_DIMENSIONAL', 'FIFTEEN_PLUS', 'ENGLISH'),
       ('Mad Max: Fury Road', 2015, 'ACTION', 120, 'THREE_DIMENSIONAL', 'FIFTEEN_PLUS', 'ENGLISH'),
       ('Badehotellet: Special', 2022, 'ROMANCE', 95, 'TWO_DIMENSIONAL', 'ALL', 'DANISH'),
       ('Planet X', 2024, 'SCIENCE_FICTION', 132, 'THREE_DIMENSIONAL', 'ELEVEN_PLUS', 'ENGLISH'),
       ('Night Train', 2021, 'ACTION', 110, 'TWO_DIMENSIONAL', 'SEVEN_PLUS', 'DANISH'),
       ('Haunted Hall', 2019, 'HORROR', 99, 'TWO_DIMENSIONAL', 'FIFTEEN_PLUS', 'ENGLISH'),
       ('Titanic', 1997, 'ROMANCE', 195, 'TWO_DIMENSIONAL', 'ELEVEN_PLUS', 'ENGLISH'),
       ('Druk', 2020, 'ACTION', 117, 'TWO_DIMENSIONAL', 'ELEVEN_PLUS', 'DANISH');

INSERT INTO theater (theater_name, total_rows, seats_per_row)
VALUES ('Small Theater', 20, 12),
       ('Large Theater', 25, 16);

INSERT INTO seat (theater_id, seat_number, row_number)
VALUES (1, 1, 1),
       (1, 2, 1),
       (1, 3, 1),
       (1, 4, 1),
       (1, 1, 2),
       (1, 2, 2),
       (1, 3, 2),
       (1, 4, 2),
       (2, 1, 1),
       (2, 2, 1),
       (2, 3, 1),
       (2, 4, 1),
       (2, 1, 2),
       (2, 2, 2),
       (2, 3, 2),
       (2, 4, 2);

INSERT INTO showing (movie_id, theater_id, start_time, end_time, showing_status)
VALUES (1, 1, '2026-03-15T19:00:00', '2026-03-15T21:28:00', 'UPCOMING'),
       (3, 1, '2026-03-15T22:00:00', '2026-03-15T23:44:00', 'UPCOMING'),
       (9, 2, '2026-03-16T18:30:00', '2026-03-16T21:45:00', 'UPCOMING'),
       (10, 1, '2026-03-16T21:00:00', '2026-03-16T22:57:00', 'CANCELLED'),
       (10, 2, '2026-03-03T21:00:00', '2026-03-03T22:57:00', 'COMPLETED');

INSERT INTO reservation (showing_id, customer_name, created_at, total_price, booking_status,
                         payment_status)
VALUES (1, 'Alice Jensen', '2026-03-10T12:00:00', 200.0, 'CONFIRMED', 'PAID'),
       (1, 'Mikkel Hansen', '2026-03-10T12:15:00', 100.0, 'CONFIRMED', 'AWAITING'),
       (3, 'Sara Nielsen', '2026-03-11T09:30:00', 300.0, 'PENDING', 'AWAITING');

INSERT INTO reservation_seat (reservation_id, seat_id, price)
VALUES (1, 1, 100.0),
       (1, 2, 100.0),
       (2, 3, 100.0),
       (3, 9, 100.0),
       (3, 10, 100.0),
       (3, 11, 100.0);
