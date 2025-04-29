package ru.netolgy.OOP_3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для класса MovieManager.
 * Проверяет работу конструкторов и основных методов: addMovie, findAll, findLast.
 */
class MovieManagerTest {

    private MovieManager defaultManager;     // Лимит по умолчанию = 5
    private MovieManager customLimitManager; // Лимит = 3

    /**
     * Инициализация перед каждым тестом: создаём два экземпляра MovieManager.
     */
    @BeforeEach
    void setUp() {
        defaultManager = new MovieManager();
        customLimitManager = new MovieManager(3);
    }

    /**
     * Проверяем, что конструктор по умолчанию устанавливает лимит равным 5.
     */
    @Test
    void testConstructorDefaultLimitIs5() {
        assertEquals(5, defaultManager.getLimit());
    }

    /**
     * Проверяем, что пользовательский конструктор устанавливает заданный лимит.
     */
    @Test
    void testConstructorCustomLimitSetCorrectly() {
        assertEquals(3, customLimitManager.getLimit());
    }

    /**
     * Проверяем добавление одного фильма.
     * После добавления он должен быть доступен через findAll().
     */
    @Test
    void testAddMovieAddsSingleMovie() {
        defaultManager.addMovie("Movie 1");
        assertArrayEquals(new String[]{"Movie 1"}, defaultManager.findAll());
    }

    /**
     * Проверяем добавление нескольких фильмов подряд.
     * Массив должен содержать фильмы в порядке добавления.
     */
    @Test
    void testAddMovieAddsMultipleMoviesInOrder() {
        defaultManager.addMovie("Movie 1");
        defaultManager.addMovie("Movie 2");
        defaultManager.addMovie("Movie 3");
        assertArrayEquals(new String[]{"Movie 1", "Movie 2", "Movie 3"}, defaultManager.findAll());
    }

    /**
     * Проверяем, что метод findAll возвращает все фильмы в порядке добавления.
     */
    @Test
    void testFindAllReturnsAllMoviesInOrderAdded() {
        defaultManager.addMovie("Movie 1");
        defaultManager.addMovie("Movie 2");
        assertArrayEquals(new String[]{"Movie 1", "Movie 2"}, defaultManager.findAll());
    }

    /**
     * Проверяем findLast(), если фильмов меньше лимита.
     * Должны вернуться последние фильмы в обратном порядке.
     */
    @Test
    void testFindLastWhenMoviesLessThanLimit() {
        customLimitManager.addMovie("Movie 1");
        customLimitManager.addMovie("Movie 2");
        assertArrayEquals(new String[]{"Movie 2", "Movie 1"}, customLimitManager.findLast());
    }

    /**
     * Проверяем findLast(), если количество фильмов равно лимиту.
     * Все фильмы должны быть возвращены в обратном порядке.
     */
    @Test
    void testFindLastWhenMoviesEqualToLimit() {
        customLimitManager.addMovie("Movie 1");
        customLimitManager.addMovie("Movie 2");
        customLimitManager.addMovie("Movie 3");
        assertArrayEquals(new String[]{"Movie 3", "Movie 2", "Movie 1"}, customLimitManager.findLast());
    }

    /**
     * Проверяем findLast(), если фильмов больше лимита.
     * Возвращаются только N последних (где N — лимит), в обратном порядке.
     */
    @Test
    void testFindLastWhenMoviesGreaterThanLimit() {
        customLimitManager.addMovie("Movie 1");
        customLimitManager.addMovie("Movie 2");
        customLimitManager.addMovie("Movie 3");
        customLimitManager.addMovie("Movie 4");
        assertArrayEquals(new String[]{"Movie 4", "Movie 3", "Movie 2"}, customLimitManager.findLast());
    }

    /**
     * Проверяем findLast(), если список фильмов пуст.
     * Ожидается пустой массив.
     */
    @Test
    void testFindLastEmptyListReturnsEmptyArray() {
        assertArrayEquals(new String[0], defaultManager.findLast());
    }
}