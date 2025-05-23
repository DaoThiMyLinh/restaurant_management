package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "floors")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NamedQueries({
    @NamedQuery(name = "FloorEntity.findAll", query = "select f from FloorEntity f")
})
public class FloorEntity implements Serializable {

    @Id
    @Column(name = "floor_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long floorId;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @ToString.Exclude
    @OneToMany(mappedBy = "floor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<TableEntity> tables;

    public FloorEntity(Long floorId) {
        setFloorId(floorId);
    }

    public FloorEntity(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public FloorEntity(Long floorId, String name, int capacity) {
        setFloorId(floorId);
        this.name = name;
        this.capacity = capacity;
    }
}
