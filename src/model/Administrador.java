package model;

import enums.Privilege;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Administrador")
public class Administrador extends User {

    public Privilege getPrivilege() {
        return Privilege.ADMINISTRADOR;
    }
}
