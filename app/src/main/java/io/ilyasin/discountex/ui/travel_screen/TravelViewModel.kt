package io.ilyasin.discountex.ui.travel_screen

import dagger.hilt.android.lifecycle.HiltViewModel
import io.ilyasin.discountex.domain.TravelUseCase
import io.ilyasin.discountex.ui.common.GridViewModel
import javax.inject.Inject

@HiltViewModel
class TravelViewModel @Inject constructor(travelsUseCase: TravelUseCase) : GridViewModel(travelsUseCase)