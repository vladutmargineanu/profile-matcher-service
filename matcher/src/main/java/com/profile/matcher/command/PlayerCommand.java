package com.profile.matcher.command;

import com.profile.matcher.arhitecture.BaseCommand;
import com.profile.matcher.dto.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class PlayerCommand extends BaseCommand<PlayerDto> {

    @Override
    protected PlayerDto doExecute() {
        return null;
    }
}
