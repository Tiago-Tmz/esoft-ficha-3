package pt.ipleiria.estg.ei.dei.esoft.tests;
import org.junit.jupiter.api.*;
/**
 *
 * JUnit 5 Framework recommends:
 *
 * Methods annotated with @BeforeAll and @AfterAll must be static.
 *
 */
public class FooTestCase {

    @BeforeAll
    public static void setUpClass() {
        System.out.println(
                "setUpClass() | I print before everything, and only once!"
        );
    }
    @AfterAll
    public static void tearDownClass() {
        System.out.println(
                "tearDownClass() | I print after everything, and only once!"
        );
    }
    @BeforeEach
    public void setUp() {
        System.out.println("setup() | I print before each test!");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("tearDown() | I print after each test!");
    }

    @Test
    public void test1() {
        System.out.println("Test 1");
    }
    @Test
    public void test2() {
        System.out.println("Test 2");
    }
    @Test
    public void test3() {
        System.out.println("Test 3");
    }
}
