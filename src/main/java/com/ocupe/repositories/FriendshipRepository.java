package com.ocupe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ocupe.models.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer>, FriendshipRepositoryCustom {
    Friendship findByRequesterAndRequestee(int requesterUserId, int requesteeUserId);
}