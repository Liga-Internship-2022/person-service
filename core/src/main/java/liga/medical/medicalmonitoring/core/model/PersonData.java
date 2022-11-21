package liga.medical.medicalmonitoring.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person_data", schema = "clinic")
public class PersonData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "birth_dt", nullable = false)
    private java.sql.Date birthDt;

    @Column(name = "age", nullable = false)
    private Long age;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "medical_card_id", nullable = false)
    private Long medicalCardId;

    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    @Column(name = "parent_id",
            columnDefinition = "bigint check ( parent_id <> person_data.id ) references person_data (id)")
    private Long parentId;
}
