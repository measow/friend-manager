package com.ocupe.repositories;

import java.util.List;
import com.ocupe.models.Friendship;

public interface FriendshipRepositoryCustom {
    List<Friendship> findFriendshipsFor(int userId);
}