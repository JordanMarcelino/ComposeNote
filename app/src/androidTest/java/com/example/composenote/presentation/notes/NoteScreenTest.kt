package com.example.composenote.presentation.notes

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenote.di.AppModule
import com.example.composenote.presentation.MainActivity
import com.example.composenote.presentation.util.Screen
import com.example.composenote.ui.theme.ComposeNoteTheme
import com.example.composenote.util.TestTag.SORT_BUTTON
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NoteScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            ComposeNoteTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.NotesScreen.route){
                    composable(route = Screen.NotesScreen.route){
                        NotesScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun performClick_orderSection_isDisplayed() {
        composeRule.onNodeWithTag(SORT_BUTTON).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(SORT_BUTTON).assertIsDisplayed()
    }
}