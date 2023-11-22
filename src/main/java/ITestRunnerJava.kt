import java.lang.reflect.InvocationTargetException

// Объявление интерфейса TestRunner с использованием обобщений T
interface ITestRunnerJava<T> {
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    fun runTest(steps: T, test: Runnable)
}
