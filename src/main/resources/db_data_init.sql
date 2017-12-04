-- USER TABLE
INSERT INTO public.users (uuid,username, password, enabled, role) VALUES
  ('1', 'admin', 'admin', TRUE, 'ROLE_ADMIN'),
  ('2', 'user', 'user', TRUE, 'ROLE_USER');

-- AUTHORS TABLE
INSERT INTO public.authors (uuid, username, firstname, lastname) VALUES
  ('1', 'dawidk', 'Dawid', 'K.');

-- POSTS TABLE
INSERT INTO public.posts (uuid, author_id, subject, body, published) VALUES
  ('1', 1, 'Test post', 'Test post body', '2017-11-29');

-- COMMENTS TABLE
INSERT INTO public.comments (uuid, author, subject, body, post_id, published) VALUES
  ('1', 'Stefan', 'Test comment', 'Test comment body', 1, '2017-12-01'),
  ('2', 'Stefan', 'Test comment', 'Test comment body', 1, '2017-11-29'),
  ('3', 'Stefan', 'Test comment', 'Test comment body', 1,'2017-11-30');