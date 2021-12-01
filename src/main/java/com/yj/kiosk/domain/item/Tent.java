package com.yj.kiosk.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TENT")
@Getter @Setter
public class Tent extends Item{
    private String name;
    private String type;
}
