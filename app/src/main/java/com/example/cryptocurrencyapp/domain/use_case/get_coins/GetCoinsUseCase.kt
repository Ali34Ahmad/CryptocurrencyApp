package com.example.cryptocurrencyapp.domain.use_case.get_coins

import android.net.http.HttpException
import android.util.Log
import com.example.cryptocurrencyapp.core.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    val coinRepository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        try {
            emit(Resource.Loading())
            val coins= coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success(data=coins))
        }catch (e:Exception){
            Log.e("GetCoinsUseCase", "invoke: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error occurred"))
        }
    }
}