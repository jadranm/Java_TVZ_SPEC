package hr.java.production.enumeration;

public enum CitiesEnum {
    ZAGREB("Zagreb", "10000"),
    SPLIT("Split", "21000"),
    RIJEKA("Rijeka", "51000"),
    OSIJEK("Osijek", "31000");

    private final String zipCode;
    private final String cityName;

    CitiesEnum(String zipCode, String cityName) {
        this.zipCode = zipCode;
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }
}
