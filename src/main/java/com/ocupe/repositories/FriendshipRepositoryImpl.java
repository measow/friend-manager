package com.ocupe.repositories;

import com.ocupe.models.Friendship;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional(readOnly=true)
public class FriendshipRepositoryImpl implements FriendshipRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Friendship> findFriendshipsRequestedFrom(int userId) {

        String sql = "SELECT f.* FROM friend_manager.friendships f " +
                "WHERE f.requester_user_id = ?";

        Query query = entityManager.createNativeQuery(
                sql, Friendship.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Friendship> findFriendshipsRequestedTo(int userId) {

        String sql = "SELECT f.* FROM friend_manager.friendships f " +
                "WHERE f.requestee_user_id = ?";

        Query query = entityManager.createNativeQuery(
                sql, Friendship.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }
}
