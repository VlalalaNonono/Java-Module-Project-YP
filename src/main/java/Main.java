import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Food> shoppingCard = new ArrayList<Food>();
        System.out.println("Добро пожаловать в Кото-Кафе, это консольное приложение для подсчета цены сьеденного корма!");
        String meow;
        Calculator.checkNumberOfCats();
        do {
            ShoppingCard (shoppingCard);
            System.out.println("Хотите добавить ещё одну позицию корма?\n\nВведите 'Продолжить' чтобы добавить ещё корма, или 'Завершить' чтобы разделить счет: ");
            meow = scanner.nextLine();
            if (!meow.equalsIgnoreCase("Завершить") && !meow.equalsIgnoreCase("Продолжить") ){
                System.out.println("\n\nОшибка: введите 'Продолжить' чтобы добавить ещё корма, или 'Завершить' чтобы разделить счет: ");
                meow = scanner.nextLine();
            }
        } while (!meow.equalsIgnoreCase("Завершить"));
        Calculator.total(shoppingCard);
        System.out.println("Список заказанных кормов");
        for( Food name : shoppingCard){
            System.out.println(name.getName());
        }

        System.out.println("Сумма на каждого котика составила: " + String.format("%.2f", Calculator.totalPricePerCats) + Formator.around());
    }
    public static void ShoppingCard (ArrayList<Food> shoppingCard){
        Scanner scanner = new Scanner(System.in);
        String name;
        double price = 0;
        boolean validInput = false;

        System.out.println("Введите название корма");
        name = scanner.nextLine();

        while (!validInput) {
            System.out.print("Введите цену корма: ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                if (price <= 0) {
                    System.out.println("Цена корма не может быть отрицательной или равна нули (БЕСПЛАТНО НЕ КОРМИМ)!");
                } else {
                    validInput = true;
                }
            } else {
                System.out.println("Возможно введена точка или буква");
                scanner.nextLine();
            }
        }
        Food feed = new Food(name, price);
        shoppingCard.add(feed);
    }
}

class Food{
    String name;
    double price ;
    Food (String name, double price){
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
}
class Calculator {
    public static int countCats;
    public static double totalPricePerCats;
    public static int checkNumberOfCats() {
        Scanner scanner = new Scanner(System.in);
        int cat = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введите количество котиков: ");
            if (scanner.hasNextInt()) {
                cat = scanner.nextInt();
                if (cat <= 1) {
                    System.out.println("Котиков должно быть больше двух");
                } else {
                    validInput = true;
                }
            } else {
                System.out.println("Возможно введены буквы или иные символы");
                scanner.nextLine();
            }
        }
//        do {
//            System.out.println("Введите количество котиков.");
//            while (!scanner.hasNextInt()){
//                System.out.println("Это не число");
//                System.out.println("Введите количество котиков.");
//                scanner.next();
//            }
//            cat = scanner.nextInt();
//            if (cat <= 1) {
//                System.out.println("Котиков должно быть больше двух");
//            }
//        } while (cat <= 1);
//        scanner.nextLine();
        System.out.println("Отлично");
        countCats = cat;
        return countCats;
    }
    public static double total (ArrayList<Food> shoppingCard){
        double sum = 0;
        for (Food price: shoppingCard){
           sum =  sum + price.getPrice();
        }
        sum = sum / countCats;
        totalPricePerCats = sum;
        return Math.round(sum);
    }

}

class Formator {

    public static String  around() {
        String output;
        double money = Calculator.totalPricePerCats;
        int x = (int) money % 10;
        if (x % 100 >= 11 && x % 100 <= 14) {
            output = " рублей";
        } else {
            switch (x) {
                case 1:
                    output = " рубль";
                    break;
                case 2:
                case 3:
                case 4:
                    output = " рубля";
                    break;
                default:
                    output = " рублей";
            }
        }
        return output;
    }

}
