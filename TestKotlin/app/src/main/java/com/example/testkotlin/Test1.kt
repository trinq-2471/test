package com.example.testkotlin

import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

//region test

//        var a : String = "sad"
//        a = "eqwe"
//
//        val b : String = "ddd"
//
//        // can't not
//        b = "d"

//        // Add ?  to can set a = null
//        var a : String? = "ee"
//        a = null
//
//        // Add !!  to can set b not null
//        var b : String = "pp"!!
//        b = null   "error"

//        var a : Int = 1
//        if (a == 1) {
//           Log.d("AAA","AAAA");
//        }
//        else{
//            Log.d("AAA","BBB")
//        }

//        var x : Int = 1
//        var arrayNum = arrayListOf<Int>(4,5,6)
//        when(x){
//            1 -> print("x = 1")
//            2,3 -> print("x = 2 or 3")
//            in arrayNum -> print("x = 4,5,6")
//            in 7..10 -> print("x = 7,8,9,10")
//            !in 11..15 -> print("")
//            else -> {
//                print("x > 15")
//            }
//        }
//
//        var s : String = "123"
//        when(x){
//            Integer.parseInt(s) -> print("ss")
//            else -> {
//                print("3123")
//            }
//        }

//        var collection = arrayListOf<Int>(1,2,3,4)
//        for (item in collection){
//            println(item)
//        }
//
//        for (item: Int in collection) {
//            println(item)
//        }
//
//        for (i in 1..3) {
//            println(i)
//        }

//        for (i in 6 downTo 0 step 2) {
//            Toast.makeText(this,"text" + i,Toast.LENGTH_SHORT).show()
//        }

//        var arrays = arrayListOf(1,2,3,4,5,6)
//        for (i in arrays.indices){
//            Toast.makeText(this,"text" + i,Toast.LENGTH_SHORT).show()
//        }
//
//        var x = 0;
//        while (x > 0) {
//            x--
//        }
//
//        do {
//            x ++
//            println(x)
//        } while (x <5)

//        var array = arrayListOf<String>("dd","dds")
//        var arrayNum : IntArray = intArrayOf(1,2,4,5)
//        var StringArr : List<String> = listOf("zz","vv")

//endregion
//region Nested, inner class, data class

//class Fruit{
//    private val apple : String = "apple";
//
//    class Nested{
//        fun eat(){
//            println("eat")
//        }
//    }
//    inner class Inner{
//        fun eat(){
//            println("eat ${apple}")
//        }
//    }
//}

data class Customers( val name : String, val age : Int){

}
// Tra ve nhieu gia tri ta co the su dung data class
fun calculate() : Customers{
    return Customers("tri",26)
}

//endregion
// region enum

//enum class laBan(){
//    EAST,
//    WEST,
//    SOUTH,
//    NORTH
//}
//
//enum class Color(var red:Int,var green:Int){
//    RED(123,33),
//    GREEN(44,55),
//    BLACK(33,55)
//}

//enum class ProtocolState {
//    WAITING {
//        override fun signal() = TALKING
//    },
//    TALKING {
//        override fun signal() = WAITING
//    };
//    abstract fun signal(): ProtocolState
//}

// endregion
//region delegation

//interface Bird{
//    fun flyAndFindFood()
//}
//
//class Eagle : Bird {
//    override fun flyAndFindFood() {
//        println("I am Eagle, i go to find food")
//    }
//}
//class Cuckkoo(b : Bird) : Bird by b {
//    override fun flyAndFindFood() {
//    println("I am Cuckkoo, i go to find food")
//    }
//}

//endregion
//region delegation pro

