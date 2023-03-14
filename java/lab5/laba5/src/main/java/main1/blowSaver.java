package main1;
import Converters.ListToJsonParser;
import Converters.SortHashTable;
import FileChecker.DragonCheck;
import TaskCommand.Save;
import command.Command;
import data.Dragon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static main1.Loader.dragonHashtable;

public class blowSaver {
    Command saver = new Save(){
        @Override
        public void execute(){
            List<Dragon> checkedDragon= new DragonCheck().check(new SortHashTable().sort(dragonHashtable));
            String outputFileName = "/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/timeMashine.json";

            try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
                if(new ListToJsonParser().converter(checkedDragon).size()==3){
                    for (String value : new ListToJsonParser().converter(checkedDragon)) {
                        writter.write(value + "\n");
                    }
                }else if(new ListToJsonParser().converter(checkedDragon).size()>3){
                    for (String value : new ListToJsonParser().converter(checkedDragon)) {
                        if((!value.equals(new ListToJsonParser().converter(checkedDragon).get(0)))&&(!value.equals(new ListToJsonParser().converter(checkedDragon).get(new ListToJsonParser().converter(checkedDragon).size()-1)))&&(!value.equals(new ListToJsonParser().converter(checkedDragon).get(new ListToJsonParser().converter(checkedDragon).size()-2)))){
                            writter.write(value + ",\n");
                        }else writter.write(value + "\n");
                    }
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public void save(){
        saver.execute();
    }
}
