package com.profile.matcher.entity.player;

import com.profile.matcher.entity.campaign.Campaign;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "PLAYER", schema = "matcherapplication")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_PLAYER", columnDefinition = "BINARY(16)")
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idPlayer;

    @NotNull
    @Column(name = "CREDENTIAL")
    private String credential;

    @Column(name = "CREATED")
    private Timestamp created;

    @Column(name = "MODIFIED")
    private Timestamp modified;

    @Column(name = "LAST_SESSION")
    private Timestamp lastSession;

    @Column(name = "TOTAL_SPENT")
    private BigDecimal totalSpent;

    @Column(name = "TOTAL_REFUND")
    private BigDecimal totalRefund;

    @Column(name = "TOTAL_TRANSACTIONS")
    private Integer totalTransactions;

    @Column(name = "LAST_PURCHASE")
    private Timestamp lastPurchase;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "CAMPAIGN_PLAYER",
            joinColumns = @JoinColumn(name = "ID_PLAYER"),
            inverseJoinColumns = @JoinColumn(name = "ID_CAMPAIGN"))
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Device> devices;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "XP")
    private Integer xp;

    @Column(name = "TOTAL_PLAYTIME")
    private BigDecimal totalPlaytime;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "BIRTHDATE")
    private Timestamp birthdate;

    @Column(name = "GENDER")
    private String gender;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "INVENTORY", referencedColumnName = "ID_INVENTORY")
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "CLAN", referencedColumnName = "ID_CLAN")
    private Clan clan;

    @Column(name = "CUSTOM_FIELD")
    private String customField;
}
