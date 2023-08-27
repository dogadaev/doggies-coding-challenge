package org.dogadaev.simplesurance_dogs_collection.ui.screens.breeds

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed

@Composable
fun BreedsList(
    items: List<Breed>,
    onFavoriteClick: (breed: Breed) -> Unit,
    onCardClick: (breed: Breed) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 16.dp, vertical = 8.dp
        ),
    ) {
        items(
            items = items,
        ) { breed ->
            BreedsListItem(
                breed = breed,
                onCardClick = { onCardClick(breed) },
                onFavoriteClick = { onFavoriteClick(breed) }
            )
        }
    }
}