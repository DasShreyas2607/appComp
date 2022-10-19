package com.example.jetpackcomposedemo.data

data class OrderUiState (
    val cartMap:MutableMap<Flowers,Int> = mutableMapOf(),
    val total:Int=0

){

}