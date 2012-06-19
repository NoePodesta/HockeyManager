package model;

import enums.Privilege;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Administrador")
public class Administrador extends User {
	
	@Enumerated
	private static final Privilege PRIVILEGE = Privilege.ADMINISTRADOR;

}
