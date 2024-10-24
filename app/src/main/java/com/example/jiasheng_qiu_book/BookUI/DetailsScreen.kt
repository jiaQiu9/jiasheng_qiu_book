package com.example.jiasheng_qiu_book.BookUI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jiasheng_qiu_book.network.BookItem
import com.example.jiasheng_qiu_book.viewmodel.BookViewModel
import org.jsoup.Jsoup

@Composable
fun DetailsScreen(viewModel: BookViewModel, title: String) {
    // State to hold the book details
    var bookItem by remember { mutableStateOf<BookItem?>(null) }

    // Effect to load book details when the title changes
    LaunchedEffect(title) {
        viewModel.getBookDetails(title) { bookDetails ->
            bookItem = bookDetails
        }
    }

    val windowInfo = calculateCurrentWindowInfo() //get the screen width, height, and orientation
    if (windowInfo.orientation == Orientation.PORTRAIT) {
        //PortraitLayout(windowInfo)
        // Layout to display book details
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            bookItem?.let { book ->
                Column(modifier = Modifier.padding(16.dp)) {

                    Row{
                        Text(text = "Title: ${book.volumeInfo.title}", style = MaterialTheme.typography.titleLarge)
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Row{
                        Text(text = "Authors: ${book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author"}", style = MaterialTheme.typography.bodyLarge)

                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row{
                        val formattedDescription = book.volumeInfo.description?.let { Jsoup.parse(it).text() } ?: "No description available."
                        Text(text = "Description: $formattedDescription", style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Display the book image if available
                    Row(){
                        book.volumeInfo.imageLinks?.smallThumbnail?.let { imageUrl ->
                            Image(
                                painter = rememberAsyncImagePainter(imageUrl),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }


                }
            } ?: run {
                Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
            }
        }
        // end of box
    } else {
        //LandscapeLayout(windowInfo)
        // Layout to display book details
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            bookItem?.let { book ->
//                Column(modifier = Modifier.padding(16.dp)) {
//
//                    Row{
//                        Text(text = "Title: ${book.volumeInfo.title}", style = MaterialTheme.typography.titleLarge)
//                    }
//
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Row{
//                        Text(text = "Authors: ${book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author"}", style = MaterialTheme.typography.bodyLarge)
//
//                    }
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Row{
//                        val formattedDescription = book.volumeInfo.description?.let { Jsoup.parse(it).text() } ?: "No description available."
//                        Text(text = "Description: $formattedDescription", style = MaterialTheme.typography.bodyMedium)
//                    }
//                    Spacer(modifier = Modifier.height(8.dp))
//                    // Display the book image if available
//                    Row(){
//                        book.volumeInfo.imageLinks?.smallThumbnail?.let { imageUrl ->
//                            Image(
//                                painter = rememberAsyncImagePainter(imageUrl),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(200.dp),
//                                contentScale = ContentScale.Crop
//                            )
//                        }
//                    }
//
//
//                }
                Row( modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        book.volumeInfo.imageLinks?.smallThumbnail?.let { imageUrl ->
                            Image(
                                painter = rememberAsyncImagePainter(imageUrl),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier=Modifier.width(6.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row{
                            Text(text = "Title: ${book.volumeInfo.title}", style = MaterialTheme.typography.titleLarge)
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Row{
                            Text(text = "Authors: ${book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author"}", style = MaterialTheme.typography.bodyLarge)

                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row{
                            val formattedDescription = book.volumeInfo.description?.let { Jsoup.parse(it).text() } ?: "No description available."
                            Text(text = "Description: $formattedDescription", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            } ?: run {
                Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
            }
        }
        // end of box
    }


}



@Composable
fun calculateCurrentWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val orientation = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
        Orientation.PORTRAIT
    } else {
        Orientation.LANDSCAPE
    }

    return WindowInfo(
        widthDp = screenWidth,
        heightDp = screenHeight,
        orientation = orientation
    )
}

// Data class to hold window info
data class WindowInfo(
    val widthDp: Int,
    val heightDp: Int,
    val orientation: Orientation
)

enum class Orientation {
    PORTRAIT,
    LANDSCAPE
}

