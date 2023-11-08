package com.C10G14.WorldFitBackend.entity;

import com.C10G14.WorldFitBackend.enumeration.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME"
            ,unique = true)
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public static ERole RoletoERole (String role) {
        role = role.toLowerCase();
        return switch (role){
            case "user" -> ERole.ROLE_USER;
            case "coach" -> ERole.ROLE_COACH;
            case "customer" -> ERole.ROLE_CUSTOMER;
            case "admin" -> ERole.ROLE_ADMIN;
            default -> null;
        };

    }
}
