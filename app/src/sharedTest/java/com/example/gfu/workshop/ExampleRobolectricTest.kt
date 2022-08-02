package com.example.gfu.workshop

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gfu.backend.User
import com.example.gfu.backend.posts.data.Post
import com.example.gfu.workshop.posts.ui.PostCard
import com.example.gfu.workshop.user.ui.TestContract
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleRobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    private val given: Post =
        Post(author = User(name = "Beispiel Name", "Buxtehude"), imageUrl = "")


    @Test
    fun postsCanBeDisplayed() {
        composeTestRule.setContent {
            PostCard(
                post = given,
                liked = true,
                onLiked = {}
            )
        }

        composeTestRule.onNodeWithText(given.author.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(given.author.location).assertIsDisplayed()

    }


    @Test
    fun locationIsDisplayedAtCorrectElement() {
        composeTestRule.setContent {
            PostCard(
                post = given,
                liked = true,
                onLiked = {}
            )
        }

        composeTestRule.onNodeWithTag(TestContract.NameIdentifier)
            .assertTextEquals(given.author.name)

    }


    @Test
    fun clickingLikeInvokesGivenCallback() {
        var clicked = false

        composeTestRule.setContent {
            PostCard(
                post = given,
                liked = true,
                onLiked = {
                    clicked = true
                }
            )
        }

        composeTestRule
            .onNode(hasContentDescription("Like"))
            .performClick()

        assertTrue(clicked)
    }
}