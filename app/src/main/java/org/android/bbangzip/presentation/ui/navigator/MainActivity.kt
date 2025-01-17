package org.android.bbangzip.presentation.ui.navigator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.android.bbangzip.ui.theme.BBANGZIPTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            BBANGZIPTheme {
                MainScreen(navigator = navigator)
            }
        }
    }
}
