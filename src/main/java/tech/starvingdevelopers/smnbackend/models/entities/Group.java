package tech.starvingdevelopers.smnbackend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 36)
    private String ownerName;

    @Column(nullable = false, length = 64)
    private String customName;

    @Column(nullable = false, length = 64)
    private String searchableName;

    @Column(length = 200)
    private String description;

    @Lob
    private String profileImage;

    @Column(nullable = false, length = 7)
    private String baseColor;

    public Group(String customName, String searchableName, String description, String baseColor) {
        this.customName = customName;
        this.searchableName = searchableName;
        this.description = description;
        this.baseColor = baseColor;
    }
}
