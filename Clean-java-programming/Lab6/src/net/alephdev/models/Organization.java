package net.alephdev.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import net.alephdev.Main;
import net.alephdev.Utils;

public class Organization {
    private Integer id;
    private String name; 
    private Coordinates coordinates;
    private LocalDate creationDate;
    private Float annualTurnover; 
    private String fullName;
    private OrganizationType type; 
    private Address officialAddress;

    public Organization() {
        id = Main.getStorageManager().getMaxId()+1;
        creationDate = LocalDate.now();
    }
    private Organization(Integer id, String name, Coordinates coordinates, LocalDate creationDate, Float annualTurnover, String fullName, OrganizationType type, Address officialAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = officialAddress;
    }
    
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank())
            throw new IllegalArgumentException("Значение названия не может быть пустым");
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates == null)
            throw new IllegalArgumentException("Значение координат не может быть пустым");
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Scanner scan) {
        float annualTurnover;
        try {
             annualTurnover = scan.nextFloat();
        } catch(Exception ex) {
            scan.nextLine();
            throw new IllegalArgumentException("Значение ежегодного оборота должно быть числом");
        }
        if(annualTurnover == 0.0f)
            throw new IllegalArgumentException("Значение ежегодного оборота не может быть пустым");
        scan.nextLine();
        this.annualTurnover = annualTurnover;
    }

    public void setFullName(String fullName) {
        if(fullName == null || fullName.isBlank() || fullName.length() > 1737)
            throw new IllegalArgumentException("Значение полного названия не может быть пустым");
        for(Organization organization : Main.getStorageManager().getAll()) {
            if(!Objects.equals(organization.getId(), this.id) && organization.getFullName().equals(fullName))
                throw new IllegalArgumentException("Значение полного названия должно быть уникальным");
        }
        this.fullName = fullName;
    }
    
    public String getFullName() {
        return fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setTypeFromString(String type) {
        if(type == null || type.isBlank())
            throw new IllegalArgumentException("Необходимо установить тип");
        try {
            this.type = OrganizationType.valueOf(type);
        } catch(IllegalArgumentException ex) {
            throw new IllegalArgumentException("Данного типа организации не существует");
        }
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        if(officialAddress == null)
            throw new IllegalArgumentException("Адрес не может быть пустым");
        this.officialAddress = officialAddress;
    }
    /**
     * Parsing organization object based on CSV line
     * @param fields CSV line
     * @param setStartingData Define is object actually new
     * @return Saved organization object
     */
    public static Organization parseCSVLine(String[] fields, boolean setStartingData) {
        int id = setStartingData ? Main.getStorageManager().getMaxId()+1 : Integer.parseInt(fields[0]);
        String name = Utils.unescapeString(fields[1]);
        float x = Float.parseFloat(fields[2]);
        int y = Integer.parseInt(fields[3]);
        LocalDate creationDate = setStartingData ? LocalDate.now() : LocalDate.parse(fields[4]);
        float annualTurnover = Float.parseFloat(fields[5]);
        String fullName = Utils.unescapeString(fields[6]);
        OrganizationType type = OrganizationType.valueOf(fields[7]);
        String street = Utils.unescapeString(fields[8]);
        String zipCode = Utils.unescapeString(fields[9]);
        float townX = Float.parseFloat(fields[10]);
        double townY = Double.parseDouble(fields[11]);
        String townName = Utils.unescapeString(fields[12]);
        Coordinates coordinates = new Coordinates(x, y);
        Location town = new Location(townX, townY, townName);
        Address officialAddress = new Address(street, zipCode, town);
        return new Organization(id, name, coordinates, creationDate, annualTurnover, fullName, type, officialAddress);
    }

    @Override
    public String toString() {
        return "Organization{" + "id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate=" + creationDate + ", annualTurnover=" + annualTurnover + ", fullName=" + fullName + ", type=" + type + ", officialAddress=" + officialAddress + '}';
    }
}
