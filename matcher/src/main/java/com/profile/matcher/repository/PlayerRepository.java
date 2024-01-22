package com.profile.matcher.repository;

import com.profile.matcher.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    Optional<Player> findPlayerByIdPlayer(UUID idPlayer);
}
