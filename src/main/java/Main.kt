import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TestRunnerImplementation runner = new TestRunnerImplementation();
        // Создание объекта runner класса MyTestRunner

        runner.runTest(new MyJavaTestSteps(), () -> System.out.println("someTest"));
        // Вызов метода runTest с объектом MyTestSteps и лямбда-выражением для тестирования
    }

}
