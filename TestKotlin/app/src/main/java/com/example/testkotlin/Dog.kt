package com.example.testkotlin

class Dog(name : String,age: Int) : Animal() {

//    constructor(name : String,age: Int){
//        this.name = name
//        this.age = age
//    }

    override fun eat() {
        super.eat()
    }

    private var name : String = ""
    private var age : Int = 18

    init {
        this.name = name
        this.age = age
    }

    fun getName(): String {
        return this.name;
    }
    fun setName( name : String){
        this.name = name
    }
    fun getAge() : Int{
        return this.age
    }
    fun setAge( age : Int){
        this.age = age
    }
}