package io.ilyasin.discountex.ui.details_screen

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import io.ilyasin.discountex.R
import io.ilyasin.discountex.ui.common.FeedEntry
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test


class DetailsScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailsNoLinkTest() {
        val dummyTime = "21/05/2024 13:02"
        composeTestRule.setContent {
            DetailsScreenContent(navController = rememberNavController(), mutableStateOf(dummyTime), feedEntry = null)
        }

        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        val myNameStr = context.resources.getString(R.string.my_name_is)
        val openNews = context.resources.getString(R.string.show_news_feed)

        val myNameText = composeTestRule.onNodeWithText(myNameStr)
        assertTrue(myNameText.isDisplayed())

        val openNewsBtn = composeTestRule.onNodeWithText(openNews)
        assertTrue(openNewsBtn.isDisplayed())

        val timeText = composeTestRule.onNodeWithText(dummyTime)
        assertTrue(timeText.isDisplayed())

        val linkText = composeTestRule.onNodeWithTag("link_label")
        assertFalse(linkText.isDisplayed())
    }

    @Test
    fun detailsWithLinkTest() {
        val dummyTime = "21/05/2024 13:03"
        val title = "Some dummy title"
        composeTestRule.setContent {
            DetailsScreenContent(
                navController = rememberNavController(),
                mutableStateOf(dummyTime),
                feedEntry = FeedEntry(title, "https://www.google.com")
            )
        }

        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        val myNameStr = context.resources.getString(R.string.my_name_is)
        val openNews = context.resources.getString(R.string.show_news_feed)

        val myNameText = composeTestRule.onNodeWithText(myNameStr)
        assertTrue(myNameText.isDisplayed())

        val openNewsBtn = composeTestRule.onNodeWithText(openNews)
        assertTrue(openNewsBtn.isDisplayed())

        val timeText = composeTestRule.onNodeWithText(dummyTime)
        assertTrue(timeText.isDisplayed())

        val linkText = composeTestRule.onNodeWithTag("link_label")
        assertTrue(linkText.isDisplayed())
        linkText.assertTextEquals(title)
    }
}
