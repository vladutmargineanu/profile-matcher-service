package com.profile.matcher.controller;

import com.profile.matcher.arhitecture.BaseController;
import com.profile.matcher.dto.PlayerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController extends BaseController {

    @GetMapping("/get_client_config/{player_id}")
    public ResponseEntity<PlayerDto> getClientConfig(@PathVariable("player_id") String idPlayer) {
        return ResponseEntity.ok(null);
    }

}
