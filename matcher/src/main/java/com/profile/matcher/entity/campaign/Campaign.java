package com.profile.matcher.entity.campaign;

import com.profile.matcher.entity.player.Player;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CAMPAIGN", schema = "matcherapplication")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPAIGN")
    private Long campaignId;

    @NotNull
    @Column(name = "GAME")
    private String game;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "CAMPAIGN_PLAYER",
            joinColumns = @JoinColumn(name = "ID_CAMPAIGN"),
            inverseJoinColumns = @JoinColumn(name = "ID_PLAYER"))
    private List<Player> players;

    @Column(name = "LAST_UPDATED")
    private Timestamp lastUpdated;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @PrePersist
    public void setStartDate() {
        this.setStartDate(Timestamp.from(Instant.now()));
    }

    @PreUpdate
    public void setLastUpdated() {
        this.setLastUpdated(Timestamp.from(Instant.now()));
    }
}
