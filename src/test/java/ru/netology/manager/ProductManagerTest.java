package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "Метро 2033", 500, "Глуховский");
    Product book2 = new Book(2, "Выстоять", 600, "Громов");
    Product book3 = new Book(3, "Земля лишних", 550, "Круз");
    Product book4 = new Book(4, "Исход", 650, "Круз");
    Product smart1 = new Smartphone(5, "X6600", 6500, "Nokia");
    Product smart2 = new Smartphone(6, "XR10", 50_000, "Apple");
    Product smart3 = new Smartphone(7, "X50", 15000, "LG");
    Product smart4 = new Smartphone(8, "X13 Pro", 100_000, "Apple");


    @BeforeEach
    public void installation() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        manager.add(smart4);

    }


    @Test
        // Поиск по названию книги. Книги нет
    void SearchByBookTitleNoBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Дом");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Поиск по названию книги. Книга есть
    void SearchByBookTitle() {
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy("Выстоять");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Поиск по автору книги.
    void searchForBooksByAuthor() {
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("Глуховский");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Все книги автора
    void searchForAllBooksByAuthor() {
        Product[] expected = new Product[]{book3,book4};
        Product[] actual = manager.searchBy("Круз");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Книги с автором нет
    void searchNotAllBooksByAuthor() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Шабалов");
        assertArrayEquals(expected, actual);
    }

    @Test // Поиск по названию модели смартфона.Модели нет
    void searchBySmartphoneModelNameNotModel() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("7700");
        assertArrayEquals(expected, actual);
    }

    @Test // Поиск по названию модели смартфона.
    void searchBySmartphoneModelName() {
        Product[] expected = new Product[]{smart1};
        Product[] actual = manager.searchBy("6600");
        assertArrayEquals(expected, actual);
    }

    @Test // Поиск по названию производителя.
    void searchBySmartphoneManufacturer() {
        Product[] expected = new Product[]{smart3};
        Product[] actual = manager.searchBy("LG");
        assertArrayEquals(expected, actual);
    }

    @Test // Поиск всех смартфонов производителя
    void searchAllBySmartphoneManufacturer() {
        Product[] expected = new Product[]{smart2,smart4};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test // Поиск по названию производителя.Производителя нет
    void searchBySmartphoneNotManufacturer() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

}
