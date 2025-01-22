package org.android.bbangzip.data.interceptor

import android.app.Application
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor
    @Inject
    constructor(
        private val json: Json,
        private val userLocalRepository: UserLocalRepository,
        private val context: Application,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val accessToken =
                runBlocking {
                    userLocalRepository.userPreferenceFlow
                        .map { it.accessToken }
                        .firstOrNull()
                }
            val isLogin =
                runBlocking {
                    userLocalRepository.userPreferenceFlow
                        .map { it.isLogin }
                        .firstOrNull()
                }

            Timber.d("[인터셉터] -> AuthInterceptor에 $accessToken")
            Timber.d("[인터셉터] -> AuthInterceptor에 $isLogin")

            val authRequest = if (isLogin == true) originalRequest.newBuilder().addHeader(ACCESS_TOKEN, "$accessToken").build() else originalRequest
            val response = chain.proceed(authRequest)

            when (response.code) {
                EXPIRE_TOKEN_CODE -> {
                    // TODO 토큰 재발급 api 연동
                }
            }
            return response
        }

        companion object {
            const val ACCESS_TOKEN = "Authorization"
            const val EXPIRE_TOKEN_CODE = 401
        }
    }
