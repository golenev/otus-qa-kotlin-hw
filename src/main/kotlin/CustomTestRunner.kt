import java.lang.reflect.Method


    interface TestRunner {
        fun runTest(steps: MyTestSteps, test: () -> Unit)
    }

    class MyTestSteps {
        fun after() {
            println("After method is executed")
        }
        fun before() {
            println("Before method is executed")
        }
    }

    class MyTestRunner : TestRunner {
        override fun runTest(steps: MyTestSteps, test: () -> Unit) {

            val listBefore = mutableListOf<Method>()
            val listAfter = mutableListOf<Method>()
            steps::class.java.declaredMethods.forEach {
                if (it.name.startsWith("before")) {
                    listBefore.add(it)
                }
                else if (it.name.startsWith("after")) {
                    listAfter.add(it)
                }
            }
            for (method in listBefore) {
                method.invoke(steps)
            }

            test()

            for (method in listAfter) {

                method.invoke(steps)
            }
        }
    }


    fun main() {
        val runner = MyTestRunner()
        runner.runTest(MyTestSteps()) {
            println("someTest")
        }
    }

