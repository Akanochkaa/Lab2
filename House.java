public class House {
    private int floors;

    public House(int floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        String ending;
        int lastDigit = floors % 10;
        int lastTwoDigits = floors % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) ending = "этажами";
        else if (lastDigit == 1) ending = "этажом";
        else if (lastDigit >= 2 && lastDigit <= 4) ending = "этажами";
        else ending = "этажами";

        return "дом с " + floors + " " + ending;
    }
}