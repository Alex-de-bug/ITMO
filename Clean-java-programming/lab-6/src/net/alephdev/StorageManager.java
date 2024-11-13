package net.alephdev;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;
import net.alephdev.models.Organization;

public final class StorageManager {
    private Vector<Organization> data = new Vector<>();
    private String filename;
    private LocalDate initializationDate;
    public static String autosaveName = System.getProperty("java.io.tmpdir")+"autosave.csv";
    public StorageManager(String filename) {
        this.filename = filename;
        this.initializationDate = LocalDate.now();
        try {
            if(new File(autosaveName).exists()) {
                System.out.print("У вас есть несохраненные данные, напишите '+', что бы их загрузить: ");
                if((new Scanner(System.in)).nextLine().equals("+")) {
                    parse(autosaveName);
                    System.out.println("Данные были загружены из автоматического сохранения.");
                } else {
                    new File(autosaveName).delete();
                    parse(filename);
                }
            } else
                parse(filename);
        } catch(Exception exception) {
            System.out.println("Не удалось прочитать поврежденные данные из файла. Коллекция содержит "+data.size()+" элементов.");
        }
    }
    /**
     * Get active file name
     * @return File name
     */
    public String getFilename() {
        return filename;
    }
    /**
     * Add object to collection
     * @param organization New organisation
     */
    public void add(Organization organization) {
        data.add(organization);
    }
    /**
     * Update collection object
     * @param organization Existing organization
     */
    public void update(Organization organization) {
        for(int i = 0; i < data.size(); i++) {
            if(Objects.equals(data.get(i).getId(), organization.getId())) {
                data.set(i, organization);
                return;
            }
        }
        throw new NullPointerException("Редактируемый объект не найден");
    }
    /**
     * Remove object by id
     * @param id ID
     */
    public void remove(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getId() == id) {
                data.remove(i);
                return;
            }
        }
        throw new NullPointerException("Объект с id "+id+" не найден");
    }
    /**
     * Remove objects with greater id
     * @param id ID
     */
    public void removeGreater(int id) {
        List<Organization> toRemove = new ArrayList<>();
        for(int i = 0; i < data.size(); i++)
            if(data.get(i).getId() > id)
                toRemove.add(data.get(i));
        for(Organization organization : toRemove)
            data.remove(organization);
    }
    /**
     * Remove last element of the collection
     */
    public void removeLast() {
        if(!data.isEmpty())
            data.remove(data.size()-1);
        else
            throw new NullPointerException("Коллекция пуста");
    }
    /**
     * Clear collection
     */
    public void clear() {
        data.clear();
    }
    /**
     * Reverse collection elements
     */
    public void reverse() {
        int size = data.size();
        for (int i = 0; i < size / 2; i++) {
            Organization temp = data.get(i);
            data.set(i, data.get(size - i - 1));
            data.set(size - i - 1, temp);
        }
    }
    /**
     * Save collection to the file
     * @param filename File name
     * @throws IOException Exceptions with saving, i.e. access exceptions
     */
    public void save(String filename) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename));
        for (Organization organization : data) {
            String[] data = {organization.getId().toString(), 
                        Utils.escapeString(organization.getName()),
                        (Float.toString(organization.getCoordinates().getX())),
                        organization.getCoordinates().getY()+"",
                        organization.getCreationDate().toString(),
                        (Float.toString(organization.getAnnualTurnover())),
                        Utils.escapeString(organization.getFullName()),
                        organization.getType().toString(),
                        Utils.escapeString(organization.getOfficialAddress().getStreet()),
                        Utils.escapeString(organization.getOfficialAddress().getZipCode()),
                        (Float.toString(organization.getOfficialAddress().getTown().getX())),
                        (Double.toString(organization.getOfficialAddress().getTown().getY())),
                        Utils.escapeString(organization.getOfficialAddress().getTown().getName())};
            writer.writeNext(data);
        }
        writer.close();
    }
    /**
     * Convert CSV file to a collection objects
     * @param filename File name
     * @throws IOException Exceptions with saving, i.e. access exceptions, not found exceptions
     */
    public void parse(String filename) throws IOException {
        try {
            CSVReader readerCsv = new CSVReader(new FileReader(filename));
            String[] res;
            while((res = readerCsv.readNext()) != null) {
                data.add(Organization.parseCSVLine(res, false));
            }
            readerCsv.close();
        } catch(CsvException ex) {
            throw new IOException();
        }
    }
    /**
     * Get object by ID
     * @param id ID
     * @return Organization or null
     */
    public Organization get(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getId() == id) {
                return data.get(i);
            }
        }
        return null;
    }
    /**
     * Get max Id of existing objects
     * @return Max id
     */
    public int getMaxId() {
        int result = 0;
        for(Organization organization : data)
            result = Math.max(result, organization.getId());
        return result;
    }
    /**
     * Get all objects
     * @return All organizations
     */
    public Vector<Organization> getAll() {
        return data;
    }
    /**
     * Get collection initialization date
     * @return Initialization date
     */
    public LocalDate getInitializationDate() {
        return initializationDate;
    }
}
