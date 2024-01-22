package com.profile.matcher.service;

import com.profile.matcher.arhitecture.BaseService;
import com.profile.matcher.dto.campaign.CampaignDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CampaignService extends BaseService {
    @Value("${campaign.api.url}")
    private String campaignApiUrl;

    private final RestTemplate restTemplate;


    public CampaignService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * @return
     */
    public List<CampaignDto> getCurrentCampaignsRealService() {
        writeLog("CampaignService.getCurrentCampaignsRealService IN");

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
            writeLog("CampaignService.getCurrentCampaignsRealService get current campaign: {}", campaignDtoList);
            return campaignDtoList;
        } catch (Exception e) {
            writeLog("Error getting current campaign - exception: {}", e.getMessage());
        }
        return campaignDtoList;
    }

    /**
     * @return
     */
    public List<CampaignDto> getCurrentCampaignMockedService() {
        writeLog("CampaignService.getCurrentCampaignMockedService IN");

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

        writeLog("CampaignService.getCurrentCampaignMockedService current campaign: {}", campaignDtoList);

        return campaignDtoList;
    }
}
