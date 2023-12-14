package hr.java.production.main;

import hr.java.production.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public class Main {
    private static final Integer NUMBER_OF_CATEGORIES = 3;
    private static final Integer NUMBER_OF_ITEMS = 3;
    private static final Integer NUMBER_OF_FACTORIES = 1;
    private static final Integer NUMBER_OF_STORES = 1;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static <T> void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //logger.info("program je pokrenut");


        List<Factory> factoryList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        List<Store> storeList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        List<Item> chosenStoreItemsList = new ArrayList<>();
        List<Item> chosenItemsList = new ArrayList<>();

        List<Store> listOfTehnicalStores = new ArrayList<>();
        List<Store> listOfFoodStores = new ArrayList<>();

        Map<Category, List<Item>> categoryItemMap = new HashMap<>();

        //metode
        categoryReader(categoryList);
        itemReader(categoryList, itemList, categoryItemMap);
        factoryReader(itemList, factoryList);
        storeReader(chosenItemsList, itemList, chosenStoreItemsList, storeList);


        //lv5 priprema
        itemList.sort((i1, i2) -> i1.getVolume().compareTo(i2.getVolume()));

        System.out.println("sortirani itemi: ");
        itemList.forEach(item -> System.out.println(item.getName() + " - " + item.getVolume()));
        long startTime = System.currentTimeMillis();

        BigDecimal averageVolume = itemList.stream().map(Item::getVolume).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(itemList.size()), RoundingMode.CEILING);

        Optional<List<Item>> filteredItems = Optional.of(itemList.stream().filter(item -> item.getVolume().compareTo(averageVolume) > 0).toList());

        System.out.println("average volume " + averageVolume);
        System.out.println("itemi sa nadprosjecnim volumenom: ");
        filteredItems.ifPresent(System.out::println);

        List<Item> discountGraterThen0List = itemList.stream().filter(item -> item.getDiscountAmount().compareTo(BigDecimal.ZERO) > 0).toList();
        discountGraterThen0List.forEach(item -> System.out.println("item sa popustom vecim od 0 " + item.getName() + " popust " + item.getDiscountAmount() + " %"));

        storeList.forEach(store -> System.out.println("broj itema u " + store.getName() + " je " + store.getItems().size()));

        System.out.println("__________________________________________________");
        System.out.println("Vrijeme izvođenja svih lamda funkcija: " + (System.currentTimeMillis() - startTime) + " ms");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dat/binary.dat"))) {
            oos.writeObject(discountGraterThen0List);

        }catch (IOException ex){
            ex.printStackTrace();
        }

        //----------------
        //Kraj main metode
        //----------------
    }

    /**
     * Metoda za unos podataka o kategorijama proizvoda.
     *
     * @param categoryList Lista objekata klase Category za pohranu unesenih podataka o kategorijama.
     */
    private static void categoryReader(List<Category> categoryList) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/categories.txt"))) {

            String line;

            while ((line = bufferedReader.readLine()) != null){
                
                Integer id = Integer.parseInt(line);
                String name = bufferedReader.readLine();
                String description = bufferedReader.readLine();

                Category newCategory = new Category(name,id,description);
                categoryList.add(newCategory);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda za unos podataka o dućanima i proizvodima koje prodaju.
     *
     * @param chosenItemsArray      Polje odabranih stavki.
     * @param itemList              Polje svih dostupnih stavki.
     * @param chosenStoreItemsArray Polje stavki koje su odabrane za pojedini dućan.
     * @param storeList            Lista objekata klase Store za pohranu unesenih podataka o dućanima.
     */
    private static void storeReader(List<Item> chosenItemsArray, List<Item> itemList, List<Item> chosenStoreItemsArray, List<Store> storeList) {

        try (BufferedReader reader = new BufferedReader(new FileReader("dat/stores.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {

                String name = line;
                Integer id = Integer.parseInt(reader.readLine().trim());
                String webAddress = reader.readLine();

                List<String> itemIds = Arrays.asList(reader.readLine().trim().split(","));
                List<Item> items = new ArrayList<>();

                for (String itemId : itemIds) {
                    int itemIdInt = Integer.parseInt(itemId.trim());
                    items.add(itemList.get(itemIdInt - 1));
                }

                Store store = new Store(name, id, webAddress, items);
                storeList.add(store);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda za unos podataka o tvornicama i proizvodima koje proizvode.
     *

     * @param itemList Lista svih dostupnih stavki.
     * @param factoryList Lista objekata klase Factory za pohranu unesenih podataka o tvornicama.
     */
    private static void factoryReader(List<Item> itemList, List<Factory> factoryList) {

        try (BufferedReader factoryReader = new BufferedReader(new FileReader("dat/factories.txt"));
             BufferedReader addressReader = new BufferedReader(new FileReader("dat/addresses.txt"))) {

            String line;
            while ((line = factoryReader.readLine()) != null) {

                String id = line.trim();
                String name = factoryReader.readLine().trim();
                Integer factoryId = Integer.parseInt(factoryReader.readLine().trim());

                List<String> chosenItemsListIndex = List.of(factoryReader.readLine().trim().split(","));
                List<Item> chosenItemsList = new ArrayList<>();


                //mozda je greska
                for (String chosenItemIndex : chosenItemsListIndex) {
                    chosenItemsList.add(itemList.get(Integer.parseInt(chosenItemIndex) - 1));
                }

                String street = addressReader.readLine().trim();
                String houseNumber = addressReader.readLine().trim();
                String city = addressReader.readLine().trim();
                String postalCode = addressReader.readLine().trim();


                Factory newFactory = new Factory(name, factoryId, new Address(street, houseNumber, city, postalCode), chosenItemsList);
                factoryList.add(newFactory);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metoda za unos podataka o predmetima (proizvodima).
     *
     * @param categoryList Polje dostupnih kategorija proizvoda.
     * @param itemList     Lista objekata klase Item za pohranu unesenih podataka o proizvodima.
     */
    private static void itemReader(List<Category> categoryList, List<Item> itemList, Map categoryItemMap) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/items.txt"))) {

            String line;

            while ((line = bufferedReader.readLine()) != null){
                String name = line.trim();

                line = bufferedReader.readLine();
                Integer id = Integer.parseInt(line.trim());
                Integer categoryIndex = Integer.parseInt(bufferedReader.readLine());
                BigDecimal width = new BigDecimal(bufferedReader.readLine());
                BigDecimal height = new BigDecimal(bufferedReader.readLine());
                BigDecimal length = new BigDecimal(bufferedReader.readLine());
                BigDecimal productionCost = new BigDecimal(bufferedReader.readLine());
                BigDecimal sellingPrice = new BigDecimal(bufferedReader.readLine());
                BigDecimal discountAmount = new BigDecimal(bufferedReader.readLine());

                Item newItem = new Item(name, id, categoryList.get(categoryIndex - 1), width, height, length, productionCost, sellingPrice, discountAmount);
                itemList.add(newItem);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}