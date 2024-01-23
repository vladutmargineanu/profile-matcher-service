package com.profile.matcher.controller;

import com.profile.matcher.assembler.PlayerAssembler;
import com.profile.matcher.command.PlayerCommand;
import com.profile.matcher.dto.campaign.CampaignDto;
import com.profile.matcher.dto.player.ClanDto;
import com.profile.matcher.dto.player.DeviceDto;
import com.profile.matcher.dto.player.PlayerDto;
import com.profile.matcher.entity.campaign.Campaign;
import com.profile.matcher.entity.player.Clan;
import com.profile.matcher.entity.player.Device;
import com.profile.matcher.entity.player.Inventory;
import com.profile.matcher.entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PlayerControllerTest {
    @InjectMocks
    private PlayerController controller;

    @Mock
    private PlayerAssembler playerAssembler;

    @Mock
    private PlayerCommand playerCommand;

    @Mock
    protected BeanFactory beanFactory;

    @BeforeEach
    public void setUp() {
        controller = new PlayerController();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getClientConfigTest() {
        Player player = new Player();
        player.setClan(new Clan());
        player.setDevices(Collections.singletonList(new Device()));
        player.setInventory(new Inventory());
        Campaign campaign = new Campaign();
        campaign.setName("mygame");
        player.setCampaigns(Collections.singletonList(campaign));

        PlayerDto playerDto = new PlayerDto();
        playerDto.setClan(new ClanDto());
        playerDto.setDevices(Collections.singletonList(new DeviceDto()));
        playerDto.setInventory(Collections.singletonMap("item_22", "45"));
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setName("mygame");
        playerDto.setActive_campaigns(Collections.singletonList(campaignDto.getName()));

        when(beanFactory.getBean(PlayerCommand.class, "test")).thenReturn(playerCommand);
        when(playerCommand.execute()).thenReturn(Optional.of(player));
        when(playerAssembler.toResource(player)).thenReturn(playerDto);

        ResponseEntity<PlayerDto> playerDtoResponseEntity = controller.getClientConfig("test");
        assertNotNull(playerDtoResponseEntity);
        assertEquals(Objects.requireNonNull(playerDtoResponseEntity.getBody()).getActive_campaigns(), playerDto.getActive_campaigns());
    }

    @Test
    public void getClientConfigTest_EmptyResponse() {
        when(beanFactory.getBean(PlayerCommand.class, "test")).thenReturn(playerCommand);
        when(playerCommand.execute()).thenReturn(Optional.empty());

        ResponseEntity<PlayerDto> playerDtoResponseEntity = controller.getClientConfig("test");
        assertNotNull(playerDtoResponseEntity);
    }
}
