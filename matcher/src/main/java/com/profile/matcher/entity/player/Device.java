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
@NoArgsConstructor
@Entity
@Table(name = "DEVICE", schema = "matcherapplication")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEVICE")
    private Long idDevice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "PLAYER", referencedColumnName = "ID_PLAYER")
    private Player player;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "CARRIER")
    private String carrier;

    @Column(name = "FIRMWARE")
    private String firmware;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @PrePersist
    public void setStartDate() {
        this.setStartDate(Timestamp.from(Instant.now()));
    }
}