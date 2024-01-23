package com.profile.matcher.service;

import com.profile.matcher.arhitecture.BaseService;
import com.profile.matcher.dto.campaign.CampaignDto;
import com.profile.matcher.entity.campaign.Campaign;
import com.profile.matcher.entity.player.Player;
import com.profile.matcher.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService extends BaseService {
    @Value("${campaign.api.url}")
    private String campaignApiUrl;

    @Autowired
    private CampaignRepository campaignRepository;

    /**
     * @return
     */
    public List<CampaignDto> getCurrentCampaignsRealService() {
        writeLog("CampaignService.getCurrentCampaignsRealService() IN");

        final RestTemplate restTemplate = new RestTemplate();
        List<CampaignDto> campaignDtoList = new ArrayList<>();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<CampaignDto>> response = restTemplate.exchange(
                    campaignApiUrl,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {
                    }
            );
            campaignDtoList = response.getBody();
            writeLog("CampaignService.getCurrentCampaignsRealService() get current campaigns: {}", campaignDtoList);
            return campaignDtoList;
        } catch (Exception e) {
            writeLog("Error getting current campaigns - exception: {}", e.getMessage());
        }
        return campaignDtoList;
    }

    /**
     * @return
     */
    public List<CampaignDto> getCurrentCampaignsMockedService() {
        writeLog("CampaignService.getCurrentCampaignsMockedService() IN");

        CampaignDto campaign = new CampaignDto();
        campaign.setGame("mygame");
        campaign.setName("mycampaign");
        campaign.setPriority(BigDecimal.valueOf(10.5));

        CampaignDto.Matchers.Range levelRange = new CampaignDto.Matchers.Range();
        levelRange.setMin(1);
        levelRange.setMax(3);

        CampaignDto.Matchers.Has has = new CampaignDto.Matchers.Has();
        has.setCountry(List.of("US", "RO", "CA"));
        has.setItems(List.of("item_1"));

        CampaignDto.Matchers.DoesNotHave doesNotHave = new CampaignDto.Matchers.DoesNotHave();
        doesNotHave.setItems(List.of("item_4"));

        CampaignDto.Matchers matchers = new CampaignDto.Matchers();
        matchers.setLevel(levelRange);
        matchers.setHas(has);
        matchers.setDoesNotHave(doesNotHave);

        campaign.setMatchers(matchers);

        campaign.setStart_date("2022-01-25 00:00:00Z");
        campaign.setEnd_date("2022-02-25 00:00:00Z");
        campaign.setEnabled(true);
        campaign.setLast_updated("2021-07-13 11:46:58Z");
        List<CampaignDto> campaignDtoList = Collections.singletonList(campaign);

        writeLog("CampaignService.getCurrentCampaignsMockedService() current campaigns: {}", campaignDtoList);

        return campaignDtoList;
    }

    /**
     * @param campaignDto
     * @param player
     * @return
     */
    public Optional<Campaign> createCampaignEntity(CampaignDto campaignDto, Player player) {
        if (null != campaignDto) {
            writeLog("CampaignService.createCampaignEntity() entity from dto: {}", campaignDto);

            Campaign campaign = new Campaign();
            campaign.setGame(campaignDto.getGame());
            campaign.setName(campaignDto.getName());
            campaign.setPriority(campaignDto.getPriority());
            campaign.setEnabled(campaignDto.getEnabled());
            List<Player> players = new ArrayList<>();
            players.add(player);
            campaign.setPlayers(players);

            writeLog("CampaignService.createCampaignEntity() campaign entity created: {}", campaign.getName());

            return Optional.of(campaign);
        }

        return Optional.empty();
    }
}
