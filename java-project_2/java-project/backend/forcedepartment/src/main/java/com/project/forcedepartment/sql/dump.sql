DROP TABLE IF EXISTS public.website_user CASCADE;
CREATE TABLE public.website_user
(
    id           serial  NOT NULL PRIMARY KEY,
    first_name varchar(100),
    last_name         varchar(100),
    birth_date          varchar(100) NOT NULL,
    email           varchar(150),
    is_admin      boolean,
    password varchar(300),
    registration_date varchar(150) NOT NULL,
    group_name     text
    -- ,image text
);

ALTER TABLE IF EXISTS ONLY public.worker DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;

DROP TABLE IF EXISTS public.worker CASCADE;
CREATE TABLE public.worker
(
    id          serial  NOT NULL PRIMARY KEY,
    user_id     integer NOT NULL,
    phone_number varchar(200),
    is_available boolean,
    rate numeric,
    description text
);

ALTER TABLE ONLY public.worker
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.website_user(id);


DROP TABLE IF EXISTS public.profession CASCADE;
CREATE TABLE public.profession
(
    id          serial  NOT NULL PRIMARY KEY,
    profession_name     varchar(200)
);


ALTER TABLE IF EXISTS ONLY public.worker_experience DROP CONSTRAINT IF EXISTS fk_profession_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.worker_experience DROP CONSTRAINT IF EXISTS fk_worker_id CASCADE;

DROP TABLE IF EXISTS public.worker_experience CASCADE;
CREATE TABLE public.worker_experience
(
    id            serial  NOT NULL PRIMARY KEY,
    worker_id     integer NOT NULL,
    profession_id integer NOT NULL,
    experience_years    numeric NOT NULL
);

ALTER TABLE ONLY public.worker_experience
    ADD CONSTRAINT fk_profession_id FOREIGN KEY (profession_id) REFERENCES public.profession(id);

ALTER TABLE ONLY public.worker_experience
    ADD CONSTRAINT fk_worker_id FOREIGN KEY (worker_id) REFERENCES public.website_user(id);


DROP TABLE IF EXISTS public.work_object CASCADE;
CREATE TABLE public.work_object
(
    id          serial  NOT NULL PRIMARY KEY,
    work_object     varchar(200)
);

ALTER TABLE IF EXISTS ONLY public.worker_requirement DROP CONSTRAINT IF EXISTS fk_profession_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.worker_requirement DROP CONSTRAINT IF EXISTS fk_work_object_id CASCADE;


DROP TABLE IF EXISTS public.work_requirement CASCADE;
CREATE TABLE public.work_requirement
(
    id          serial  NOT NULL PRIMARY KEY,
    work_object_id     integer,
    profession_id integer
);

ALTER TABLE ONLY public.work_requirement
    ADD CONSTRAINT fk_profession_id FOREIGN KEY (profession_id) REFERENCES public.profession(id);

ALTER TABLE ONLY public.work_requirement
    ADD CONSTRAINT fk_work_object_id FOREIGN KEY (work_object_id) REFERENCES public.work_object(id);

