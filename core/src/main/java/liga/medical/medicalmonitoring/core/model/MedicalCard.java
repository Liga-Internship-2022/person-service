package liga.medical.medicalmonitoring.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "client_status", nullable = false)
    private String clientStatus;

    @Column(name = "med_status")
    private String medStatus;

    @Column(name = "registry_dt", nullable = false)
    private java.sql.Date registryDt;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "comment", nullable = false)
    private String comment;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicalCard", cascade = CascadeType.ALL)
    private Set<Illness> illnesses;

    @OneToOne(mappedBy = "medicalCard", orphanRemoval = true, cascade = CascadeType.ALL)
    private PersonData personData;
}
