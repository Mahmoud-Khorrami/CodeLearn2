package com.mahapp1397.codelearn2.use_cases

data class UseCases(
    val savePhoneNumberUseCase: SavePhoneNumberUseCase,
    val getPhoneNumberUseCase: GetPhoneNumberUseCase,
    val saveProfileUseCase: SaveProfileUseCase,
    val getProfilesUseCase: GetProfilesUseCase,
    val getSelectedProfileUseCase: GetSelectedProfileUseCase
    )
