package alireza.nezami.movieapp

import alireza.nezami.data.util.ConnectivityManagerNetworkMonitor
import alireza.nezami.data.util.NetworkMonitor
import alireza.nezami.designsystem.theme.MovieAppTheme
import alireza.nezami.movieapp.ui.MoviesApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var networkMonitor: NetworkMonitor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkMonitor = ConnectivityManagerNetworkMonitor(this.applicationContext)
        setContent {
            MovieAppTheme {
                MoviesApp(
                    networkMonitor = networkMonitor
                )
            }
        }
    }
}

