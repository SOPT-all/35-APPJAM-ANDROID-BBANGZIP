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
        setTimber()
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}