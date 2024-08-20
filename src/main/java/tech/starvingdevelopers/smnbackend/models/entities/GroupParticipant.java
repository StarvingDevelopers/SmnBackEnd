package tech.starvingdevelopers.smnbackend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "group_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long groupID;

    @Column(nullable = false, length = 36)
    private String username;

    public GroupParticipant(long groupID, String username) {
        this.groupID = groupID;
        this.username = username;
    }
}
