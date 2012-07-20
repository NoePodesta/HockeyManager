package model;

import enums.Privilege;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BasicUser")
public class BasicUser extends User {

    public Privilege getPrivilege() {
        return Privilege.BASICUSER;
    }
}
