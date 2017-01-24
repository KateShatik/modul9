import java.util.*;

import java.util.Set;
import java.lang.String;
import java.util.stream.Stream;
import java.util.function.Predicate;
/**
 * Created by Катя on 24.01.2017.
 */
public class Main {

    public static void sortByPrice(List<Order> orders) {//отсортируйте список за ценой заказа по убыванию
        Comparator<Order> byPrice = (a, b) -> b
                .getPrice()- a.getPrice();
        orders.stream().sorted(byPrice)
                .forEach(o -> System.out.println(o));
    }
    public static void sortByPriceAndCity(List<Order> orders) {//отсортируйте список за ценой заказа по возрастанию и за городом пользователя
        Comparator<Order> byPrice = (a, b) -> a
                .getPrice()- b.getPrice();

        Comparator<Order> byCity = (a, b) -> a.getUser().getCity()
                .compareTo(b.getUser().getCity());

        orders.stream().sorted(byPrice.thenComparing(byCity))
                .forEach(o -> System.out.println(o));
    }

    public static void sortByItemName_Id_City(List<Order> orders) {
        //отсортируйте список за наименованием товара, идентификатором заказа, и городом пользователя
        Comparator<Order> byItemName = (a, b) -> (int)a
                .getItemName().compareTo(b.getItemName());

        Comparator<Order> byShopIdentificator = (a, b) -> (int)a.getId()
               - (int)b.getId();

        Comparator<Order> byCity = (a, b) -> a.getUser().getCity()
                .compareTo(b.getUser().getCity());

        orders.stream().sorted(byItemName.thenComparing(byShopIdentificator).thenComparing(byCity))
                .forEach(o -> System.out.println(o));
    }

    public static void deleteDuplicates(List<Order> orders) {//удалите дублированные данные со списка
       orders.parallelStream().distinct().forEach(o -> System.out.println(o));
    }

    public static void deleteOrdersPriceLess1500(List<Order> orders) {//удалите объекты, где цена меньше 1500
        orders.stream().filter((o) -> o.getPrice() > 1500).forEach(o -> System.out.println(o));
    }

    public static void divideInto_USD_and_UAH_lists(List<Order> orders) {
        //разделите список на 2 списка - заказы в долларах и в гривнах
        System.out.println("list of orders with USD");
        orders.stream().filter((o) -> o.getCurrency()== Currency.USD).forEach(o -> System.out.println(o));
        System.out.println("list of orders with UAH");
        orders.stream().filter((o) -> o.getCurrency()== Currency.UAH).forEach(o -> System.out.println(o));
    }

    public static void uniqueCity(List<Order> orders,String city) {
        //разделите список на столько списков, сколько уникальных городов в User
        System.out.println("list of orders with city "+ city);
        orders.stream().filter((o) -> o.getUser().getCity().equals(city)).forEach(o -> System.out.println(o));

    }

    public static void lastNamePetrov(Set<Order> ordersTreeSet) {
        //проверьте, содержит ли сет заказ, где фамилия пользователя - “Petrov”
      Predicate<Order> predicate = (o)-> o.getUser().getLastName().equals("Petrov");

       ordersTreeSet.forEach(order ->  {if(predicate.test(order)){
           System.out.println("true");}
        } );

    }

    public static void deleteOrdersUSD(Set<Order> ordersTreeSet) {
        //удалите заказы в USD

        ordersTreeSet.stream().filter((o)->o.getCurrency()!= Currency.USD).forEach(o -> System.out.println(o));

    }
    public static void main(String[] args) {
        User user1 = new User(100, "Marina", "Shilkova", "Kiev", 1000);
        User user2 = new User(101, "Masha", "Stepko", "Lviv", 1500);
        User user3 = new User(102, "Sasha", "Borovaya", "Odessa", 2000);
        User user4 = new User(103, "Olya", "Gogol", "Kiev", 3000);
        User user5 = new User(104, "Kate", "Orel", "Odessa", 6000);
        User user6 = new User(105, "Karina", "Donchenko", "Kiev", 700);
        User user7 = new User(106, "Oleg", "Petrov", "Lviv", 500);
        User user8 = new User(107, "Lera", "Ivanova", "Donezk", 1000);
        User user9 = new User(108, "Yulia", "Lebedeva", "Lviv", 2000);
        User user10 = new User(109, "Dana", "Sava", "Donezk", 1700);

        Order order1 = new Order(100, 2000, Currency.UAH, "pen", "Shop1", user1);
        Order order2 = new Order(101, 100, Currency.USD, "bag", "Shop2", user2);
        Order order3 = new Order(102, 200, Currency.UAH, "bag", "Shop2", user3);
        Order order4 = new Order(103, 2030, Currency.UAH, "iron", "Shop1", user4);
        Order order5 = new Order(104, 200, Currency.USD, "cup", "Shop1", user5);
        Order order6 = new Order(105, 250, Currency.UAH, "dress", "Shop2", user6);
        Order order7 = new Order(106, 270, Currency.UAH, "t-shirt", "Shop2", user7);
        Order order8 = new Order(107, 290, Currency.USD, "gloves", "Shop2", user8);
        Order order9 = new Order(100, 2000, Currency.UAH, "pen", "Shop1", user1);
        Order order10 = new Order(101, 100, Currency.USD, "bag", "Shop2", user2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);
        orders.add(order9);
        orders.add(order10);

        System.out.println("List sorted by price from highest to lowest");
        sortByPrice(orders);

        System.out.println("List sorted by price from lowest to highest and by city");
       sortByPriceAndCity(orders);

        System.out.println("List sorted by itemName,ShopIdentificator and user's city");
        sortByItemName_Id_City(orders);

        System.out.println("Delete duplicates");
        deleteDuplicates(orders);

        System.out.println("Delete items where price <1500");
        deleteOrdersPriceLess1500(orders);

        divideInto_USD_and_UAH_lists(orders);

        //разделите список на столько списков, сколько уникальных городов в User
        Set<String> uniqueCity = new TreeSet<>();
        orders.forEach(order -> uniqueCity.add(order.getUser().getCity()));//список уникальных городов
        uniqueCity.forEach(city -> uniqueCity(orders,city));

        //проверьте, содержит ли сет заказ, где фамилия пользователя - “Petrov”
        Set<Order> ordersTreeSet = new TreeSet<>();
        orders.forEach(order -> ordersTreeSet.add(order));
        System.out.println("New TreeSet");
        System.out.println(ordersTreeSet);
        System.out.println("Check if set contains user Petrov");
        lastNamePetrov(ordersTreeSet);
        System.out.println("Delete orders where currency = USD");
        deleteOrdersUSD(ordersTreeSet);



    }
}
