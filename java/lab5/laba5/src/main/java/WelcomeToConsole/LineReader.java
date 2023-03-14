package WelcomeToConsole;
import ReaderAndWriter.Reader;

import java.util.Scanner;

/**
 * Класс считывает данные, введенные пользователем,
 * а также вывадит сообщение для пользователя и сообщение
 * об ошибке
 */

public class LineReader implements ConsoleReader {
    private Scanner scanner;
    public LineReader(){
        this.scanner = new Scanner(System.in);
    }
    public LineReader(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
