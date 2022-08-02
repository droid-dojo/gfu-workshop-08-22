package com.example.gfu.workshop.user.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.gfu.backend.User
import com.example.gfu.workshop.ui.theme.spacings

@Composable
fun UserDataRow(user: User, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings)
    ) {
        Avatar()

        Column {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.testTag(TestContract.NameIdentifier)
            )
            Text(text = user.location, style = MaterialTheme.typography.caption)
        }

    }
}

object TestContract {
    const val NameIdentifier = "User Name"
}
