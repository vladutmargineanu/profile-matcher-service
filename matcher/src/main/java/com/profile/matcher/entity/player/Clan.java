package com.profile.matcher.entity.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CLAN", schema = "matcherapplication")
public class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLAN")
    private Long idClan;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "PLAYER", referencedColumnName = "ID_PLAYER")
    private Player player;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @PrePersist
    public void setStartDate() {
        this.setStartDate(Timestamp.from(Instant.now()));
    }
}