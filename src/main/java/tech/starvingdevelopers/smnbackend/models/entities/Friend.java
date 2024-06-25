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
@Table(name = "friends")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Friend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "left_relation_user")
    private Account leftUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_relation_user")
    private Account rightUsername;

    @CreationTimestamp
    private LocalDate createdAt;

    public Friend(Account leftUsername, Account rightUsername) {
        this.leftUsername = leftUsername;
        this.rightUsername = rightUsername;
    }
}
