import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class TestRunnerImplementation : ITestRunnerJava<MyJavaTestSteps> {
    // Класс MyTestRunner, реализующий интерфейс TestRunner с параметром типа MyTestSteps
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    override fun runTest(steps: MyJavaTestSteps, test: Runnable) {
        val listBefore: MutableList<Method> =
            ArrayList() // Создание изменяемого списка listBefore, содержащего элементы типа Method
        val listAfter: MutableList<Method> =
            ArrayList() // Создание изменяемого списка listAfter, содержащего элементы типа Method

        for (method in steps.javaClass.declaredMethods) {
            // Для каждого объявленного метода в классе steps
            if (method.name.startsWith("before")) {
                // Если имя метода начинается с "before"
                listBefore.add(method) // Добавить метод в listBefore
            } else if (method.name.startsWith("after")) {
                // В противном случае, если имя метода начинается с "after"
                listAfter.add(method) // Добавить метод в listAfter
            }
        }

        for (method in listBefore) {
            // Для каждого метода в listBefore
            method.invoke(steps) // Вызвать метод, передавая steps в качестве аргумента
        }

        test.run() // Вызвать функцию test

        for (method in listAfter) {
            // Для каждого метода в listAfter
            method.invoke(steps) // Вызвать метод, передавая steps в качестве аргумента
        }
    }
}
