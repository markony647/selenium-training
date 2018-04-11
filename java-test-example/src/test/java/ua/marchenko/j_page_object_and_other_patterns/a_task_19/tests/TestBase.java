package ua.marchenko.j_page_object_and_other_patterns.a_task_19.tests;


import org.junit.After;
import org.junit.Before;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.app.Application;

public class TestBase {

    public Application app;
    public static int timeout = 2;

    @Before
    public void setUp() {
        app = new Application();
    }

    @After
    public void tearDown() {
        app.quit();
    }
}
