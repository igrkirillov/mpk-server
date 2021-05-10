package ru.x5.mpk.server.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "abonent")
public class Abonent extends MpkEntity {

    @Column(name = "full_name")
    private String fullName;
}
