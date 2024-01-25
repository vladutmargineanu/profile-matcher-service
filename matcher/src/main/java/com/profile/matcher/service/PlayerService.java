package com.profile.matcher.service;

import com.profile.matcher.arhitecture.BaseService;
import com.profile.matcher.dto.campaign.CampaignDto;
import com.profile.matcher.entity.player.Item;
import com.profile.matcher.entity.player.Player;
import com.profile.matcher.exception.FindCurrentCampaignException;
import com.profile.matcher.exception.FindPlayerDetailsException;
import com.profile.matcher.exception.UpdatePlayerDetailsException;
import com.profile.matcher.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiPredicate;

import static com.profile.matcher.utils.Constants.ErrorCode.ERROR_CURRENT_CAMPAIGN;
import static com.profile.matcher.utils.Constants.ErrorCode.ERROR_PLAYER_DETAIL;

@Service
public class PlayerService extends BaseService {

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Method to retrieve a player with id from database
     * This method also update the retrieved player with the current campaign
     * If the player satisfy some condition from current campaign
     *
     *
     * @param idPlayer
     * @return Optional of player
     */
    @Transactional(rollbackFor = {Exception.class, UpdatePlayerDetailsException.class}, propagation = Propagation.REQUIRED)
    public Optional<Player> getPlayerDetails(String idPlayer) {
        Optional<Player> optionalPlayer;

        try {
            writeLog("PlayerService.getPlayerDetails() - get player by idPlayer: {}", idPlayer);
            optionalPlayer = playerRepository.findByIdPlayer(UUID.fromString(idPlayer));
        } catch (Exception e) {
            writeLog("PlayerService.getPlayerDetails() - failed to retrieve player: {}", e.getMessage());
            throw new FindPlayerDetailsException(e.getMessage(), ERROR_PLAYER_DETAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            writeLog("PlayerService.getPlayerDetails() - player found: {}", player.getIdPlayer());

            try {
                List<CampaignDto> campaignDtoList = campaignService.getCurrentCampaignsMockedService();

                if (!CollectionUtils.isEmpty(campaignDtoList)) {
                    campaignDtoList.forEach(campaignDto -> {
                        if (matchCurrentCampaign(player, campaignDto) && checkNewCampaignForPlayer(player, campaignDto)) {

                            campaignService.getCampaignEntity(campaignDto, player).ifPresent(campaign -> {
                                player.getCampaigns().add(campaign);
                                playerRepository.save(player);
                                writeLog("PlayerService.getPlayerDetails() - player updated with a new campaign: {}",
                                        campaign.getName());
                            });
                        }
                    });
                }
                writeLog("PlayerService.getPlayerDetails() - player: {}", player.getIdPlayer());
                return Optional.of(player);
            } catch (Exception e) {
                writeLog("PlayerService.getPlayerDetails() - failed to retrieve current campaign: {}",
                        e.getMessage());
                throw new FindCurrentCampaignException(e.getMessage(), ERROR_CURRENT_CAMPAIGN, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return Optional.empty();
    }

    /**
     * Method to check if a player is already in the campaign
     *
     * @param player
     * @param campaignDto
     * @return boolean
     */
    public static boolean checkNewCampaignForPlayer(Player player, CampaignDto campaignDto) {
        if (!CollectionUtils.isEmpty(player.getCampaigns())) {
            return player.getCampaigns()
                    .stream()
                    .noneMatch(campaign -> campaign.getName().equals(campaignDto.getName()));
        }
        return true;
    }

    /**
     * Method to check if a player can be in a campaign
     *
     * @param player
     * @param campaign
     * @return
     */
    public static boolean matchCurrentCampaign(Player player, CampaignDto campaign) {
        List<BiPredicate<Player, CampaignDto>> matchers = List.of(
                matchLevel,
                matchCountry,
                matchItems,
                matchDoesNotHaveItems
                // Add new matchers here
        );

        return matchers.stream().allMatch(matcher -> matcher.test(player, campaign));
    }

    /**
     * Implementation of the method test() from functional interface BiPredicate
     * This predicate check if the user match with his level in the campaign
     */
    private static final BiPredicate<Player, CampaignDto> matchLevel = (player, campaign) -> {
        if (null != player.getLevel()) {
            int playerLevel = player.getLevel();
            int minLevel = campaign.getMatchers() != null && campaign.getMatchers().getLevel() != null ?
                    campaign.getMatchers().getLevel().getMin() : Integer.MAX_VALUE;
            int maxLevel = campaign.getMatchers() != null && campaign.getMatchers().getLevel() != null ?
                    campaign.getMatchers().getLevel().getMax() : Integer.MIN_VALUE;

            return playerLevel >= minLevel && playerLevel <= maxLevel;
        }
        return false;
    };

    /**
     * Implementation of the method test() from functional interface BiPredicate
     * This predicate check if the user match with his country in the campaign
     */
    private static final BiPredicate<Player, CampaignDto> matchCountry = (player, campaign) -> {
        if (null != player.getCountry()) {
            String playerCountry = player.getCountry();
            List<String> campaignCountries = null != campaign.getMatchers() && null != campaign.getMatchers().getHas() ?
                    campaign.getMatchers().getHas().getCountry() : Collections.emptyList();

            return campaignCountries.contains(playerCountry);
        }
        return false;
    };

    /**
     * Implementation of the method test() from functional interface BiPredicate
     * This predicate check if the user has the respective items from campaign
     */
    private static final BiPredicate<Player, CampaignDto> matchItems = (player, campaign) -> {
        if (null != player.getInventory() && !CollectionUtils.isEmpty(player.getInventory().getItems())) {
            List<Item> playerItems = player.getInventory().getItems();
            List<String> campaignItems = null != campaign.getMatchers() && null != campaign.getMatchers().getHas() ?
                    campaign.getMatchers().getHas().getItems() : Collections.emptyList();

            return playerItems.stream().map(Item::getName).anyMatch(campaignItems::contains);
        }
        return false;
    };

    /**
     * Implementation of the method test() from functional interface BiPredicate
     * This predicate check if the user has not the respective items from campaign
     */
    private static final BiPredicate<Player, CampaignDto> matchDoesNotHaveItems = (player, campaign) -> {
        if (null != player.getInventory() && !CollectionUtils.isEmpty(player.getInventory().getItems())) {
            List<Item> playerItems = player.getInventory().getItems();
            List<String> campaignDoesNotHaveItems = null != campaign.getMatchers() && null != campaign.getMatchers().getDoesNotHave() ?
                    campaign.getMatchers().getDoesNotHave().getItems() : Collections.emptyList();

            return playerItems.stream().map(Item::getName).noneMatch(campaignDoesNotHaveItems::contains);
        }
        return false;
    };
}
