package com.profile.matcher.assembler;

import com.profile.matcher.arhitecture.BaseAssembler;
import com.profile.matcher.dto.player.ClanDto;
import com.profile.matcher.entity.player.Clan;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClanAssembler extends BaseAssembler<Clan, ClanDto> {

    @Override
    public ClanDto toResource(Clan clan) {
        ClanDto clanDto = new ClanDto();

        if (null != clan) {
            clanDto.setId(clan.getIdClan());
            clanDto.setName(clan.getName());
        }
        return clanDto;
    }

    @Override
    public List<ClanDto> toCollectionResource(List<Clan> clans) {
        List<ClanDto> clanDtos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(clans)) {
            for (Clan clan : clans) {
                clanDtos.add(toResource(clan));
            }
        }
        return clanDtos;
    }
}
