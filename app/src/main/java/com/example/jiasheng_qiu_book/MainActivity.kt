package com.example.jiasheng_qiu_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jiasheng_qiu_book.BookUI.DetailsScreen
import com.example.jiasheng_qiu_book.BookUI.SearchScreen
import com.example.jiasheng_qiu_book.network.RetrofitInstance
import com.example.jiasheng_qiu_book.repository.BookRepository
import com.example.jiasheng_qiu_book.ui.theme.Jiasheng_qiu_bookTheme
import com.example.jiasheng_qiu_book.viewmodel.BookViewModel
import com.example.jiasheng_qiu_book.viewmodel.BookViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = BookRepository(RetrofitInstance.api)
        val viewModel = ViewModelProvider(this, BookViewModelFactory(repository)).get(BookViewModel::class.java)
        setContent {
            Jiasheng_qiu_bookTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BookSearchApp(viewModel)
                }
            }
        }
    }
}


@Composable
fun BookSearchApp(viewModel: BookViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "search") {
        composable("search") {
            SearchScreen(viewModel) { title ->
                navController.navigate("details/$title")
            }
        }
        composable("details/{id}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("id") ?: ""
            DetailsScreen(viewModel, title)  // Pass the viewModel to DetailsScreen
        }
    }
}