package com.example.navigationlesson_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationlesson_2.ui.theme.Greeting
import com.example.navigationlesson_2.ui.theme.NavigationLesson2Theme
import com.example.navigationlesson_2.ui.theme.SecondScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigation() {
                        val navBackStateEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStateEntry?.destination
                        BottomNavigationItem(
                            selected =
                            currentDestination?.hierarchy?.any { it.route == "firstlesson" } == true,
                            onClick = { navController.navigate("firstlesson") },
                            icon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                            label = { Text(text = "First") }
                        )
                        BottomNavigationItem(
                            selected =
                            currentDestination?.hierarchy?.any { it.route == "secondlesson" } == true,
                            onClick = { navController.navigate("secondlesson") },
                            icon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                            label = { Text(text = "First") }
                        )
                    }
                },
                floatingActionButton = {

                    var expanded by rememberSaveable { mutableStateOf(false) }

                    FloatingActionButton(onClick = {expanded = !expanded}) {
                        Row() {
                            Icon(Icons.Filled.Call, contentDescription = null)
                            AnimatedVisibility(expanded) {
                                Text(text = "Hello", Modifier.padding(start = 8.dp))
                            }

                        }
                    }
                }

            ) {
                NavigationLesson2Theme {
                    NavHost(navController = navController, startDestination = "") {
                        composable("firstlesson") {
                            Greeting(
                                name = "firstlesson",
                                { navController.navigate("secondlesson") })
                        }
                        composable("secondlesson") {
                            SecondScreen(name = "second")
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationLesson2Theme {
        Greeting("Android") {}
    }
}