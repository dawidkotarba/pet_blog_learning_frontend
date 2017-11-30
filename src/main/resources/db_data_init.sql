-- USER TABLE
INSERT INTO public.users (username, password, enabled, role) VALUES
  ('admin', 'admin', TRUE, 'ROLE_ADMIN'),
  ('user', 'user', TRUE, 'ROLE_USER');

-- AUTHORS TABLE
INSERT INTO public.authors (username, firstname, lastname) VALUES
  ('dawidk', 'Dawid', 'K.');

-- POSTS TABLE
INSERT INTO public.posts (author_id, subject, body, published) VALUES
  (1, 'Test post', 'Test post body', '2017-11-29');

-- COMMENTS TABLE
INSERT INTO public.comments (author, subject, body, post_id) VALUES
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1),
  ('Stefan', 'Test comment', 'Test comment body', 1);