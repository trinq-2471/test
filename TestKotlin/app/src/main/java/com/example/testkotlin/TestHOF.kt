package com.example.testkotlin

//region example

// example 1
//fun calculation( x: Int, y : Int, funCalculation : (Int, Int) ->  Int) : Int {
//    var result : Int = funCalculation(x, y)
//    return result
//}
//
//var sum =  fun (x: Int,y: Int) : Int {
//    return x + y
//}

// example 2
//fun calculateSum() : ((Int,Int) -> Int){
//    return ::sum
//}
//
//fun sum (x: Int,y: Int) : Int {
//    return x + y
//}

//endregion

val lessFunction = fun( x: Int, y: Int ) : Boolean {
    return x < y
}

fun getMaxValue( list : MutableList<Int>, compareFunction : (Int,Int) -> Boolean ) : Int? {
    var maxValue : Int? = null
    for (value in list){
        if ( maxValue == null || compareFunction( maxValue, value ) )
        {
            maxValue = value
        }
    }
    return maxValue
}

fun main(){
    var myList = mutableListOf<Int>(7,2,12,10,23)
    println("Max value is ${getMaxValue(myList, lessFunction)}")
    // Max value is 23
}

//region example

// example 1
//    println(calculation(10,12, sum ))   // 22

// example 2
//    var sumResult = calculateSum()
//    println(sumResult(1,2))         // 3


//endregion