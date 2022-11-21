set search_path to clinic;

select *
from medical_card
inner join person_data pd on medical_card.id = pd.medical_card_id;