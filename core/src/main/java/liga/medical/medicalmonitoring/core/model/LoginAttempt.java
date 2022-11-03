package liga.medical.medicalmonitoring.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
@Table(name = "login_attempt", schema = "clinic")
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "dttm", nullable = false)
    private java.sql.Timestamp dttm;

    @Column(name = "username", length = 128)
    private String username;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "comment")
    private String comment;
}
