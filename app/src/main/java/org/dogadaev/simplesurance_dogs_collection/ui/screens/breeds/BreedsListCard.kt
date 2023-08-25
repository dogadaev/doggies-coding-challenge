package org.dogadaev.simplesurance_dogs_collection.ui.screens.breeds

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BreedsListItem(
    breedName: String,
    onFavoriteClick: () -> Unit,
    onCardClick: () -> Unit,
) {
    val shape = RoundedCornerShape(16.dp)
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(vertical = 8.dp),
    ) {

        BreedsListFavoriteButton(
            shape = shape,
            onClick = onFavoriteClick
        )

        Spacer(
            modifier = Modifier
                .width(16.dp)
        )

        BreedsListCard(
            breedName = breedName,
            shape = shape,
            onClick = onCardClick
        )
    }
}

@Composable
fun BreedsListCard(
    breedName: String,
    shape: Shape,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                2.dp, DividerDefaults.color, shape
            )
            .clip(shape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = breedName
                .replaceFirstChar(Char::uppercaseChar),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BreedsListFavoriteButton(
    shape: Shape,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .border(
                2.dp, DividerDefaults.color, shape
            )
            .clip(shape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "",
        )
    }
}