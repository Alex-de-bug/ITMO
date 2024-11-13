package net.alephdev;

import java.io.File;

public class Main {
    private static StorageManager storageManager;
    private static CommandManager commandManager;
    /**
     * Lab 6
     */
    public static void main(String[] args) {
        System.out.println("Система управления организациями");

        File file = new File("data.csv");
        String filename = file.getAbsolutePath();
//        String filename = System.getenv("LAB5_CSV");
//        if (filename == null) {
//            System.out.println("Переменная окружения для файла данных не найдена");
//            return;
//        }
        while(true) {
            storageManager = new StorageManager(filename);
            commandManager = new CommandManager();
            try {
                commandManager.run();
            } catch(Exception ex) {
                System.out.println("Перезапуск приложения...");
            }
        }
    }
    /**
     * Get storage manager
     * @return Storage manager
     */
    public static StorageManager getStorageManager() {
        return storageManager;
    }
    /**
     * Get command manager
     * @return Command manager
     */
    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
