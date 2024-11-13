package TaskCommand;

import command.Command;

import static main1.Loader.dragonHashtable;

/**
 * Класс, очищающий коллекцию
 */
public class Clear implements Command {
    /**
     * Метод очищает коллекцию
     */
    @Override
    public void execute() {
        dragonHashtable.clear();
    }
}
