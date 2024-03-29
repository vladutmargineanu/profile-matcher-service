package com.profile.matcher.controller;

import com.profile.matcher.arhitecture.BaseController;
import com.profile.matcher.assembler.PlayerAssembler;
import com.profile.matcher.command.PlayerCommand;
import com.profile.matcher.dto.player.PlayerDto;
import com.profile.matcher.entity.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PlayerController extends BaseController {

    @Autowired
    private PlayerAssembler playerAssembler;

    @GetMapping("/get_client_config/{player_id}")
    public ResponseEntity<PlayerDto> getClientConfig(@PathVariable("player_id") String idPlayer) {
        writeLog("PlayerController.getClientConfig() - get player with id: {}", idPlayer);

        PlayerCommand playerCommand = getCommand(PlayerCommand.class, idPlayer);
        Optional<Player> playerOptional = playerCommand.execute();

        if (playerOptional.isPresent()) {
            PlayerDto playerDto = playerAssembler.toResource(playerOptional.get());
            writeLog("PlayerController.getClientConfig() - player found: {}", playerDto);
            return ResponseEntity.ok(playerDto);
        } else {
            writeLog("PlayerController.getClientConfig() - player not found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(HttpHeaders.EMPTY).build();
        }
    }

}
