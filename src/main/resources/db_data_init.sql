-- USER TABLE
INSERT INTO public.users (username, password, enabled, role) VALUES
  ('admin', 'admin', TRUE, 'ROLE_ADMIN'),
  ('user', 'user', TRUE, 'ROLE_USER');

-- CITIES TABLE
INSERT INTO public.cities (name, country, population) VALUES
  ('Warsaw', 1, 1735442),
  ('Krakow', 1, 0),
  ('Lodz', 1, 0),
  ('Poznan', 1, 0),
  ('Gdansk', 1, 0),
  ('Berlin', 2, 3440991),
  ('London', 3, 8416535),
  ('Amsterdam', 4, 801200),
  ('Moscow', 5, 12100000);

-- COUNTRY TABLE
INSERT INTO public.countries (name, capital, area, population, currency) VALUES
  ('Poland', 1, 312685, 38000000, 'PLN'),
  ('Germany', 6, 357340, 81000000, 'EUR'),
  ('UK', 7, 244820, 63000000, 'GBP'),
  ('Netherlands', 9, 41526, 16800000, 'EUR'),
  ('Russia', 8, 17098242, 143500000, 'RUB');

-- NEIGHBOUR TABLE
INSERT INTO public.neighbours (country, neighbour) VALUES
  (1, 2),
  (1, 5),
  (2, 1),
  (2, 4),
  (4, 2),
  (5, 1);