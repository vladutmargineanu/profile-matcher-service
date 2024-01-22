package com.profile.matcher.dto.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDto {
    private Long id;

    private String model;

    private String carrier;

    private String firmware;

    private String startDate;

    private String endDate;
}
