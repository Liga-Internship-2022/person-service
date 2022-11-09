set search_path to clinic;

-- medical_card
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (1, 'M', 'F', '2022-06-26', 'Jetwire');
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (2, 'M', null, '2022-03-23', 'InnoZ');
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (3, 'M', 'M', '2022-01-01', 'Fivechat');
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (4, 'M', 'M', '2021-11-29', 'Shufflester');
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (5, 'F', 'F', '2022-01-29', 'Divape');
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (6, 'M', 'F', '2022-01-08', 'Kayveo');
insert into medical_card (id, client_status, med_status, registry_dt, comment) values (7, 'F', null, '2022-01-13', 'Wikizz');

-- contact
insert into contact (id, phone_number, email, profile_link) values (1, '605-350-7402', 'sedscer0@naver.com', 'sogou.com');
insert into contact (id, phone_number, email, profile_link) values (2, '447-139-8614', 'sdennison1@sbwire.com', 'samsung.com');
insert into contact (id, phone_number, email, profile_link) values (3, '312-540-1675', 'eceresa2@istockphoto.com', 'opensource.org');
insert into contact (id, phone_number, email, profile_link) values (4, '735-637-3646', 'pgummary3@accuweather.com', 'shinystat.com');
insert into contact (id, phone_number, email, profile_link) values (5, '627-869-8498', 'jloadsman4@hibu.com', 'msu.edu');
insert into contact (id, phone_number, email, profile_link) values (6, '729-802-5538', 'lhesey5@thetimes.co.uk', 'statcounter.com');
insert into contact (id, phone_number, email, profile_link) values (7, '920-383-0712', 'cbraunle6@adobe.com', 'google.co.jp');

-- illness
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values (1, 2, 4, 'F', '2022-04-23 03:26:19', '2022-02-22');
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values (2, 3, 6, 'F', '2022-02-28 23:26:39', '2022-04-16');
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values (3, 4, 8, 'M', '2022-09-29 17:13:42', '2022-02-19');
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values (4, 5, 10, 'F', '2022-03-24 02:47:02', '2022-06-30');
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values (5, 6, 12, 'F', '2022-06-01 21:02:11', '2021-11-04');
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values (6, 7, 14, 'F', '2021-11-18 19:55:50', '2022-09-10');

-- person_data
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id) values (1, 'Kainov', 'Kirill', '2021-12-22', 1, 'F', 1, 1);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values (2, 'Кайнов', 'Кирилл', '2022-02-01', 2, 'M', 2, 2, 1);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values (3, 'Хенкс', 'Том', '2022-09-03', 3, 'M', 3, 3, 2);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values (4, 'Николсон', 'Джек', '2022-01-02', 4, 'M', 4, 4, 2);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values (5, 'Дикаприо', 'Леонардо', '2022-04-03', 5, 'M', 5, 5, 3);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values (6, 'Хоффман', 'Дастин', '2022-05-19', 6, 'M', 6, 6, 4);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values (7, 'Питт', 'Брэд', '2022-04-23', 7, 'F', 7, 7, 1);

-- address
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (2, 1, 1, 'Garanhuns', 1, '0663 Springview Hill', 'Point', '6');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (3, 2, 2, 'Nagorsk', 2, '45724 Mifflin Park', 'Circle', '073');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (4, 3, 3, 'Motala', 3, '32304 Bonner Center', 'Alley', '431');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (5, 4, 4, 'Manikchari', 4, '652 Shoshone Terrace', 'Center', '2159');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (6, 5, 5, 'Passo Fundo', 5, '9272 Anderson Crossing', 'Road', '971');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (7, 6, 6, 'Rumboci', 6, '79831 Farragut Park', 'Center', '9219');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values (8, 7, 7, 'Daet', 7, '761 Ludington Place', 'Terrace', '1');
