package com.example.jetpackcomposedemo.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposedemo.CartViewModel
import com.example.jetpackcomposedemo.R
import com.example.jetpackcomposedemo.data.Flowers

@Composable
fun showcart(
    cartViewModel: CartViewModel = viewModel()
){

    val UiState by cartViewModel.uiState.collectAsState()

    Text(
        text = "Cart Value=$${UiState.total}.00",
        modifier=Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.subtitle2.copy(color = colorPrimary),
        textAlign = TextAlign.Center
    )
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
    ) {
        val key_flower:MutableSet<Flowers> =UiState.cartMap.keys
        Log.d(TAG,key_flower.toString())
        items(key_flower.size) {
            if(UiState.cartMap[key_flower.elementAt(it)] !=0){
                UiState.cartMap[key_flower.elementAt(it)]?.let { it1 ->
                    FlowerCard(flower = key_flower.elementAt(it),
                        it1
                    )
                }

            }

        }
    }

}

@Composable
private fun FlowerCard(flower: Flowers,count:Int) {
            Card(
                shape = RoundedCornerShape(14.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .padding(10.dp).fillMaxWidth().height(100.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {

                    Image(
                        modifier = Modifier.size(140.dp),
                        bitmap = ImageBitmap.imageResource(id = flower.image),
                        contentDescription = "flower_card"
                    )
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text ="$count X "+flower.name,
                                style = TextStyle(
                                    color = gray,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                                )
                            )
                            Text(
                                text ="$count X ${flower.price} = $ ${count*(flower.price.removePrefix("$").removeSuffix(".00").toInt())}.00 ",
                                style = TextStyle(
                                    color = colorPrimary,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(
                                    color = colorPrimary,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                        }
                    }
                }
        }



}

private fun times(first: String,second: String): String {

    return  (first.toInt()*second.toInt()).toString();
}
