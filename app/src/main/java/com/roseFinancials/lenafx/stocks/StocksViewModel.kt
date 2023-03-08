package com.roseFinancials.lenafx.stocks

import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.data.repositories.ApiDataRepository
import com.roseFinancials.lenafx.data.repositories.StocksStateRepository
import com.roseFinancials.lenafx.utils.ResetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val stocksStateRepository: StocksStateRepository,
    private val apiDataRepository: ApiDataRepository
): ResetViewModel() {
    override val stocksState = stocksStateRepository.stocksState
    override val apiState = apiDataRepository.apiState
    override val job = apiDataRepository.jobState.value
    val tickerState = apiDataRepository.tickerState
    val indexState = apiDataRepository.indexState

    fun callApis() { callApi(apiDataRepository) }

    fun calculateBetaSlope(data: List<Pair<Double, Double>>): Double {
        val (tickerMean, indexMean) = data.unzip().toList().map { it.average() }

        val numerator = data.sumOf { pair -> (pair.second - indexMean) * (pair.first - tickerMean) }
        val denominator = data.sumOf { (it.second - indexMean).pow(2) }

        return numerator/denominator
    }

    override fun updateApiState(state: Boolean) { viewModelScope.launch { apiDataRepository.updateState(state) } }

    override fun updateJob(job: Job?) { viewModelScope.launch { apiDataRepository.updateJob(job) } }

    override fun resetState() { viewModelScope.launch { stocksStateRepository.resetState() } }
}