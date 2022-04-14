package az.javadevs.usersservice.dao.repository;

import az.javadevs.usersservice.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByAccountId(Long id);

}
