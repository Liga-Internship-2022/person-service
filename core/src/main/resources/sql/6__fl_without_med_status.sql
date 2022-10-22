set search_path to clinic;

select *
from person_data p
where p.id in (select id
               from medical_card mc
               where mc.med_status is null);