//class User1{
//    var name : String by DelegatedUser()
//}
//
//class DelegatedUser{
//    operator fun getValue(thisRef : Any?,property : KProperty<*>) : String {
//        return " ${thisRef}, thank for delegating ${property.name}"
//    }
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value : String){
//        println("assign to ${property.name} in ${thisRef}. Detail :${value}")
//    }
//}

//endregion
//region lamda

//var sum = {x:Int,y:Int -> x+y}
//var mimus : (Int,Int) -> Int ={x,y -> x-y}
//
////Anomymous Fun
//var div = fun(x:Double,y:Double) : Double{
//    return x/y
//}

//endregion
//region Observable and map

//Observable
//class User2(name : String){
//    var currentAddress : String by Delegates.observable("No Address"){
//        property, oldValue, newValue ->  run {
//            println("${property.name} change")
//            println("From ${oldValue} To ${newValue}")
//        }
//    }
//}

// map
//var user2Obj = mutableMapOf<String,Any?>("name" to "Tri","age" to 26)
//
//class Customer(val mapObj : Map<String,Any?>){
//    val name : String by mapObj
//    val age : Int by mapObj
//    val className : String by mapObj
//}

//endregion
//region Extention

// Extention IntArray and MutableList<Cat>

//fun IntArray.swapIndex(index1 : Int, index2 : Int){
//    val temp : Int = this[index1]
//    this[index1] = this[index2]
//    this[index2] = temp
//}
//
//fun MutableList<Cat>.swapIndex(i1 : Int,i2 : Int){
//    val temp : Cat = this[i1]
//    this[i1] = this[i2]
//    this[i2] = temp
//}

//class Calculation{
//    // giong staic trong java
//    companion object{
//        fun PI() : Float = 3.123F
//    }
//}
//
//// Extention companion
//fun Calculation.Companion.doubePI() : Float{
//    return Calculation.Companion.PI() * 2
//}

//class A{
//    fun menthodA(){
//        println("menthod A of class A")
//    }
//    fun menthodX(){
//        println("mendthod X of class A")
//    }
//    fun B.menthodB2(){
//        menthodA()
//        this.menthodB()
//        this@A.menthodX()
//    }
//    fun callB2FromA( b : B){
//        b.menthodB2()
//    }
//}
//
//class B{
//    fun menthodB(){
//        println("menthod B of class B")
//    }
//    fun menthodX(){
//        println("mendthod X of class B")
//    }
//}


//endregion
//region Higher-Order Functions

//val compare : (Int,Int) -> Boolean = {x,y -> x < y}
//
//fun getMaxValue(list : MutableList<Int>,less : (Int,Int) -> Boolean) : Int?{
//    var maxValue : Int = 0
//    for (value in list){
//        if (less(maxValue,value) )
//        {
//            maxValue = value
//        }
//    }
//
//    return maxValue
//}

//endregion
//region Generics

//class GenericsExample<T>(input: T) {
//    init {
//        println("Giá trị là: " + input)
//    }
//}

//endregion

fun main(){

//region data class

//    var (custName,ageName) = calculate()
//    println("${custName} ${ageName}")

//    var userObj = mapOf<String, Any?>("name" to "tri","age" to 26,"male" to true,"address" to "Hue")
//    for ((key,value) in userObj){
//        println("${key} ${value}")
//    }

//endregion
// region enum

//    print("Huong ${laBan.EAST}")
//    for (color in Color.values()){
//        println("mau ${color} - vi tri ${color.ordinal}")
//    }

//    println(ProtocolState.WAITING)
//    println(ProtocolState.TALKING)

// endregion
//region delegation
//val eagle = Eagle();
//val cuckkoo = Cuckkoo(eagle)
//cuckkoo.flyAndFindFood()
//endregion
//region delegation pro

//var user1 : User1 = User1()
//user1.name = "tri"
//println(user1.name)

//endregion
//region lamda

//    println("${sum(3,5)}")
//    println("${mimus(93,5)}")
//    println("${div(10.0,2.5)}")

//    var arr = doubleArrayOf(10.0,3.0,5.0,7.8)
//    arr.forEach { num -> println("${num + 1}") }

    //endregion
//region Observable and map

//Observable
//    val user2 : User2 = User2("Tri")
//    user2.currentAddress = "Hue"
//    user2.currentAddress = "DN"

//map
//    println("detail : ${user2Obj.getValue("name")} age : ${user2Obj.getValue("age")}")
//    user2Obj.set("age",27)
//    println("detail : ${user2Obj.getValue("name")} age : ${user2Obj.getValue("age")}")

//    val customer : Customer = Customer(mapOf("name" to "tri","age" to 15,"className" to "A2"))
//    println("Detail ${customer.name} age ${customer.age} class ${customer.className}")

//endregion
//region Extention

//    val listNum : IntArray  = intArrayOf(1,2,3,4,5,6)
//    listNum.swapIndex(4,5)
//    for(item in listNum){
//        println("${item}")
//    }

//    var cat1 : Cat = Cat("cat1",1)
//    var cat2 : Cat = Cat("cat2",2)
//    var cat3 : Cat = Cat("cat3",3)
//    var cat4 : Cat = Cat("cat4",4)
//    val listOfCat = mutableListOf(cat1,cat2,cat3,cat4)
//    listOfCat.swapIndex(1,2)
//    for(cat in listOfCat){
//        println("name: ${cat.name} age: ${cat.age}")
//    }

// Extention companion
//    println("${Calculation.PI()}")
//    println("${Calculation.doubePI()}")

//    val a = A()
//    val b = B()
//    a.callB2FromA(b)

//endregion
//region Higher-Order Functions

//    var list : MutableList<Int> = mutableListOf(3,12,6,22,9,4)
//    var maxValue = getMaxValue(list, compare)
//    println("${maxValue}")

//endregion
//region Generics

//    var obj1 = GenericsExample<String>("JAVA")
//    var obj2 = GenericsExample<Int>(20)

//endregion


//region test

//endregion
}