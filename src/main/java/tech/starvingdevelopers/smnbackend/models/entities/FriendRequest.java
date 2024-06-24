package tech.starvingdevelopers.smnbackend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "friend_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sender;

    private String receiver;

    @CreationTimestamp
    private LocalDateTime sent;

    public FriendRequest(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
