package com.profile.matcher.dto.player;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClanDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String startDate;

    private String endDate;
}
