package net.alephdev.commands;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.alephdev.Main;
import net.alephdev.StorageManager;
import net.alephdev.models.Address;
import net.alephdev.models.Coordinates;
import net.alephdev.models.Location;
import net.alephdev.models.Organization;
import net.alephdev.models.OrganizationType;
/**
 * Command for adding a new object to the collection
 * @author MixaDev
 */
public class AddCommand implements Command {
    @Override
    public void execute(String[] args) {
        if(args.length > 0) {
            try {
                CSVReader readerCsv = new CSVReader(new StringReader(String.join(" ", args)));
                Main.getStorageManager().add(Organization.parseCSVLine(readerCsv.readNext(), true));
                System.out.println("Объект добавлен.");
                try {
                    Main.getStorageManager().save(StorageManager.autosaveName);
                } catch (IOException ex) {}
            } catch(Exception ex) {
                System.out.println("Введены неверные данные об объекте!");
            }
            return;
        }
        Organization created = new Organization();
        setValue("название", () -> created.setName(new Scanner(System.in).nextLine()));
        created.setCoordinates(createCoordinates());
        setValue("ежегодный оборот", () -> created.setAnnualTurnover(new Scanner(System.in)));
        setValue("полное название", () -> created.setFullName(new Scanner(System.in).nextLine()));
        setValue("тип " + Arrays.toString(OrganizationType.values()), () -> created.setTypeFromString(new Scanner(System.in).nextLine()));
        created.setOfficialAddress(createAddress());
        Main.getStorageManager().add(created);
        System.out.println("Объект добавлен.");
        try {
            Main.getStorageManager().save(StorageManager.autosaveName);
        } catch (IOException ex) {}
    }
    private void setValue(String label, Runnable setter) {
        while (true) {
            System.out.print("Введите "+label+": ");
            try {
                setter.run();
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    private Coordinates createCoordinates() {
        Coordinates created = new Coordinates(0, 0);
        setValue("координаты, X", () -> created.setX(new Scanner(System.in)));
        setValue("координаты, Y", () -> created.setY(new Scanner(System.in)));
        return created;
    }

    private Address createAddress() {
        Address created = new Address(null, null, null);
        setValue("город, улица", () -> created.setStreet(new Scanner(System.in).nextLine()));
        setValue("город, индекс", () -> created.setZipCode(new Scanner(System.in).nextLine()));
        created.setTown(createLocation());
        return created;
    }

    private Location createLocation() {
        Location created = new Location(0, 0, null);
        setValue("город, местоположение, X", () -> created.setX(new Scanner(System.in)));
        setValue("город, местоположение, Y", () -> created.setY(new Scanner(System.in)));
        setValue("город, местоположение, название", () -> created.setName(new Scanner(System.in).nextLine()));
        return created;
    }
    @Override
    public String getDesctiption() {
        return "добавить новый элемент в коллекцию";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
