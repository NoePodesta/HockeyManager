package enums;

public enum Privilege {
	
	ADMINISTRADOR("0"),
	USERADMIN("1"),
	BASICUSER("2");
	
	private String value;

	private Privilege(String value){
        this.value = value;
    }

	public String getValue() {
		return value;
	}

    public boolean isAdministrador(){
        return equals(ADMINISTRADOR);
    }

    public boolean isBasicUser(){
        return equals(BASICUSER);
    }

    public boolean isUserAdmin(){
        return equals(USERADMIN);
    }

}
