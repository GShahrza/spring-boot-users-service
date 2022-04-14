package az.javadevs.usersservice.dao.repository;

import az.javadevs.usersservice.dao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    @Query(value = "select * from account where username = :username and id <> :id",
            nativeQuery = true)
    Optional<Account> findByIdAndUsername(@Param("id") Long id, @Param("username") String username);
}
