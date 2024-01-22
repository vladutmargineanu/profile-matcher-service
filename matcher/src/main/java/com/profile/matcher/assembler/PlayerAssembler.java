package com.profile.matcher.assembler;

import com.profile.matcher.arhitecture.BaseAssembler;
import com.profile.matcher.dto.player.PlayerDto;
import com.profile.matcher.entity.player.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerAssembler extends BaseAssembler<Player, PlayerDto> {


    @Override
    public PlayerDto toResource(Player player) {
        return null;
    }

    @Override
    public List<PlayerDto> toCollectionResource(List<Player> t) {
        return null;
    }
}
