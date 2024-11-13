package net.alephdev.models;

public class Address {
    private String street; 
    private String zipCode; 
    private Location town; 

    public Address(String street, String zipCode, Location town) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street == null || street.isBlank() || street.length() > 63)
            throw new IllegalArgumentException("Длина названия не должна быть пустой или превышать 63 символов");
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if(zipCode == null || zipCode.isBlank())
            throw new IllegalArgumentException("Индекс не может быть пустым");
        this.zipCode = zipCode;
    }

    public Location getTown() {
        return town;
    }

    public void setTown(Location town) {
        if(town == null)
            throw new IllegalArgumentException("Необходимо указать город");
        this.town = town;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + ", zipCode=" + zipCode + ", town=" + town + '}';
    }
    
    
}
