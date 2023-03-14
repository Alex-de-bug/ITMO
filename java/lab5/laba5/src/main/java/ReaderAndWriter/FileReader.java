package ReaderAndWriter;


import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Класс, читающий файл
 */

public class FileReader implements Reader{
    private String fileOut;

    /**
     * Метод, преобразующий json-файл в String методом FileInputStream
     * @param fileInput
     */
    @Override
    public String read(String fileInput){
        fileOut="";
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(fileInput))) {
            int data = input.read();
            while (data != -1) {
                fileOut += (char) data;
                data = input.read();
            }
        }
        catch (Exception e) {
            System.err.println("Ошибка чтения файла \n"+e.getMessage());
            e.getStackTrace();
            System.exit(1345);
        }
        return fileOut;
    }
}
