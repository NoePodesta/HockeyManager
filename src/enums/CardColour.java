package enums;


public enum CardColour {

    RED("Red"),
    YELLOW("Yellow"),
    GREEN("Green");

    private String value;

    private CardColour(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isRed(){
        return equals(RED);
    }

    public boolean isYellow(){
        return equals(YELLOW);
    }

    public boolean isGreen(){
        return equals(GREEN);
    }

}