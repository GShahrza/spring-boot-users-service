package az.javadevs.usersservice.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", nullable = false, unique = true, length = 15)
    String username;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "timestamp default now()", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "timestamp default now()", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    Set<RoleEntity> roles = new HashSet<>();
}
