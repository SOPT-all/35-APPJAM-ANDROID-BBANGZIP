package org.android.bbangzip.presentation.ui.login

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf
import org.android.bbangzip.R
import org.android.bbangzip.presentation.util.base.BaseContract

class LoginContract {
    @Parcelize
    data class LoginState(
        val isOnboardingCompleted: Boolean = false,
        val onBoardingList: List<Int> =
            immutableListOf(
                R.drawable.img_login1,
                R.drawable.img_login2,
                R.drawable.img_login1,
            ),
        val loginState: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface LoginEvent : BaseContract.Event {
        data class OnClickKakaoLoginBtn(val context: Context) : LoginEvent
    }

    sealed interface LoginReduce : BaseContract.Reduce {
        data class UpdateState(val state: LoginState) : LoginReduce

        data class UpdateLoginSuccess(val success: Boolean) : LoginReduce
    }

    sealed interface LoginSideEffect : BaseContract.SideEffect {
        data object NavigateToOnboarding : LoginSideEffect

        data object NavigateToSubject : LoginSideEffect
    }
}
