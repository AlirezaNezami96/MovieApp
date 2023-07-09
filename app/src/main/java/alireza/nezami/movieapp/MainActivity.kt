package alireza.nezami.movieapp

import alireza.nezami.data.util.NetworkMonitor
import alireza.nezami.home.presentation.movieList.MoviesScreen
import alireza.nezami.movieapp.ui.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {


    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
            }
        }
    }
}

