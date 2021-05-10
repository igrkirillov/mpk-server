package ru.x5.mpk.server.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "mpkaddress_abonent_link")
public class MpkAddressAbonentLink extends MpkEntity {

    @ManyToOne
    @JoinColumn(name = "mpkaddress_uid")
    private MpkAddress mpkAddress;

    @ManyToOne
    @JoinColumn(name = "abonent_uid")
    private Abonent abonent;
}
