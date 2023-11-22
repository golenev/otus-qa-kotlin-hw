import java.lang.reflect.InvocationTargetException

object Main {
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val runner = TestRunnerImplementation()

        // Создание объекта runner класса MyTestRunner
        runner.runTest(MyJavaTestSteps()) { println("someTest") }
        // Вызов метода runTest с объектом MyTestSteps и лямбда-выражением для тестирования
    }
}
