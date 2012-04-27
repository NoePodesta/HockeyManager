package model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import enums.Privilege;

@Entity
@Table(name = "Administrador")
public class Administrador extends User {
	
	@Enumerated
	private static final Privilege PRIVILEGE = Privilege.ADMINISTRADOR;

}
