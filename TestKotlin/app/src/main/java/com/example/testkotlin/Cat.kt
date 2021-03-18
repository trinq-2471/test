package com.example.testkotlin

data class Cat(val name : String,val age : Int, val address : String) {
    constructor(name1 : String) : this (name1,1,"hue")
}