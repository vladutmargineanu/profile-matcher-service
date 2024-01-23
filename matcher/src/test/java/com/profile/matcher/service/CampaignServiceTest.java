package com.profile.matcher.service;

import com.profile.matcher.dto.campaign.CampaignDto;
import com.profile.matcher.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CampaignServiceTest {
    @InjectMocks
    private CampaignService service;

    @Mock
    private CampaignRepository campaignRepository;

    @BeforeEach
    public void setUp() {
        service = new CampaignService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCurrentCampaignsMockedServiceTest() {
        List<CampaignDto> response = service.getCurrentCampaignsMockedService();
        assertNotNull(response);
    }

    @Test
    public void getCurrentCampaignsRealServiceTest() {
        List<CampaignDto> response = service.getCurrentCampaignsRealService();
        assertNotNull(response);
    }
}
