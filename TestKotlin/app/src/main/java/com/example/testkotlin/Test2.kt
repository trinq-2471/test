package com.example.testkotlin

import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// region generic

open class Father()

class Son() : Father()

class Person<out T>(val value: T) {
    fun get(): T {
        return value
    }
}

//class Person<in T>() {
//    fun say(value : T){
//        println("${value.hashCode()}")
//    }
//}

// endregion

fun main() {

    val sonObject: Person<Son> = Person(Son())
    val fatherObject: Person<Father>
    fatherObject = sonObject

//        val fatherObject: Person<Father> = Person()
//        val sonObject: Person<Son>
//        sonObject = fatherObject
}




