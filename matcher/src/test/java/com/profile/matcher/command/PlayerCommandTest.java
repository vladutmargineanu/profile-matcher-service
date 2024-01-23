package com.profile.matcher.command;

import com.profile.matcher.entity.player.Player;
import com.profile.matcher.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PlayerCommandTest {
    @InjectMocks
    private PlayerCommand command;

    @Mock
    private PlayerService playerService;

    private final String idPlayer = "test";

    @BeforeEach
    public void setUp() {
        command = new PlayerCommand(idPlayer);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doExecuteTest() {
        when(playerService.getPlayerDetails(idPlayer)).thenReturn(Optional.of(new Player()));
        assertNotNull(command.doExecute());
    }
}
