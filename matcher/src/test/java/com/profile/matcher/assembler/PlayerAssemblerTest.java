package com.profile.matcher.assembler;

import com.profile.matcher.dto.player.ClanDto;
import com.profile.matcher.dto.player.DeviceDto;
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

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PlayerAssemblerTest {

    @InjectMocks
    private PlayerAssembler assembler;

    @Mock
    private DeviceAssembler deviceAssembler;
    @Mock
    private InventoryAssembler inventoryAssembler;
    @Mock
    private ClanAssembler clanAssembler;

    @BeforeEach
    public void setUp() {
        assembler = new PlayerAssembler();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void toResourceTest() {
        Player player = new Player();
        player.setClan(new Clan());
        player.setDevices(Collections.singletonList(new Device()));
        player.setInventory(new Inventory());
        Campaign campaign = new Campaign();
        campaign.setName("mygame");
        player.setCampaigns(Collections.singletonList(campaign));

        when(deviceAssembler.toCollectionResource(player.getDevices())).thenReturn(Collections.singletonList(new DeviceDto()));
        when(inventoryAssembler.toResource(player.getInventory())).thenReturn(new HashMap<>(Collections.singletonMap("item_22", "34")));
        when(clanAssembler.toResource(player.getClan())).thenReturn(new ClanDto());

        assertNotNull(assembler.toResource(player));
    }
}
