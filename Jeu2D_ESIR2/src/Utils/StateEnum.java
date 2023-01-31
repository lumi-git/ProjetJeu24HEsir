package Utils;

//states if the game, found in gamePanel
public enum StateEnum {
    Play(0),
    Main(1),
    Translate(2),
    GameOver(3);

    private int value;

    StateEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}


