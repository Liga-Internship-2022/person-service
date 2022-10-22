set search_path to clinic;

create view client_overview as
select t1.id,
       t2.last_name,
       t2.first_name,
       t2.birth_dt,
       t2.age,
       t2.sex,
       t1.phone_number,
       t1.email,
       t1.profile_link,
       t3.city,
       t3.building,
       t3.flat
from contact as t1
         left join person_data as t2 ON t1.id = t2.contact_id
         left join address as t3 ON t1.id = t3.contact_id;
