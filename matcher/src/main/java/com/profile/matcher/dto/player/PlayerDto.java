package com.profile.matcher.dto.player;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlayerDto {
    private UUID player_id;

    @NotNull
    @Size(max = 255)
    private String credential;

    private String created;

    private String modified;

    private String last_session;

    private BigDecimal total_spent;

    private BigDecimal total_refund;

    private Integer total_transactions;

    private String last_purchase;

    private List<String> active_campaigns;

    private List<DeviceDto> devices;

    private Integer level;

    private Integer xp;

    private BigDecimal total_playtime;

    private String country;

    private String language;

    private String birthdate;

    private String gender;

    private JSONObject inventory;

    private ClanDto clan;

    private String _customField;
}
