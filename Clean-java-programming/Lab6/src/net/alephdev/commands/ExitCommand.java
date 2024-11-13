package net.alephdev.commands;

import java.io.File;
import java.util.Scanner;
import net.alephdev.Main;
import net.alephdev.StorageManager;

/**
 * Command for exit
 * @author MixaDev
 */
public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        try {
            if(new File(StorageManager.autosaveName).exists()) {
                System.out.print("У вас есть несохраненные данные, напишите '+', что бы их сохранить: ");
                if((new Scanner(System.in)).nextLine().equals("+"))
                    Main.getCommandManager().executeInput("save");
            }
        } catch(Exception ex) {
            System.out.println("Не удалось обработать файл автосохранения");
        }
        new File(StorageManager.autosaveName).delete();
        System.exit(0);
    }
    @Override
    public String getDesctiption() {
        return "завершить программу (без сохранения в файл)";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
