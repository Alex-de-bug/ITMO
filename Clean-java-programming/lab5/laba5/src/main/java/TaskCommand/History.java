package TaskCommand;

import command.Command;

import static ReaderAndWriter.CommandReader.history;

/**
 * Класс, выводящий историю команд
 */

public class History implements Command {

    /**
     * Метод выводящий последние 12 команд
     */
    @Override
    public void execute() {
        if(history.size()>=12){
            for (int i = 1; i < 12; i++) {
                System.out.println(history.get(history.size()-i));
            }
        }else{
            for (int i = 0; i < history.size(); i++) {
                System.out.println(history.get(i));
            }
        }
    }
}
