package com.example.core.base.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


//Use this class if you want to manage the cycle between remote and local APIs
@Suppress("UNCHECKED_CAST")
abstract class BaseLocalUseCase<Response : Any,MapIt : Any,in Params> {

    //map class from response to the result needed in View
    abstract fun mapper(req: Response): MapIt
    // run the local fun
    protected abstract suspend fun run(params: Params?) : Flow<Response>
    // ask if we should cache the response in local DB
    protected abstract var isSave :Boolean
    // action save to local DB
    abstract suspend fun saveToLocal(res: Response) : MapIt

    operator fun invoke(params: Params? = null,coroutineScope: CoroutineScope, onResult: (MapIt) -> Unit = {}) {
        coroutineScope.launch(Dispatchers.Default) {
            run(params).collect {
                if (isSave) {
                    onResult.invoke(saveToLocal(params as Response))
                } else {
                    val mapped = mapper(it)
                    onResult.invoke(mapped)
                }
            }
        }

    }
}