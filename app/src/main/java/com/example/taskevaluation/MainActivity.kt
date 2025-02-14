@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskevaluation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskevaluation.ui.theme.TaskEvaluationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataManager.loadFileFromAssets(this)
        setContent {

            TaskEvaluationTheme {
                // A surface container using the 'background' color from the theme

                MyAppTheme(children = {
                    Surface(
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(topBar = {
                            CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,

                                ), title = {
                                Text(
                                    text = "CityList",
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            })

                        }) {}


                        App()
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun App() {
var checked=true
    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Switch(checked = checked, onCheckedChange = {
            if(checked)
            ThemeState.isLight = !ThemeState.isLight
            else
                ThemeState.isLight=ThemeState.isLight

        })
        Column {
            if (DataManager.isDataLoaded.value) {
                CityList(DataManager.data)

            }
        }

    }
}

@Composable
fun MyAppTheme(
    children: @Composable() () -> Unit
) {

    if (ThemeState.isLight) {
        MaterialTheme(lightColorScheme()) {
            children()
        }
    } else {
        MaterialTheme(darkColorScheme()) {
            children()
        }

    }
}

object ThemeState {
    var isLight by mutableStateOf(true)
}


