set search_path to clinic;

delete
from illness i1 using illness i2
where i1.id > i2.id
  and i1.medical_card_id = i2.medical_card_id
  and i1.type_id = i2.type_id
  and i1.heaviness = i2.heaviness
  and i1.appearance_dttm = i2.appearance_dttm
  and i1.recovery_dt = i2.recovery_dt;
