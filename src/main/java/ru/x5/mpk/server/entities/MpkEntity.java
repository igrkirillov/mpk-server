package ru.x5.mpk.server.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EqualsAndHashCode
@ToString
@Getter
@Setter
public abstract class MpkEntity {
    @Id
    @Column(name = "uid")
    private String uid;
}
