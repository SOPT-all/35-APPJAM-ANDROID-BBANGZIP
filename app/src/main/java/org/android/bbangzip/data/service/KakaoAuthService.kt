package org.android.bbangzip.data.service

import android.content.ContentValues.TAG
import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber
import javax.inject.Inject

class KakaoAuthService
    @Inject
    constructor(
        private val client: UserApiClient,
    ) {
        private val isKakaoTalkLoginAvailable: (Context) -> Boolean = { context ->
            client.isKakaoTalkLoginAvailable(context)
        }

        fun loginKakao(
            context: Context,
            loginListener: ((String) -> Unit),
        ) {
            val callback: (OAuthToken?, Throwable?) -> Unit = { oAuthToken, throwable ->
                if (throwable != null) {
                    loginError(throwable = throwable, context = context)
                    Timber.d("[카카오 로그인] -> 사용자 정보 요청 실패 $throwable")
                } else if (oAuthToken != null) {
                    loginSuccess(
                        oAuthToken = oAuthToken,
                        loginListener = loginListener,
                    )
                    Timber.d("[카카오 로그인] -> 사용자 정보 요청 성공 $oAuthToken")
                }
            }

            if (isKakaoTalkLoginAvailable(context)) {
                Timber.d("[카카오 로그인] -> loginWithKakaoTalk $isKakaoTalkLoginAvailable")
                client.loginWithKakaoTalk(context = context, callback = callback)
            } else {
                Timber.d("[카카오 로그인] -> loginWithKakaoAccount $isKakaoTalkLoginAvailable")
                client.loginWithKakaoAccount(context = context, callback = callback)
            }
        }

        private fun loginError(
            throwable: Throwable,
            context: Context,
        ) {
            val kakaoType = if (isKakaoTalkLoginAvailable(context)) KAKAO_TALK else KAKAO_ACCOUNT
            Timber.d("[카카오 로그인] -> {$kakaoType}으로 로그인 실패 ${throwable.message}")
        }

        private fun loginSuccess(
            oAuthToken: OAuthToken,
            loginListener: (String) -> Unit,
        ) {
            client.me { _, error ->
                loginListener(oAuthToken.accessToken)
                if (error != null) {
                    Timber.d("[카카오 로그인] -> 사용자 정보 요청 실패 $error")
                }
            }
        }

        fun logoutKakao(logoutListener: () -> Unit) {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Timber.tag(TAG).e(error.message, "[카카오 로그인] -> 카카오 로그아웃 실패.")
                } else {
                    logoutListener()
                    Timber.tag(TAG).i("[카카오 로그인] -> 카카오 로그아웃 성공.")
                }
            }
        }

        fun withdrawKakao(withdrawListener: () -> Unit) {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Timber.tag(TAG).e(error, "[카카오 로그인] -> 카카오 회원 탈퇴 실패 : ${error.message}")
                } else {
                    withdrawListener()
                    Timber.tag(TAG).i("[카카오 로그인] -> 카카오 회원 탈퇴 성공. ")
                }
            }
        }

        companion object {
            const val KAKAO_TALK = "kakao talk"
            const val KAKAO_ACCOUNT = "kakao account"
        }
    }
