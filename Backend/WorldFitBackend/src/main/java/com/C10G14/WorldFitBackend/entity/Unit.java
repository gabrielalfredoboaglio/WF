package com.C10G14.WorldFitBackend.entity;

import com.C10G14.WorldFitBackend.enumeration.EUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME",
            unique = true)
    private EUnit name;

    public Unit() {
    }
    public Unit(EUnit name) {
        this.name = name;
    }

    public static EUnit UnitToEUnit (String unit){
        return switch (unit.toLowerCase()){
          case "km" -> EUnit.Km;
          case "kg" -> EUnit.Kg;
          case "minutos" -> EUnit.Minutos;
            default -> EUnit.None;
        };
    }

    @Override
    public String toString() {
        return (this.name == EUnit.None) ? "" : "" + name + "";
    }
}
