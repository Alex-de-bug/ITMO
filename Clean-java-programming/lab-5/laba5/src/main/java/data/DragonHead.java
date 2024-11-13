package data;

/**
 * Класс с данными количестве глаз на голове Дракона
 */

public class DragonHead {
    private long eyesCount;

    public DragonHead(long eyesCount) {
        this.eyesCount = eyesCount;
    }

    public long getEyesCount() {
        return eyesCount;
    }

    public void setEyesCount(long eyesCount) {
        this.eyesCount = eyesCount;
    }
    /**
     * Получение строкового представления объекта
     * @return String
     */
    @Override
    public String toString() {
        return "eyes count - " + eyesCount;
    }
}
