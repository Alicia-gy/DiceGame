package itacademy.dicegame.utilities;

public class DiceRoller {

    public static byte d6Roll() {
        return (byte) (Math.random() * 6 + 1);
    }

}
