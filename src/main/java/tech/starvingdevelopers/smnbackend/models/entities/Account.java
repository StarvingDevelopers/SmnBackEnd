package tech.starvingdevelopers.smnbackend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @Column(unique = true, nullable = false, length = 36)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    public Account(String username, String email, String password, String gender, LocalDate birthdate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthdate = birthdate;
    }
}
