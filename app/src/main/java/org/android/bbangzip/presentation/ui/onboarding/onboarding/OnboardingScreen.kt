package org.android.bbangzip.presentation.ui.onboarding.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.progressbar.OnboardingProgressBar
import org.android.bbangzip.presentation.component.textfield.BbangZipBasicTextField
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.component.wheelpicker.BbangZipSemesterPicker
import org.android.bbangzip.presentation.model.Semester
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.OnboardingType
import org.android.bbangzip.presentation.ui.onboarding.OnboardingContract
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme
import timber.log.Timber

@Composable
fun OnboardingScreen(
    state: OnboardingContract.OnboardingState,
    pagerState: PagerState,
    onUserNameChanged: (String) -> Unit = {},
    onSemesterChanged: (Semester) -> Unit = {},
    onSubjectChanged: (String) -> Unit = {},
    onChangeUserNameFocused: (Boolean) -> Unit = {},
    onChangeSubjectFocused: (Boolean) -> Unit = {},
    onBackBtnClick: () -> Unit = {},
    onNextBtnClick: () -> Unit = {},
    clearUserName: () -> Unit = {},
    clearSubject: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF)
                .addFocusCleaner(focusManager),
    ) {
        BbangZipBaseTopBar(
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            onLeadingIconClick = { onBackBtnClick() },
        )

        Spacer(modifier = Modifier.height(11.dp))

        OnboardingProgressBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
            onboardingType = OnboardingType.entries[state.currentPage],
        )

        Box(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
        ) {
            OnboardingPager(
                modifier = Modifier.fillMaxSize(),
                pagerState = pagerState,
                state = state,
                focusManager = focusManager,
                onUserNameChanged = { onUserNameChanged(it) },
                onSemesterChanged = { onSemesterChanged(it) },
                onSubjectChanged = { onSubjectChanged(it) },
                onChangeUserNameFocused = { onChangeUserNameFocused(it) },
                onChangeSubjectFocused = { onChangeSubjectFocused(it) },
                clearUserName = { clearUserName() },
                clearSubject = { clearSubject() },
            )
        }

        BbangZipButton(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onNextBtnClick() },
            label = stringResource(id = R.string.btn_next_label),
            isEnable = state.buttonEnabled,
            trailingIcon = R.drawable.ic_chevronright_thick_small_24,
        )
    }
}

@Composable
private fun OnboardingPager(
    pagerState: PagerState,
    state: OnboardingContract.OnboardingState,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    onUserNameChanged: (String) -> Unit = {},
    onSemesterChanged: (Semester) -> Unit = {},
    onSubjectChanged: (String) -> Unit = {},
    onChangeUserNameFocused: (Boolean) -> Unit = {},
    onChangeSubjectFocused: (Boolean) -> Unit = {},
    clearUserName: () -> Unit = {},
    clearSubject: () -> Unit = {},
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        userScrollEnabled = false,
    ) { pageIndex ->
        val onboardingType = OnboardingType.entries[pageIndex]

        Timber.d("[온보딩] -> ${state.currentPage}")
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(37.dp))

            OnboardingType.entries[pageIndex].description?.let { descriptionId ->
                if (pageIndex == 1) {
                    Text(
                        text = stringResource(id = descriptionId, state.userName ?: ""),
                        style = BbangZipTheme.typography.body2Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61,
                    )
                } else if (pageIndex == 2) {
                    Text(
                        text = stringResource(id = descriptionId, state.semester.year + "년", state.semester.semester.text),
                        style = BbangZipTheme.typography.body2Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61,
                    )
                }
            }

            if (pageIndex == 0) {
                Spacer(modifier = Modifier.height(30.dp))
            } else {
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = stringResource(id = onboardingType.title),
                style = BbangZipTheme.typography.title2Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
            )

            Spacer(modifier = Modifier.height(32.dp))

            when (pageIndex) {
                0 ->
                    BbangZipBasicTextField(
                        bbangZipTextFieldInputState = state.userNameTextFieldState,
                        leadingIcon = R.drawable.ic_user_one_default_24,
                        placeholder = R.string.onboarding_name_placeholder,
                        guideline = R.string.onboarding_name_description,
                        value = state.userName ?: "",
                        onValueChange = { onUserNameChanged(it) },
                        maxCharacter = 10,
                        focusManager = focusManager,
                        onFocusChange = { onChangeUserNameFocused(it) },
                        onDeleteButtonClick = { clearUserName() },
                    )

                1 ->
                    BbangZipSemesterPicker(
                        onConfirm = { onSemesterChanged(it) },
                        currentSemester = state.semester,
                    )

                2 ->
                    BbangZipBasicTextField(
                        bbangZipTextFieldInputState = state.subjectNameTextFieldState,
                        leadingIcon = R.drawable.ic_book_default_24,
                        placeholder = R.string.onboarding_subject_placeholder,
                        guideline = R.string.onboarding_subject_description,
                        value = state.subjectName ?: "",
                        onValueChange = { onSubjectChanged(it) },
                        maxCharacter = 20,
                        focusManager = focusManager,
                        onFocusChange = { onChangeSubjectFocused(it) },
                        onDeleteButtonClick = { clearSubject() },
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    BBANGZIPTheme {
        val pagerState = rememberPagerState(pageCount = { 3 })

        OnboardingScreen(
            state =
                OnboardingContract.OnboardingState(
                    currentPage = 1,
                    buttonEnabled = false,
                ),
            pagerState = pagerState,
            onBackBtnClick = { },
            onNextBtnClick = { },
        )
    }
}
