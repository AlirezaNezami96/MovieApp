package alireza.nezami.movieapp

import alireza.nezami.data.util.NetworkMonitor
import alireza.nezami.designsystem.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
//                MoviesApp(
//                    networkMonitor = networkMonitor
//                )
            }
        }
    }
}

