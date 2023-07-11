package alireza.nezami.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Rating(
    modifier: Modifier,
    rating: Double
) {

    Row(
        modifier = modifier
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.background.copy(
                    alpha = 0.8f
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating Icon",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(16.dp)
                .padding(end = 4.dp)
        )
        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
            )
        )
    }
}