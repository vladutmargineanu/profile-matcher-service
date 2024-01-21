package com.profile.matcher.entity.player;

import jakarta.persistence.*;
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
@Table(name = "INVENTORY", schema = "matcherapplication")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVENTORY")
    private Long idInventory;

    @OneToOne(mappedBy = "inventory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Player player;

    @Column(name = "CASH")
    private Integer cash;

    @Column(name = "COINS")
    private Integer coins;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ITEM", referencedColumnName = "ID_ITEM")
    private List<Item> item;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @PrePersist
    public void setStartDate() {
        this.setStartDate(Timestamp.from(Instant.now()));
    }
}

