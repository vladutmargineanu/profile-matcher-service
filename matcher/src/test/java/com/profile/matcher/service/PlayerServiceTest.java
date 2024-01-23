package com.profile.matcher.service;

import com.profile.matcher.dto.campaign.CampaignDto;
import com.profile.matcher.entity.player.*;
import com.profile.matcher.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PlayerServiceTest {
    @InjectMocks
    private PlayerService service;

    @Mock
    private CampaignService campaignService;
    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        service = new PlayerService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlayerDetailsTest() {
        Player player = new Player();
        player.setClan(new Clan());
        player.setDevices(Collections.singletonList(new Device()));
        player.setInventory(new Inventory());

        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setName("mygame");

        when(campaignService.getCurrentCampaignsMockedService()).thenReturn(Collections.singletonList(campaignDto));
        when(playerRepository.findByIdPlayer(UUID.fromString("37483be2-98b7-11e7-90cf-082e5f28d836"))).thenReturn(Optional.of(player));

        Optional<Player> response = service.getPlayerDetails("37483be2-98b7-11e7-90cf-082e5f28d836");
        assertNotNull(response);
    }

    @Test
    public void getPlayerDetailsTest_Matchers() {
        Player player = new Player();
        player.setClan(new Clan());
        player.setDevices(Collections.singletonList(new Device()));
        Inventory inventory = new Inventory();
        Item item = new Item();
        item.setName("item_1");
        inventory.setItems(Collections.singletonList(item));
        player.setInventory(inventory);
        player.setLevel(3);
        player.setCountry("RO");

        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setName("mygame");
        CampaignDto.Matchers matchers = new CampaignDto.Matchers();
        CampaignDto.Matchers.Range range = new CampaignDto.Matchers.Range();
        range.setMax(3);
        range.setMin(1);
        matchers.setLevel(range);
        CampaignDto.Matchers.Has has = new CampaignDto.Matchers.Has();
        has.setCountry(Collections.singletonList("RO"));
        has.setItems(Collections.singletonList("item_1"));
        matchers.setHas(has);
        CampaignDto.Matchers.DoesNotHave doesNotHave = new CampaignDto.Matchers.DoesNotHave();
        doesNotHave.setItems(Collections.singletonList("item_4"));
        matchers.setDoesNotHave(doesNotHave);
        campaignDto.setMatchers(matchers);

        when(campaignService.getCurrentCampaignsMockedService()).thenReturn(Collections.singletonList(campaignDto));
        when(playerRepository.findByIdPlayer(UUID.fromString("37483be2-98b7-11e7-90cf-082e5f28d836"))).thenReturn(Optional.of(player));

        Optional<Player> optionalPlayer = service.getPlayerDetails("37483be2-98b7-11e7-90cf-082e5f28d836");
        assertNotNull(optionalPlayer);
        Player responsePlayer = optionalPlayer.orElse(new Player());
        assertEquals(responsePlayer.getLevel(), player.getLevel());
    }

    @Test
    public void getPlayerDetailsTest_PlayerException() {
        when(playerRepository.findByIdPlayer(UUID.fromString("37483be2-98b7-11e7-90cf-082e5f28d836"))).thenThrow(new RuntimeException("Player not found"));

        assertThrows(Exception.class, () -> service.getPlayerDetails("37483be2-98b7-11e7-90cf-082e5f28d836"));
    }

    @Test
    public void getPlayerDetailsTest_CampaignException() {
        when(playerRepository.findByIdPlayer(UUID.fromString("37483be2-98b7-11e7-90cf-082e5f28d836"))).thenReturn(Optional.of(new Player()));
        when(campaignService.getCurrentCampaignsMockedService()).thenThrow(new RuntimeException("Current campaign not found"));

        assertThrows(Exception.class, () -> service.getPlayerDetails("37483be2-98b7-11e7-90cf-082e5f28d836"));
    }
}
