package com.profile.matcher.dto.player;

import com.profile.matcher.entity.player.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InventoryDto {
    private Long idInventory;

    private BigDecimal cash;

    private BigDecimal coins;

    // TODO - format like JSON format string
    private List<Item> item;

    private String startDate;

    private String endDate;
}
