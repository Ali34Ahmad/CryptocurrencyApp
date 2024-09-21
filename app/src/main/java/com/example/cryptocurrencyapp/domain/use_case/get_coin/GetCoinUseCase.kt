package com.example.cryptocurrencyapp.domain.use_case.get_coin

import android.net.http.HttpException
import android.util.Log
import com.example.cryptocurrencyapp.core.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetails
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetails
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    val coinRepository: CoinRepository
) {
    operator fun invoke(coinId:String): Flow<Resource<CoinDetails>> = flow{
        try {
            emit(Resource.Loading())
            val coin= coinRepository.getCoinDetailsById(coinId).toCoinDetails()
            emit(Resource.Success(data=coin))
        }catch (e:Exception){
            Log.e("GetCoinDetailsUseCase", "invoke: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error occurred"))
        }
    }
}