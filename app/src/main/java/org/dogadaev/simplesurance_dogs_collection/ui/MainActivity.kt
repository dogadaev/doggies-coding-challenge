package org.dogadaev.simplesurance_dogs_collection.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.dogadaev.simplesurance_dogs_collection.ui.navigation.Destinations
import org.dogadaev.simplesurance_dogs_collection.ui.navigation.NavGraph
import org.dogadaev.simplesurance_dogs_collection.ui.navigation.NavigationActions
import org.dogadaev.simplesurance_dogs_collection.ui.theme.SimplesuranceDogsCollection

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimplesuranceDogsCollection {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navigationActions = remember(navController) {
                        NavigationActions(navController)
                    }

                    val backStackEntry = navController.currentBackStackEntryAsState()

                    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                        rememberTopAppBarState()
                    )

                    Scaffold(
                        modifier = Modifier
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(text = "Doggies")
                                },
                                scrollBehavior = scrollBehavior,
                            )
                        },
                        bottomBar = {
                            BottomAppBar {
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == Destinations.BreedsList,
                                    onClick = {
                                        navigationActions.navigateToBreedsList()
                                    },
                                    label = { Text(text = "Browse") },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.List,
                                            contentDescription = ""
                                        )
                                    },
                                )

                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == Destinations.Favorites,
                                    onClick = {
                                        navigationActions.navigateToFavorites()
                                    },
                                    label = { Text(text = "Favorite") },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = ""
                                        )
                                    },
                                )
                            }
                        },
                    ) { contentPadding ->
                        NavGraph(
                            modifier = Modifier.padding(contentPadding),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}
