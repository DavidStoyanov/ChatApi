package org.myapp.chat_api.repository;

import org.hibernate.annotations.NamedQuery;
import org.myapp.chat_api.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    @Modifying
    @Query(value = "update User u set u.alias = :alias, u.firstName = :firstName, u.lastName = :lastName where u.id = :id")
    void updateUserProfileById(@Param("id") Long id,
                               @Param("username") String username,
                               @Param("firstName") String firstName,
                               @Param("lastName") String lastName);
}
