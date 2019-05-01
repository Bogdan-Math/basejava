INSERT INTO resume (uuid, full_name)
VALUES ('uuid_1', 'full_name_1'),
       ('uuid_2', 'full_name_2'),
       ('uuid_3', 'full_name_3'),
       ('uuid_4', 'full_name_4');

INSERT INTO contact (type, value, resume_uuid)
VALUES ('PHONE', '123', 'uuid_1'),
       ('MOBILE', '2342', 'uuid_1'),

       ('HOME_PHONE', '67', 'uuid_2'),
       ('SKYPE', 'skype_value', 'uuid_2'),

       ('MAIL', 'mail.value@.gmail.com', 'uuid_3'),
       ('LINKEDIN', 'http://...', 'uuid_3'),

       ('GITHUB', 'http://github', 'uuid_4'),
       ('HOME_PAGE', 'http://home_page', 'uuid_4');