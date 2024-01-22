package com.profile.matcher.dto.campaign;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CampaignDto {

    @NotBlank(message = "Game is mandatory")
    private String game;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private BigDecimal priority;

    private Matchers matchers;

    private Boolean enabled;

    private String last_updated;

    private String start_date;

    private String end_date;

    @Data
    public static class Matchers {
        private Range level;
        private Has has;
        private DoesNotHave doesNotHave;

        @Data
        public static class Range {
            private Integer min;
            private Integer max;
        }

        @Data
        public static class Has {
            private List<String> country;
            private List<String> items;
        }

        @Data
        public static class DoesNotHave {
            private List<String> items;
        }
    }
}
