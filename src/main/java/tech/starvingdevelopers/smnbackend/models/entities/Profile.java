package tech.starvingdevelopers.smnbackend.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @Column(nullable = false, length = 64)
    private String nickname;

    @Column(nullable = false, length = 64)
    private String searchableName;

    @Column(length = 200)
    private String description;

    @Lob
    private String profileImage;

    public Profile(String username, String nickname, String searchableName) {
        this.username = username;
        this.nickname = nickname;
        this.searchableName = searchableName;
    }
}
