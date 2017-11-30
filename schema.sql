CREATE TABLE public.user (
  id      SERIAL PRIMARY KEY NOT NULL,
  name    VARCHAR            NOT NULL,
  created TIMESTAMP          NOT NULL
);
