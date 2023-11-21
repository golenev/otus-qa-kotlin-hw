import java.lang.reflect.InvocationTargetException;

// Объявление интерфейса TestRunner с использованием обобщений T
public interface ITestRunnerJava<T>{
    void runTest(T steps, Runnable test) throws InvocationTargetException, IllegalAccessException;
}
