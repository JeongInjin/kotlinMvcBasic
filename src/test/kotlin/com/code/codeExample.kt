package com.code

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class codeExample {

    private val log: Logger = LoggerFactory.getLogger(codeExample::class.java)

    @Test
    fun `for loop example`() {
        //1 일반 for
        for(i: Int in 1..10)
            print("$i ")    //output : 1, 2, 3, 4, 5 ... 10
        println()
        val len: Int = 5
        for(i in 1..len)
            print("$i ")    //output : 1, 2, 3, 4, 5
        println()
        for(i in 1 until len)
            print("$i ")    //output : 1, 2, 3, 4
        println()
        println("======================================================================")

        //증가값 for
        for(i: Int in 1..10 step(2))
            print("$i ")    //output : 1, 3, 5, 7, 9
        println()
        println("======================================================================")

        //역 for
        for(i in 10 downTo 1)    //output : 10, 9, 8, 7 ... 1
            print("$i ")
        println()
        for(i in 10 downTo 1 step(2))    //output : 10, 8, 6, 4, 2
            print("$i ")
        println()
        println("======================================================================")

        val fruits = listOf("Apple", "Banana", "Cherry", "Durian")

        for ((index, fruit) in fruits.withIndex()) {
            println("$index, $fruit")
        }

        println("======================================================================")

        fruits.forEachIndexed { i, v -> println("$i, $v")  }
    }

    @Test
    fun `Data Structure example`() {
        // https://kkeman.github.io/kotlin/android/kotlin-collection/
        //코틀린에는 Immutable(읽기전용), Mutable(추가, 삭제 및 변경 가능) 형식이 있다.
        //필요에 따라 사용하며, 나는 대부분 mutable 형식으로 사용한다.

        //List - Immutable
        val numbers_Immutable = listOf("one", "two", "three", "four")
        log.info("numbers[3]: ${numbers_Immutable[3]}")
        //List - Mutable
        val numbers_Mutable = mutableListOf(1, 2, 3, 4)
        log.info("numbers_Mutable: $numbers_Mutable")
        numbers_Mutable.add(5)
        log.info("numbers_Mutable.add(5): $numbers_Mutable")
        numbers_Mutable.removeAt(1)
        log.info("numbers_Mutable.removeAt(1): $numbers_Mutable")
        numbers_Mutable[0] = 0
        log.info("numbers_Mutable[0] = 0: ${numbers_Mutable[0]}")
        numbers_Mutable.shuffle()
        log.info("numbers_Mutable.shuffle(): $numbers_Mutable")

        log.info("================================================================================================")

        //Array
        var intArray = IntArray(10)
        var longArray = LongArray(10)
        var floatArray = FloatArray(10)
        var doubleArray = DoubleArray(10)
        var charArray = CharArray(10)

        var _stringArray = Array(10) { "" } // String은 기본 type 이 아니기 때문에 따로 선언 해야함 -> 사이즈가 10이고 각 자리에 공백이 들어가 있음
        _stringArray[0] = "aaa"
        _stringArray[3] = "ddd"
        log.info("_stringArray[0]: ${_stringArray[0]}")
        log.info("_stringArray[1]: ${_stringArray[1]}")
        log.info("_stringArray[0]: ${_stringArray[3]}")

        var stringArray = arrayOf("AA", "BB", "CC", "DD")

        // getter
        log.info("stringArray[2]: ${stringArray[2]}")
        log.info("stringArray.get(3): ${stringArray.get(3)}")

        // setter
        stringArray.set(1, "EE")
        stringArray[3] = "GG"
        stringArray.forEach { v -> log.info("value: $v") }

        log.info("================================================================================================")

        //Map - Immutable
        val numbersMap_Immutable = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
        if ("key2" in numbersMap_Immutable)
            if (1 in numbersMap_Immutable.values)
                log.info("numbersMap_Immutable.containsValue(1): ${numbersMap_Immutable.containsValue(1)}")
        //Map - Mutable
        val numbersMap_Mutable = mutableMapOf("one" to 1, "two" to 2)
        numbersMap_Mutable.put("three", 3)
        numbersMap_Mutable["one"] = 11
        log.info("numbersMap_Mutable: $numbersMap_Mutable")
        numbersMap_Mutable.remove("one")
        log.info("numbersMap_Mutable.remove(\"one\"): $numbersMap_Mutable")

        log.info("================================================================================================")

        //Set - Immutable
        val numbersSet_Immutable = setOf<Int>(1, 2, 3, 4, 5, 6)
        log.info("numbersSet_Immutable.size: ${numbersSet_Immutable.size}")
        log.info("numbersSet_Immutable.contains(1): ${numbersSet_Immutable.contains(1)}")
        log.info("numbersSet_Immutable.isEmpty(): ${numbersSet_Immutable.isEmpty()}")

        //Set - Mutable
        val numbersSet_Mutable = mutableSetOf<Int>(1, 2, 3, 4, 5, 6)
        numbersSet_Mutable.add(10)
        log.info("numbersSet_Mutable.add(10): $numbersSet_Mutable")
        numbersSet_Mutable.remove(4)
        log.info("numbersSet_Mutable.remove(4): $numbersSet_Mutable")
        numbersSet_Mutable.removeIf({ it < 3 })
        log.info("numbersSet_Mutable.removeIf({ it < 3 }): $numbersSet_Mutable")

    }

    @Test
    fun `Destructuring Declarations example`() {
        val ex1_pair = Pair("Gold", 1)
        println("ex1_pair: $ex1_pair")
        val pair = "Gold" to 1
        val(_, _) = pair
        println("${pair.first}, ${pair.second}")
        println("pair: $pair")

        println("======================================================================")

        val fruitBasket = listOf("Apple", "Banana", "Cherry", "Durian")
        println("fruitBasket: $fruitBasket")
        val (first, second, third, fourth) = fruitBasket

        println("$first, $second, $third, $fourth")

    }

    @Test
    fun `컬렉션 사용`() {
       val fruits = listOf(
           Fruit("banana", 12),
           Fruit("apple", 10),
           Fruit("orange", 9),
           Fruit("pineapple", 10),
           Fruit("peach", 8),
           Fruit("lemon", 2),
           Fruit("mango", 13),
       ).sortedBy(Fruit::sugar) //정렬 sugar

        println("fruits: $fruits")
        println("앞에서 n개 가져오기: ${fruits.take(2)}")
        println("뒤에서 n개 가져오기: ${fruits.takeLast(3)}")
        println("앞에서 n개 제외한요소(삭제가아님)를 사용: ${fruits.drop(2)}")
        println("뒤에서 n개 제외한요소(삭제가아님)를 사용: ${fruits.dropLast(3)}")
        println("fruits: $fruits")

        println("======================================================================")

        //파티션 기능
        /**
         * 첫번째는 술어가 true 로 평가되는 모든 요소를, 두번째는 다른 모든 요소를 포함한다.
         */
        val (sweet, superSweet) = fruits.partition { it.sugar < 10 }
        println("sweet: $sweet") // sugar < 10
        println("superSweet: $superSweet") // 그 외 모든 요소

        println("======================================================================")

        //joinToString 확장
        //iterable 을 문자열로 변환하기 위한 옵션 제공
        println("역순으로 문자열 ${
            fruits.reversed().joinToString (
                separator = " + ",
                prefix = "🤗 = [",
                postfix = "]",
                limit = 3,
                truncated = "MORE"
            ){ it.name}    
        }")

        println("역순으로 문자열 lamda ${
            fruits.reversed().joinToString(
                separator = " + ", prefix = "🤗 = [", postfix = "]", limit = 3,
                truncated = "MORE", transform = Fruit::name)
        }")
    }
}

data class Fruit(val name: String, val sugar: Int)

