package com.example.cheng.myapplication.kotlin

/**
 * Created by chengbiao on 2018/1/5.
 */
open class Animal {

}

class Dog : Animal()


object Main {
    fun Animal.bark() = "animal"

    fun Dog.bark() = "dog"

    fun Animal.printBark(anim: Animal) {
        println(anim.bark())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Animal().printBark(Dog())
    }
}