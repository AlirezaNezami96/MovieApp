package alireza.nezami.movieapp

import alireza.nezami.home.presentation.movieList.MoviesScreen
import alireza.nezami.movieapp.ui.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                MoviesScreen()
            }
        }
    }
}

