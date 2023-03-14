package FileChecker;

import data.Dragon;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, проверяющий драконов на дефекты
 */

public class DragonCheck{

    /**
     * Проверка драконов
     * @param dragonsList
     * @return "List<Dragon>"
     */
    public List<Dragon> check(List<Dragon> dragonsList) {
        List<Integer> id = new ArrayList<>();
        List<Integer> badIndex = new ArrayList<>();

        for (int i = 0; i < dragonsList.size(); i++) {

            //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
            // Значение этого поля должно генерироваться автоматически
            if((dragonsList.get(i).getId()>0)&&(!id.contains(dragonsList.get(i).getId()))){
                id.add(dragonsList.get(i).getId());
            }else{
                badIndex.add(i);
            }

            //Поле не может быть null, Строка не может быть пустой
            if((dragonsList.get(i).getName().isEmpty())||(dragonsList.get(i).getName()==null)){
                badIndex.add(i);
            }

            //Максимальное значение поля: 962, Поле не может быть null
            //Максимальное значение поля: 450
            if((dragonsList.get(i).getCoordinates().getX()>962)||(dragonsList.get(i).getCoordinates().getY()>450.0)||(dragonsList.get(i).getCoordinates()==null)){
                badIndex.add(i);
            }

            //Поле не может быть null
            if(dragonsList.get(i).getCreationDate()==null){
                badIndex.add(i);
            }

            //Значение поля должно быть больше 0
            if(!(dragonsList.get(i).getAge()>0)){
                badIndex.add(i);
            }

            //Значение поля должно быть больше 0
            if(!(dragonsList.get(i).getWingspan()>0)){
                badIndex.add(i);
            }

            if(dragonsList.get(i).getColor()==null){
                badIndex.add(i);
            }


        }
        for (int i = 0; i < dragonsList.size(); i++) {
            if(badIndex.contains(i)){
                dragonsList.remove(i);
                System.out.println("Один из драконов не прошёл проверку, так как неверно введены данные");
            }
        }

        return dragonsList;
    }
}
