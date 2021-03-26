package com.example.testkotlin

class Student<T>(val data: T){
    fun showData(){
        println(data)
    }
}

//class Student<T>( val data : T){
//    fun showData(){
//        when{
//            data is String -> println("type of data is String")
//            data is Int -> println("type of data is Int")
//            data is Boolean -> println("type of data is Boolean")
//            else -> println("type of data is not String,Int,Boolean ")
//        }
//    }
//}

fun main(){

    val studentA = Student("tri")
    val studentB = Student(12)
    studentA.showData()
    studentB.showData()


//    val studentA = Student("tri")
//    val studentB = Student(18)
//    val studentC = Student(12.3)
//    studentA.showData()     // type of data is String
//    studentB.showData()     // type of data is Int
//    studentC.showData()     // type of data is not String,Int,Boolean
}