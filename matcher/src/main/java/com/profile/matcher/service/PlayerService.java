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
     * @param idPlayer
     * @return
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

                            campaignService.createCampaignEntity(campaignDto, player).ifPresent(campaign -> {
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
     * @param player
     * @param campaignDto
     * @return
     */
    public static boolean checkNewCampaignForPlayer(Player player, CampaignDto campaignDto) {
        return player.getCampaigns()
                .stream()
                .noneMatch(campaign -> campaign.getName().equals(campaignDto.getName()));
    }

    /**
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
     *
     */
    private static final BiPredicate<Player, CampaignDto> matchLevel = (player, campaign) -> {
        int playerLevel = player.getLevel();
        int minLevel = campaign.getMatchers().getLevel().getMin();
        int maxLevel = campaign.getMatchers().getLevel().getMax();

        return playerLevel >= minLevel && playerLevel <= maxLevel;
    };

    /**
     *
     */
    private static final BiPredicate<Player, CampaignDto> matchCountry = (player, campaign) -> {
        String playerCountry = player.getCountry();
        List<String> campaignCountries = campaign.getMatchers().getHas().getCountry();

        return campaignCountries.contains(playerCountry);
    };

    /**
     *
     */
    private static final BiPredicate<Player, CampaignDto> matchItems = (player, campaign) -> {
        List<Item> playerItems = player.getInventory().getItems();
        List<String> campaignItems = campaign.getMatchers().getHas().getItems();

        return playerItems.stream().map(Item::getName).anyMatch(campaignItems::contains);
    };

    /**
     *
     */
    private static final BiPredicate<Player, CampaignDto> matchDoesNotHaveItems = (player, campaign) -> {
        List<Item> playerItems = player.getInventory().getItems();
        List<String> campaignDoesNotHaveItems = campaign.getMatchers().getDoesNotHave().getItems();

        return playerItems.stream().map(Item::getName).noneMatch(campaignDoesNotHaveItems::contains);
    };
}
