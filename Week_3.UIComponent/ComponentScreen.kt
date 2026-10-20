package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


data class ComponentItem(val title: String, val desc: String, val route: String)

val displayItems = listOf(
    ComponentItem("Text","Displays text","textDetail"),
    ComponentItem("Image","Displays an image","imageDetail"),
)

val inputItems = listOf(
    ComponentItem("TextField","Input field for text","textFieldDetail"),
    ComponentItem("PasswordField","Input field for passwords", "passwordFieldDetail"),
    ComponentItem("Toggle","On/off switch","toggleDetail"),
    ComponentItem("Slider","Selects a value from a range","sliderDetail"),
    ComponentItem("DatePicker","Selects a date of time","datePickerDetail")
)

val layoutItems = listOf(
    ComponentItem("Column","Input field for passwords", "columnDetail" ),
    ComponentItem("Row","Arranges elements horizontally", "rowDetail" )
)

val selectionItems = listOf(
    ComponentItem("Button","Triggers an action when tapped","buttonDetail"),
    ComponentItem("Checkbox","Selects one or multiple options","checkboxDetail"),
    ComponentItem("RadioButton","Selects a single option","radioButtonDetail"),
    ComponentItem("Picker","Selects a value from list","pickerDetail"),
)

@Composable
fun itemCard(item: ComponentItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFC5DDF5))
            .clickable { onClick() }
            .padding(12.dp)
    ){
        Text(item.title, fontWeight = FontWeight.Bold)
        Text(item.desc)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentScreen(onBackClick: () -> Unit, onItemClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("UI Components List") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
        ){
            Text("Display", fontWeight = FontWeight.Bold)
            displayItems.forEach { item ->
                itemCard(item, onClick = { onItemClick(item.route) })
            }
            Text("Input", fontWeight = FontWeight.Bold)
            inputItems.forEach { item ->
                itemCard(item, onClick = { onItemClick(item.route)})
            }
            Text("Layout", fontWeight = FontWeight.Bold)
            layoutItems.forEach { item ->
                itemCard(item, onClick = { onItemClick(item.route)})
            }
            Text("Selection", fontWeight = FontWeight.Bold)
            selectionItems.forEach { item ->
                itemCard(item, onClick = { onItemClick(item.route)})
            }
        }
    }
}