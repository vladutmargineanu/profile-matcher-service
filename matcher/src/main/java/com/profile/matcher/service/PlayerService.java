package com.profile.matcher.service;

import com.profile.matcher.arhitecture.BaseService;
import com.profile.matcher.entity.player.Player;
import com.profile.matcher.repository.CampaignRepository;
import com.profile.matcher.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends BaseService {

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    public Player getPlayerDetails(String idPlayer) {

        return null;
    }
}
