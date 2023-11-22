import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunnerImplementation implements ITestRunnerJava<MyJavaTestSteps> {
    // Класс MyTestRunner, реализующий интерфейс TestRunner с параметром типа MyTestSteps

    @Override
    public void runTest(MyJavaTestSteps steps, Runnable test) throws InvocationTargetException, IllegalAccessException {
        List<Method> listBefore = new ArrayList<>(); // Создание изменяемого списка listBefore, содержащего элементы типа Method
        List<Method> listAfter = new ArrayList<>(); // Создание изменяемого списка listAfter, содержащего элементы типа Method

        for (Method method : steps.getClass().getDeclaredMethods()) {
            // Для каждого объявленного метода в классе steps
            if (method.getName().startsWith("before")) {
                // Если имя метода начинается с "before"
                listBefore.add(method); // Добавить метод в listBefore
            } else if (method.getName().startsWith("after")) {
                // В противном случае, если имя метода начинается с "after"
                listAfter.add(method); // Добавить метод в listAfter
            }
        }

        for (Method method : listBefore) {
            // Для каждого метода в listBefore
            method.invoke(steps); // Вызвать метод, передавая steps в качестве аргумента


        }

        test.run(); // Вызвать функцию test

        for (Method method : listAfter) {
            // Для каждого метода в listAfter
            method.invoke(steps); // Вызвать метод, передавая steps в качестве аргумента
        }
    }


}
