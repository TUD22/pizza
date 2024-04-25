package com.example.listapizz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listapizz.ui.theme.ListaPizzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaPizzTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListaPizz()
                }
            }
        }
    }
}

@Composable
fun ListaPizz(){
    val myList = remember { mutableStateListOf<Pizza>() }
    var pizzaSize by remember {
        mutableStateOf("")
    }
    var nameState by remember {
        mutableStateOf("")
    }
    var priceText by remember {
        mutableStateOf("")
    }
    var isExpended by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(value = "pizza", onValueChange = {}, label = { Text(text = "podaj nazwę pizzy") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(value = "cena", onValueChange = {}, label = { Text(text = "podaj cenę pizzy") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box{
            Button(onClick = { isExpended=true }) {
                Text(text = "Wielkość pizzy")
            }
            DropdownMenu(expanded = isExpended, onDismissRequest = { isExpended=true}) {
                DropdownMenuItem(text = {Text(text = "mała")}, onClick = {
                    pizzaSize = "mała"
                })
                DropdownMenuItem(text = {Text(text = "duża")}, onClick = {
                    pizzaSize = "duża"
                })
                
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {}) {
            if(nameState.isNotEmpty()){
                myList.add(Pizza(name=nameState, priceText.toDouble(), size=pizzaSize))
                nameState=""
                pizzaSize=""
                priceText=""
            }
            Text(text = "dodaj")
            
        }
        if(myList.isEmpty()){
            Text(text = "pusta lista",
            style=MaterialTheme.typography.titleMedium)
        }
        else{
            LazyColumn {
                items(myList){item=>
                    Text(text = "${item.name}, ${item.price}PLN, ${item.size}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListaPizzTheme {

    }
}