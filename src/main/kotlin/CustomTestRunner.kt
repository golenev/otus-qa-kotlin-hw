import java.lang.reflect.Method
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions


interface TestRunner<T> {
    // Объявление интерфейса TestRunner с использованием дженерика T
    fun runTest(steps: T, test: () -> Unit)
}

class MyTestSteps {
    // Класс MyTestSteps с методами before и after
    fun after() {
       "After method is executed".log()
    }
    fun before() {
        "Before method is executed".log()
    }
}

// функция расширения для String с именем .log приименяется см выше
fun String.log() {
    println("-> $this running...")
}

class MyTestRunner : TestRunner<MyTestSteps> { // Объявляем класс MyTestRunner, который реализует интерфейс TestRunner с параметром типа MyTestSteps
    override fun runTest(steps: MyTestSteps, test: () -> Unit) { // Переопределяем метод runTest с параметрами steps типа MyTestSteps и test типа функции без параметров и возвращаемого значения
        val listBefore = mutableListOf<KFunction<*>>() // Создаем изменяемый список listBefore, содержащий элементы типа KFunction
        val listAfter = mutableListOf<KFunction<*>>() // Создаем изменяемый список listAfter, содержащий элементы типа KFunction

        steps::class.declaredMemberFunctions.forEach { // Для каждого объявленного метода в классе steps
            if (it.name.startsWith("before")) { // Если имя метода начинается с "before"
                listBefore.add(it) // Добавляем метод в список listBefore
            }
            else if (it.name.startsWith("after")) { // Иначе, если имя метода начинается с "after"
                listAfter.add(it) // Добавляем метод в список listAfter
            }
        }

        for (method in listBefore) { // Для каждого метода в списке listBefore
            method.call(steps) // Вызываем метод, передавая ему steps в качестве аргумента
        }

        test() // Вызываем функцию test

        for (method in listAfter) { // Для каждого метода в списке listAfter
            method.call(steps) // Вызываем метод, передавая ему steps в качестве аргумента
        }
    }
}

fun main() {
    val runner = MyTestRunner()
    // Создание объекта runner класса MyTestRunner

    runner.runTest(MyTestSteps()) {
        println("someTest")
    }
    // Вызов метода runTest с объектом MyTestSteps и лямбда-выражением для тестирования
}

