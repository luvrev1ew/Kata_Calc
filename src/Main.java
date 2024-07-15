import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример в формате 2 + 2 или II + II!");

        String input = scanner.nextLine();

        String resultCalc = calc(input);

        System.out.println("Результат: " +  resultCalc);
    }

    public static String calc(String input){
        String [] strings = input.split(" ");
        if (strings.length != 3){
            System.out.println("Неверная форма записи примера!");
            System.exit(0);
        }

        String str1 = strings[0];
        String str2 = strings[2];
        String operation = strings[1];

        int num1 = 0;
        int num2 = 0;
        char operator = strings[1].charAt(0);
        boolean isRoman=false;

        if(Convertor.isRoman(str1) && Convertor.isRoman(str2)){
            num1 = Convertor.convertToArabic(str1);
            num2 = Convertor.convertToArabic(str2);
            isRoman = true;
        } else if (!Convertor.isRoman(str1) && !Convertor.isRoman(str2)){
            try{
                num1 = Integer.parseInt(str1);
            }catch (Exception e){
                System.out.println("Ошибка! Калькулятор может работать только с целыми числами!");
                System.exit(0);
            }
            if(num1 < 1 || num1 > 10){
                System.out.println("Ошибка! Калькулятор может принимать только числа от 1 до 10 включительно.");
                System. exit(0);
            }try {
                num2 = Integer.parseInt(str2);
            }catch (Exception e){
                System.out.println("Ошибка! Калькулятор может работать только с целыми числами!");
                System.exit(0);
            }
            if(num2 < 1 || num2 > 10){
                System.out.println("Ошибка! Калькулятор может принимать только числа от 1 до 10 включительно.");
                System. exit(0);
            }
            isRoman = false;
        }else {
            System.out.println("Числа должны быть в одном формате!");
            System.exit(0);
        }

        int result = 0;

        switch (operation) {
            case "+":
                result =  num1 + num2;
                break;
            case "-":
                result =  num1 - num2;
                if(isRoman && num1 < num2 || num1 == num2){
                    System.out.println("Результат не может быть меньше или равень нулю");
                    System.exit(0);
                }else {
                    result =  num1 - num2;
                }
                break;
            case "*":
                result =  num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result =  num1 / num2;
                } else {
                    System.out.println("Деление на ноль недопустимо!");
                }
                break;
            default:
                System.out.println("Неверная операция!");
                System.exit(0);

        }

        if(isRoman){
            return Convertor.convertToRoman(result);
        }else {
            return Integer.toString(result);
        }
    }
}

class Convertor {

    static String[] romanLiteral = new String[]{" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    static boolean isRoman(String val){
        for (int i = 0; i < romanLiteral.length; i++) {
            if (val.equals(romanLiteral[i])) {
                if(i > 10 || i < 1){
                    System.out.println("Error! Калькулятор может принимать только числа от I до X включительно.");
                    System.exit(0);
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    static int convertToArabic(String roman){
        for (int i = 0; i < romanLiteral.length; i++) {
            if (roman.equals(romanLiteral[i])) {
                return i;
            }
        }
        return 0;
    }

    static String convertToRoman(int arabian){
        return romanLiteral[arabian];
    }
}




