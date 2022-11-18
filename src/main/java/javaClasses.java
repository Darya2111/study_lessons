public class javaClasses {

    public static void main(String[] args) {

        char symbol, anotherSymbol;
        symbol = 'w';
        boolean flag = false;
        byte aByte = 123; // -128 ... 127
        short aShort; // -32768 ... 32767
        int aInt = aByte; // 2 XXX XXX XXX
        Integer boxed = null;

        char[][] array = new char[][]{{'w', 'a', 's'}};

        long aLong = 123L;
        float aFloat = 1.2F;
        double aDouble = 1.2D;

        String aString = "Dima";
        String aString0 = "Dima";

        // + сложение
        // - вычитание
        // * умножение
        // / деление (целочисленное!)
        // % взятие остатка от деления

        // логические операторы
        // & И (&&)
        // | Или (||)
        // ! Отрицание
        // != не равно
        // == равно
        // instanceof
        // > больше
        // >= больше или равно
        // < меньше
        // <= меньше или равно

        if (aString.equals("Dima")) {
            if (aString.length() == 5) {
                System.out.println("Hi, " + aString);
            }
        } else if (aString.equals("Alex")) {
            System.out.println("Hi, " + aString);
        } else {
            System.out.println("Dosada");
        }

        switch (aString) {
            case "Dima":
                System.out.println("Hi, " + aString);
                break;
            case "Alex":
                System.out.println("Hi, " + aString);
                break;
        }

        aString = 3 > 2
                ? "Dima"
                : "Alex";

        if (3 > 2) {
            aString = "Dima";
        } else {
            aString = "Alex";
        }


        getMaxIntNumber();
        System.out.println(summ(aByte, (int) aLong));
    }


    static void getMaxIntNumber() {
        System.out.println("max value: " + Integer.MAX_VALUE);
    }

    static int summ(int first, int second) {
        return first + second;
    }
}
