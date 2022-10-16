import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение.");
        System.out.println("Результат вычислений: " + calc(scanner.nextLine()) + ".");
    }

    public static String calc(String input) throws Exception {
        int firstNumber;
        int secondNumber;
        char operator;
        String result;
        int resultNumber;
        boolean resultIsRoman = false;

        String[] inputSplit = input.split(" ");

        if (inputSplit.length == 3) {
            try {
                firstNumber = Integer.parseInt(inputSplit[0]);
                secondNumber = Integer.parseInt(inputSplit[2]);
            }
        catch (NumberFormatException e1){
            resultIsRoman = true;
            RomanNumber firstNumberRoman;
            RomanNumber secondNumberRoman;
            try{
                firstNumberRoman = new RomanNumber(inputSplit[0]);
                secondNumberRoman = new RomanNumber(inputSplit[2]);
            }
            catch (Exception e2){
                throw new Exception("Неверный формат ввода чисел.");
            }
            firstNumber = firstNumberRoman.toArabic();
            secondNumber = secondNumberRoman.toArabic();
        }
        } else {
            throw new Exception("Неверный формат ввода выражения.");
        }

        if(inputSplit[1].length()==1){
            operator = inputSplit[1].charAt(0);
        }
        else {
            throw new Exception("Неверный формат ввода оператора");
        }

        if (firstNumber>=1 && secondNumber >=1 && firstNumber <= 10 && secondNumber <= 10){
            resultNumber = switch (operator) {
                case '+' -> firstNumber + secondNumber;
                case '-' -> firstNumber - secondNumber;
                case '*' -> firstNumber * secondNumber;
                case '/' -> firstNumber / secondNumber;
                default -> throw new Exception("Неверный знак действия.");
            };
        } else {
            throw new Exception("Записаны слишком большие числа.");
        }

        if (resultIsRoman) {
            result = toRoman(resultNumber);
        } else {
            result = String.valueOf(resultNumber);
        }

        return result;
    }

    enum RomanDigit {
        I(1), V(5), X(10);
        private final int value;

        RomanDigit(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

      static class RomanNumber {
        private RomanDigit[] digits;
        private int length;

        public RomanNumber(String inputString){
            length = inputString.length();
            digits = new RomanDigit[length];
            for(int i = 0; i<length; i++){
                digits[i] = RomanDigit.valueOf(String.valueOf(inputString.charAt(i)));
            }
        }

        public int toArabic(){
            int result = digits[length-1].getValue();

            for(int i = 0; i<length-1; i++){
                if (digits[i].getValue()<digits[i+1].getValue()){
                    result = result - digits[i].getValue();
                }
                else {
                    result = result + digits[i].getValue();
                }
            }

            return result;
        }
      }
    static String toRoman(int input) throws Exception {
        StringBuilder result = new StringBuilder();
        if(input<=0){
            throw new Exception("Результат не может быть записан римскими цифрами.");
        }
        while (input >= 50){
            input = input - 50;
            result.append("L");
        }
        while (input >= 40){
            input = input - 40;
            result.append("XL");
        }
        while (input >= 10){
            input = input - 10;
            result.append("X");
        }
        while (input >= 9){
            input = input - 9;
            result.append("IX");
        }
        while (input >= 5){
            input = input - 5;
            result.append("V");
        }
        while (input >= 4){
            input = input - 4;
            result.append("IV");
        }
        while (input >= 1){
            input = input - 1;
            result.append("I");
        }
        return result.toString();
    }
}