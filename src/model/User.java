package model;

import enums.Privilege;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="userType",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("User")
public abstract class User {
	
	private String userName;
	private String password;
	private String name;
	private String lastName;
	private String email;
	private byte[] photo;
	
	@Id
	@Column(name ="User_Name", nullable=false, length=20, unique=true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name ="Password", nullable=false, length=10)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "Name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "Last_Name", nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "Email", nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Lob
	@Column(name="User_Photo")
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	@Column(name="Privilege", insertable=false)
	public abstract Privilege getPrivilege();
	public void setPrivilege(Privilege privilege){
		
	};


}