INSERT INTO public.website_user VALUES (1, 'Oakley', 'Burns', '2000-10-10', 'burns.oakley@gmail.com', false, '1234', '2021-10-10', 'USER');
INSERT INTO public.website_user VALUES (2, 'Chase', 'Price', '1990-10-10', 'price.chase@gmail.com', false, '1234', '2021-10-10','WORKER');
INSERT INTO public.website_user VALUES (3, 'Harris', 'Reynolds', '1980-10-10', 'reynolds.harris@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (4, 'Spencer', 'Hayes', '1970-10-10', 'hayes.spence@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (5, 'Teddy', 'Hill', '1973-10-10', 'hill.tedd@gmail.com', false, '1234', '2021-10-10', 'USER');
INSERT INTO public.website_user VALUES (6, 'Lukas', 'Phillips', '1967-10-10', 'phillips.lukas@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (7, 'Maximus', 'Morris', '1978-10-10', 'morris.maximus@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (8, 'Franklin', 'Chambers', '1984-10-10', 'chambers.franklin@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (9, 'Lennox', 'Cole', '1976-10-10', 'cole.lennox@gmail.com', false, '1234', '2021-10-10', 'USER');
INSERT INTO public.website_user VALUES (10, 'Elis', 'Cooper', '1984-10-10', 'cooper.elis@gmail.com', false, '1234', '2021-10-10', 'USER');
INSERT INTO public.website_user VALUES (11, 'Tobias', 'Ball', '1974-10-10', 'ball.tobias@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (12, 'Jacob', 'Mccarthy', '1985-10-10', 'mccarthy.jacob@gmail.com', false, '1234', '2021-10-10', 'WORKER');
INSERT INTO public.website_user VALUES (13, 'Paul', 'Rodgers', '1960-10-10', 'rodgers.paul@gmail.com', false, '1234', '2021-10-10', 'WORKER');
SELECT pg_catalog.setval('website_user_id_seq', 13, true);


INSERT INTO public.worker VALUES (1, 2, '+36305121220', false, 7.4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.');
INSERT INTO public.worker VALUES (2, 3,'+36302548320', false, 5.2, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
INSERT INTO public.worker VALUES (3, 4, '+36305799390', false, 8.9, 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.');
INSERT INTO public.worker VALUES (4, 6, '+36205739220', false, 7.1, 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam.');
INSERT INTO public.worker VALUES (5, 7, '+36301154960', false, 7.8, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.');
INSERT INTO public.worker VALUES (6, 8, '+36705792850', false, 6.3, 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.');
INSERT INTO public.worker VALUES (7, 11, '+36705792850', false, 9.1, 'Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.');
INSERT INTO public.worker VALUES (8, 12, '+36709482320', false, 8.2, 'Vivamus vel ex vitae orci dictum feugiat quis et tortor. Ut vehicula metus diam, tempor vulputate metus bibendum et. Etiam rhoncus ex a mauris suscipit lacinia. Etiam pulvinar posuere leo, vel malesuada sapien tristique in. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc fermentum nisi ac purus consectetur, in semper risus mollis.');
INSERT INTO public.worker VALUES (9, 13, '+36305733321', false, 8.7, 'Vivamus vautem quibusdam et aut officiis debitis et tortor. Ut vehicula metus diam, tempor vulputate metus ut et voluptates repudiandae sint et molestiae non vel malesuada sapien tristique in. Interdum et malesuada fames.');
SELECT pg_catalog.setval('worker_id_seq', 9, true);


INSERT INTO public.profession VALUES (1, 'Carpenter');
INSERT INTO public.profession VALUES (2, 'Electrician');
INSERT INTO public.profession VALUES (3, 'Gas Fitter');
INSERT INTO public.profession VALUES (4, 'Plumber');
INSERT INTO public.profession VALUES (5, 'Painter');
INSERT INTO public.profession VALUES (6, 'Cabinet Makers');
INSERT INTO public.profession VALUES (7, 'Mason');
INSERT INTO public.profession VALUES (8, 'Machinist');
INSERT INTO public.profession VALUES (9, 'Sheet Metal Worker');
INSERT INTO public.profession VALUES (10, 'Heating and Cooling System Mechanic');
INSERT INTO public.profession VALUES (11, 'Construction Helper');
SELECT pg_catalog.setval('profession_id_seq', 11, true);


INSERT INTO public.worker_experience VALUES (1, 2, 4, 12);
INSERT INTO public.worker_experience VALUES (2, 2, 9, 7);
INSERT INTO public.worker_experience VALUES (3, 3, 2, 9);
INSERT INTO public.worker_experience VALUES (4, 4, 7, 14);
INSERT INTO public.worker_experience VALUES (5, 4, 8, 9);
INSERT INTO public.worker_experience VALUES (6, 4, 9, 6);
INSERT INTO public.worker_experience VALUES (7, 6, 11, 10);
INSERT INTO public.worker_experience VALUES (8, 6, 10, 3);
INSERT INTO public.worker_experience VALUES (9, 7, 1, 8);
INSERT INTO public.worker_experience VALUES (10, 8, 4, 15);
INSERT INTO public.worker_experience VALUES (11, 8, 7, 12);
INSERT INTO public.worker_experience VALUES (12, 8, 3, 18);
INSERT INTO public.worker_experience VALUES (13, 11, 6, 9);
INSERT INTO public.worker_experience VALUES (14, 11, 1, 14);
INSERT INTO public.worker_experience VALUES (15, 12, 5, 11);
INSERT INTO public.worker_experience VALUES (16, 13, 4, 17);
SELECT pg_catalog.setval('worker_experience_id_seq', 16, true);

INSERT INTO public.work_object VALUES (1, 'Bedroom');
INSERT INTO public.work_object VALUES (2, 'Bathroom');
INSERT INTO public.work_object VALUES (3, 'Kitchen');
INSERT INTO public.work_object VALUES (4, 'Living Room');
INSERT INTO public.work_object VALUES (5, 'Hall');
INSERT INTO public.work_object VALUES (6, 'Balcony');
INSERT INTO public.work_object VALUES (7, 'Terrace');
INSERT INTO public.work_object VALUES (8, 'Attic');
INSERT INTO public.work_object VALUES (9, 'Outbuilding');
INSERT INTO public.work_object VALUES (10, 'Garage');
INSERT INTO public.work_object VALUES (11, 'Roof');
INSERT INTO public.work_object VALUES (12, 'Basement');
INSERT INTO public.work_object VALUES (13, 'House Structure');
SELECT pg_catalog.setval('work_object_id_seq', 13, true);

INSERT INTO public.work_requirement VALUES (1, 1, 1);
INSERT INTO public.work_requirement VALUES (2, 1, 2);
INSERT INTO public.work_requirement VALUES (3, 1, 5);
INSERT INTO public.work_requirement VALUES (4, 1, 6);
INSERT INTO public.work_requirement VALUES (5, 1, 7);
INSERT INTO public.work_requirement VALUES (6, 1, 10);
INSERT INTO public.work_requirement VALUES (7, 1, 11);
INSERT INTO public.work_requirement VALUES (8, 2, 2);
INSERT INTO public.work_requirement VALUES (9, 2, 3);
INSERT INTO public.work_requirement VALUES (10, 2, 4);
INSERT INTO public.work_requirement VALUES (11, 2, 5);
INSERT INTO public.work_requirement VALUES (12, 2, 6);
INSERT INTO public.work_requirement VALUES (13, 2, 7);
INSERT INTO public.work_requirement VALUES (14, 2, 10);
INSERT INTO public.work_requirement VALUES (15, 3, 2);
INSERT INTO public.work_requirement VALUES (16, 3, 4);
INSERT INTO public.work_requirement VALUES (17, 3, 5);
INSERT INTO public.work_requirement VALUES (18, 3, 8);
INSERT INTO public.work_requirement VALUES (19, 3, 11);
INSERT INTO public.work_requirement VALUES (20, 4, 1);
INSERT INTO public.work_requirement VALUES (21, 4, 2);
INSERT INTO public.work_requirement VALUES (22, 4, 3);
INSERT INTO public.work_requirement VALUES (23, 4, 5);
INSERT INTO public.work_requirement VALUES (24, 4, 10);
INSERT INTO public.work_requirement VALUES (25, 4, 11);
INSERT INTO public.work_requirement VALUES (26, 5, 2);
INSERT INTO public.work_requirement VALUES (27, 5, 5);
INSERT INTO public.work_requirement VALUES (28, 5, 6);
INSERT INTO public.work_requirement VALUES (29, 5, 9);
INSERT INTO public.work_requirement VALUES (30, 5, 10);
INSERT INTO public.work_requirement VALUES (31, 5, 11);
INSERT INTO public.work_requirement VALUES (32, 6, 5);
INSERT INTO public.work_requirement VALUES (33, 6, 7);
INSERT INTO public.work_requirement VALUES (34, 6, 8);
INSERT INTO public.work_requirement VALUES (35, 6, 9);
INSERT INTO public.work_requirement VALUES (36, 6, 11);
INSERT INTO public.work_requirement VALUES (37, 7, 7);
INSERT INTO public.work_requirement VALUES (38, 7, 9);
INSERT INTO public.work_requirement VALUES (39, 7, 11);
INSERT INTO public.work_requirement VALUES (40, 8, 1);
INSERT INTO public.work_requirement VALUES (41, 8, 2);
INSERT INTO public.work_requirement VALUES (42, 8, 5);
INSERT INTO public.work_requirement VALUES (43, 8, 6);
INSERT INTO public.work_requirement VALUES (44, 8, 9);
INSERT INTO public.work_requirement VALUES (45, 8, 10);
INSERT INTO public.work_requirement VALUES (46, 8, 11);
INSERT INTO public.work_requirement VALUES (47, 9, 1);
INSERT INTO public.work_requirement VALUES (48, 9, 2);
INSERT INTO public.work_requirement VALUES (49, 9, 3);
INSERT INTO public.work_requirement VALUES (50, 9, 4);
INSERT INTO public.work_requirement VALUES (51, 9, 5);
INSERT INTO public.work_requirement VALUES (52, 9, 7);
INSERT INTO public.work_requirement VALUES (53, 9, 8);
INSERT INTO public.work_requirement VALUES (54, 9, 9);
INSERT INTO public.work_requirement VALUES (55, 9, 10);
INSERT INTO public.work_requirement VALUES (56, 10, 1);
INSERT INTO public.work_requirement VALUES (57, 10, 2);
INSERT INTO public.work_requirement VALUES (58, 10, 5);
INSERT INTO public.work_requirement VALUES (59, 10, 7);
INSERT INTO public.work_requirement VALUES (60, 10, 9);
INSERT INTO public.work_requirement VALUES (61, 11, 1);
INSERT INTO public.work_requirement VALUES (62, 11, 9);
INSERT INTO public.work_requirement VALUES (63, 11, 11);
INSERT INTO public.work_requirement VALUES (64, 12, 2);
INSERT INTO public.work_requirement VALUES (65, 12, 3);
INSERT INTO public.work_requirement VALUES (66, 12, 7);
INSERT INTO public.work_requirement VALUES (67, 12, 8);
INSERT INTO public.work_requirement VALUES (68, 12, 9);
INSERT INTO public.work_requirement VALUES (69, 12, 10);
INSERT INTO public.work_requirement VALUES (70, 12, 11);
INSERT INTO public.work_requirement VALUES (71, 13, 1);
INSERT INTO public.work_requirement VALUES (72, 13, 3);
INSERT INTO public.work_requirement VALUES (73, 13, 7);
INSERT INTO public.work_requirement VALUES (74, 13, 11);
SELECT pg_catalog.setval('work_requirement_id_seq', 13, true);