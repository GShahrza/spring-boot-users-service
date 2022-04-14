package az.javadevs.usersservice.dao.entity;

import az.javadevs.usersservice.dto.request.UserRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    String lastName;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "timestamp default now()", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "timestamp default now()", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    Account account = new Account();

    public UserEntity(UserRequestDTO userRequestDTO) {
        this.firstName = userRequestDTO.getFirstName();
        this.lastName = userRequestDTO.getLastName();
        this.account.setUsername(userRequestDTO.getUsername());
        this.account.setEmail(userRequestDTO.getEmail());
        this.account.setPassword(userRequestDTO.getPassword());
    }
}
