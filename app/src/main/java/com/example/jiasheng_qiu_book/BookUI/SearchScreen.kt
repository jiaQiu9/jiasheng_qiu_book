package com.example.jiasheng_qiu_book.BookUI

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
//import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.jiasheng_qiu_book.R
import com.example.jiasheng_qiu_book.network.BookItem
import com.example.jiasheng_qiu_book.viewmodel.BookViewModel

@Composable
fun SearchScreen(viewModel: BookViewModel, onBookClick: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier= Modifier.height(5.dp))
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            placeholder = { Text("Search for books...") }
        )

        Button(onClick = { viewModel.searchBooks(query) }) {
            Text("Search")
        }

        val books by viewModel.books.collectAsState()

        LazyColumn {
            items(books) { book ->
                BookItem(book, onBookClick)
            }
        }
    }
}

@Composable
fun BookItem(book: BookItem, onBookClick: (String) -> Unit) {
    val imageUrl = book.volumeInfo.imageLinks?.smallThumbnail

    Log.d("BookItem", "Image URL: $imageUrl")  // Log the image URL

    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onBookClick(book.id.toString()) }
            .padding(8.dp)
    ) {
        // Display the book cover image
//        imageUrl?.let {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(it)
//                    .build(),
//                contentDescription = "Book Cover",
//                modifier = Modifier
//                    .size(80.dp)
//                    .padding(end = 16.dp),
//                contentScale = ContentScale.Crop
//            )
//        }
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(end = 16.dp),
        )
//        Image( // The Image component to load the image with the Coil library
//            painter = rememberAsyncImagePainter(model = imageUrl),
//            contentDescription = null,
//            modifier = Modifier.size(80.dp, 16.dp)
//        )


        Spacer(Modifier.width(16.dp))
        Column {
            Text(book.volumeInfo.title)
            Text(book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author")
        }
    }
}