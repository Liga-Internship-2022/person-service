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
@Table(name = "illness", schema = "clinic")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Column(name = "heaviness", nullable = false)
    private String heaviness;

    @Column(name = "appearance_dttm", nullable = false)
    private java.sql.Timestamp appearanceDttm;

    @Column(name = "recovery_dt", nullable = false)
    private java.sql.Date recoveryDt;

    @Column(name = "medical_card_id", nullable = false)
    private Long medicalCardId;
}
