package com.example.testkotlin


class ArrayUtil<T> ( val mArray: Array<T> ){
    fun findIndexOfElement( element: T ){
        for ( index in mArray.indices ) {
            if ( mArray[index] == element ){
                println("Index of element ${element} is ${index}")
            }
        }
    }
}

fun main(){
    val arrayString = ArrayUtil(arrayOf("Android","Kotlin","PHP","C#"))
    val arrayInt = ArrayUtil(arrayOf(1,2,3,4,5))

    arrayString.findIndexOfElement("Kotlin")    // Index of element Kotlin is 1
    arrayInt.findIndexOfElement(3)              // Index of element 3 is 2
}