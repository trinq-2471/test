package com.example.testkotlin

import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// region generic


//class Person<out T>(val value: T) {
//    fun get(): T {
//        return value
//    }
//}

class Person<in T>() {
    fun say(value : T){
        println("${value.hashCode()}")
    }
}

open class Father(){
    open fun talk() : String{
        return "i am father class"
    }
}

class Son() : Father() {
    override fun talk(): String {
        return "i am son class"
    }
}

// endregion

fun main() {
    val fatherObject: Person<Father> = Person()
    val sonObject : Person<Son> = fatherObject
}


//    var son = Son()
//    var sonObject: Person<Son> = Person(son)
//    var fatherObject: Person<Father> = sonObject
//
//    println(fatherObject.get().talk())      // i am son class
