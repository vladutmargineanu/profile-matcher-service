package com.profile.matcher.repository;

import com.profile.matcher.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    // @Query(nativeQuery = true, value = "SELECT * FROM PLAYER PL WHERE PL.ID_PLAYER = UUID_TO_BIN(?1)")
    Optional<Player> findByIdPlayer(UUID idPlayer);
}
