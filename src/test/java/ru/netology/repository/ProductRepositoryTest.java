package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    ProductRepository repository = new ProductRepository();
    Product first = new Book(1,"ОНО",432,"Стивен Кинг");
    Product second = new Book(2,"Право на силу",546,"Шабалов");
    Product third = new Smartphone(3,"10XR",45_000,"Apple");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
    }

    @Test
    void shouldremoveByIdIfProductWithIdExist() {
        repository.removeById(2);
        Product[] expected = new Product[]{first,third};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowNotFoundExeptionIfProductWithIdNotExist() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(7);
        });
    }

}