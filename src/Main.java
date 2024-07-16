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
            throw new ArithmeticException("Неверная форма записи примера!");
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
            }catch (NumberFormatException e){
                throw  new NumberFormatException("Ошибка! Калькулятор может работать только с целыми числами!");
            }
            if(num1 < 1 || num1 > 10){
                throw new NumberFormatException("Ошибка! Калькулятор может принимать только числа от 1 до 10 включительно.");
            }try {
                num2 = Integer.parseInt(str2);
            }catch (NumberFormatException e){
                throw  new NumberFormatException("Ошибка! Калькулятор может работать только с целыми числами!");
            }
            if(num2 < 1 || num2 > 10){
                throw new NumberFormatException("Ошибка! Калькулятор может принимать только числа от 1 до 10 включительно.");
            }
            isRoman = false;
        }else {
            throw new NumberFormatException("Числа должны быть в одном формате!");
        }

        int result = 0;

        switch (operation) {
            case "+":
                result =  num1 + num2;
                break;
            case "-":
                result =  num1 - num2;
                if(isRoman && num1 < num2 || isRoman && num1 == num2){
                    throw new ArithmeticException("Результат не может быть меньше или равень нулю");
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
                    throw new ArithmeticException("Деление на ноль недопустимо!");
                }
                break;
            default:
                throw new ArithmeticException("Неверная операция!");
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
                    throw  new NumberFormatException("Ошибка! Калькулятор может принимать только числа от I до X включительно.");
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




