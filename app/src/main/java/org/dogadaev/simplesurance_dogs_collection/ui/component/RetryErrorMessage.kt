package org.dogadaev.simplesurance_dogs_collection.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RetryErrorMessage(
    modifier: Modifier = Modifier,
    errorMessage: String,
    retryButtonTitle: String,
    contentDescription: String? = null,
    retry: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp),
            text = errorMessage,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
        )
        Button(
            onClick = retry,
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = contentDescription,
            )
            Text(text = retryButtonTitle)
        }
    }
}