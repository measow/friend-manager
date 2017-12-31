package com.ocupe.repositories;

import com.ocupe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // TODO: specify query
    List<User> findFriendsFor(int userId);
    // TODO: specify query
    List<User> findOthersFor(int userId);
}
