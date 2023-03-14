package net.alephdev.commands;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import net.alephdev.Main;
import net.alephdev.StorageManager;
import net.alephdev.models.Address;
import net.alephdev.models.Coordinates;
import net.alephdev.models.Location;
import net.alephdev.models.Organization;
import net.alephdev.models.OrganizationType;
/**
 * Command for updating element of the collection by id, based on AddCommand
 * @author MixaDev
 */
public class UpdateCommand implements Command {
    @Override
    public void execute(String[] args) {
        if(args.length < 1) {
            System.out.println("Необходимо указать id, использование: update [id]");
            return;
        }
        Organization existed;
        try {
            existed = Main.getStorageManager().get(Integer.parseInt(args[0]));
            if(existed == null)
                throw new IllegalArgumentException();
        } catch(Exception ex) {
            System.out.println("Объект с указанным id не найден!");
            return;
        }
        if(args.length > 1) {
            try {
                CSVReader readerCsv = new CSVReader(new StringReader(String.join(" ", Arrays.copyOfRange(args, 1, args.length))));
                Main.getStorageManager().update(Organization.parseCSVLine(readerCsv.readNext(), false));
                System.out.println("Объект обновлен.");
                try {
                    Main.getStorageManager().save(StorageManager.autosaveName);
                } catch (IOException ex) {}
            } catch(Exception ex) {
                System.out.println("Введены неверные данные об объекте!");
            }
            return;
        }
        setValue("название", () -> existed.setName(new Scanner(System.in).nextLine()));
        existed.setCoordinates(createCoordinates());
        setValue("ежегодный оборот", () -> existed.setAnnualTurnover(new Scanner(System.in)));
        setValue("полное название", () -> existed.setFullName(new Scanner(System.in).nextLine()));
        setValue("тип " + Arrays.toString(OrganizationType.values()), () -> existed.setTypeFromString(new Scanner(System.in).nextLine()));
        existed.setOfficialAddress(createAddress());
        Main.getStorageManager().update(existed);
        System.out.println("Объект обновлен.");
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
        return "обновить значение элемента коллекции";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[]{"id"};
    }
}