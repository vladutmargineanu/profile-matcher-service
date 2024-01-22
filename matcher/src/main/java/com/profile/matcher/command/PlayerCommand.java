package com.profile.matcher.command;

import com.profile.matcher.arhitecture.BaseCommand;
import com.profile.matcher.entity.player.Player;
import com.profile.matcher.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class PlayerCommand extends BaseCommand<Optional<Player>> {
    @Autowired
    private PlayerService playerService;

    private final String idPlayer;

    @Override
    public Optional<Player> doExecute() {
        writeLog("PlayerCommand.doExecute() - command IN with idPlayer: {}", idPlayer);
        return this.playerService.getPlayerDetails(idPlayer);
    }
}
