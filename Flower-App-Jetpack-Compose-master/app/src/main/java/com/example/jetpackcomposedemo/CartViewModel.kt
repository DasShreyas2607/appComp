package com.example.jetpackcomposedemo

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposedemo.data.Flowers
import com.example.jetpackcomposedemo.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

open class CartViewModel : ViewModel() {
    private val _uistate = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uistate.asStateFlow()

    fun update(flower: Flowers) {
        _uistate.update { currentState ->
            val toaddmap = currentState.cartMap;
            val totalPrev=_uistate.value.total

            if(toaddmap.containsKey(flower)){

                val cur: Int = toaddmap.get(flower)!!

                toaddmap.put(flower, cur+1);
                currentState.copy(cartMap = toaddmap, total = totalPrev+flower.price.removePrefix("$").removeSuffix(".00").toInt() )

            }else{
                toaddmap.put(flower, 1);
                currentState.copy(cartMap = toaddmap,total = totalPrev+flower.price.removePrefix("$").removeSuffix(".00").toInt())

            }
        }
    }
    fun get_total():Int{
        return _uistate.value.cartMap.values.sum()

    }
}


