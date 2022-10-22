package liga.medical.medicalmonitoring.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "parent_id",
            columnDefinition = "bigint check ( parent_id <> person_data.id ) references person_data (id)")
    private Long parent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_card_id", nullable = false)
    private MedicalCard medicalCard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
}
