package com.profile.matcher.assembler;

import com.profile.matcher.arhitecture.BaseAssembler;
import com.profile.matcher.entity.player.Inventory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryAssembler extends BaseAssembler<Inventory, Map<String, String>> {

    @Override
    public Map<String, String> toResource(Inventory inventory) {
        Map<String, String> inventoryDto = new HashMap<>();

        if (null != inventory) {
            // Add cash and coins to the JSON object
            inventoryDto.put("cash", inventory.getCash().toString());
            inventoryDto.put("coins", inventory.getCoins().toString());

            // Add items to the JSON object
            if (null != inventory.getItems()) {
                inventory.getItems().forEach(item -> inventoryDto.put(item.getName(), item.getQuantity().toString()));
            }
        }

        return inventoryDto;
    }

    @Override
    public List<Map<String, String>> toCollectionResource(List<Inventory> inventories) {
        List<Map<String, String>> inventoryDtos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(inventories)) {
            for (Inventory inventory : inventories) {
                inventoryDtos.add(toResource(inventory));
            }
        }
        return inventoryDtos;
    }
}
