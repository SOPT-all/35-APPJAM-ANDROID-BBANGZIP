import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import org.andsopt.android.bbangzip.BuildConfig

@HiltAndroidApp
class BbangZipApp : Application() {
    override fun onCreate() {
        super.onCreate()

        setDarkMode()
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}