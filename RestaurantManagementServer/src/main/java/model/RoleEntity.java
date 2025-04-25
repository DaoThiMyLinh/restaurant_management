package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
    @NamedQuery(name = "RoleEntity.findAll", query = "select r from RoleEntity r"),
        @NamedQuery(name = "RoleEntity.findByName", query = "select r from RoleEntity r where r.roleName = :name"),
})
public class RoleEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;
    
    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    public RoleEntity(Long roleId, String roleName, LocalDate createdDate) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
