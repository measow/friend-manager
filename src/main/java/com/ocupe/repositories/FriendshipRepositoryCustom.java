package com.ocupe.repositories;

import java.util.List;
import com.ocupe.models.Friendship;
import com.ocupe.models.User;

public interface FriendshipRepositoryCustom {
    List<Friendship> findFriendshipsRequestedFrom(int userId);
    List<Friendship> findFriendshipsRequestedTo(int userId);
}