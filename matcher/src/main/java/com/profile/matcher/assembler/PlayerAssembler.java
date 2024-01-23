package com.profile.matcher.assembler;

import com.profile.matcher.arhitecture.BaseAssembler;
import com.profile.matcher.dto.player.PlayerDto;
import com.profile.matcher.entity.campaign.Campaign;
import com.profile.matcher.entity.player.Player;
import com.profile.matcher.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerAssembler extends BaseAssembler<Player, PlayerDto> {

    @Autowired
    private DeviceAssembler deviceAssembler;
    @Autowired
    private InventoryAssembler inventoryAssembler;
    @Autowired
    private ClanAssembler clanAssembler;

    @Override
    public PlayerDto toResource(Player player) {
        PlayerDto playerDto = new PlayerDto();
        if (null != player) {
            playerDto.setPlayer_id(player.getIdPlayer());
            playerDto.setCredential(player.getCredential());
            playerDto.setCreated(DateHelper.toFormattedDateTimeString(player.getCreated()));
            playerDto.setModified(DateHelper.toFormattedDateTimeString(player.getModified()));
            playerDto.setLast_session(DateHelper.toFormattedDateTimeString(player.getLastSession()));
            playerDto.setTotal_spent(player.getTotalSpent());
            playerDto.setTotal_refund(player.getTotalRefund());
            playerDto.setTotal_transactions(player.getTotalTransactions());
            playerDto.setLast_purchase(DateHelper.toFormattedDateTimeString(player.getLastPurchase()));
            // Set the names for the player's campaigns
            if (!CollectionUtils.isEmpty(player.getCampaigns())) {
                playerDto.setActive_campaigns(
                        player.getCampaigns()
                                .stream()
                                .map(Campaign::getName)
                                .collect(Collectors.toList())
                );
            }
            playerDto.setDevices(deviceAssembler.toCollectionResource(player.getDevices()));
            playerDto.setLevel(player.getLevel());
            playerDto.setXp(player.getXp());
            playerDto.setTotal_playtime(player.getTotalPlaytime());
            playerDto.setCountry(player.getCountry());
            playerDto.setLanguage(player.getLanguage());
            playerDto.setBirthdate(DateHelper.toFormattedDateTimeString(player.getBirthdate()));
            playerDto.setGender(player.getGender());
            playerDto.setInventory(inventoryAssembler.toResource(player.getInventory()));
            playerDto.setClan(clanAssembler.toResource(player.getClan()));
            playerDto.set_customField(player.getCustomField());
        }
        return playerDto;
    }

    @Override
    public List<PlayerDto> toCollectionResource(List<Player> players) {
        List<PlayerDto> playerDtos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(players)) {
            for (Player player : players) {
                playerDtos.add(toResource(player));
            }
        }
        return playerDtos;
    }
}
