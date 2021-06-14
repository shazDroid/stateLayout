package com.shazdroid.statelayout

data class StateOptions(
    var stateType: StateType,
    var errorMessage: String = "Error occurred",
    var errorLottieRes: Int = R.raw.error,
    var loadingMessage: String = "Loading...",
    var loadingLottieRes: Int = R.raw.loading_truck
) {

    fun stateType(stateType: StateType): StateOptions {
        this.stateType = stateType
        return this
    }

    fun errorMessage(errorMessage: String): StateOptions {
        this.errorMessage = errorMessage
        return this
    }

    fun errorLottieRes(errorLottieRes: Int): StateOptions {
        this.errorLottieRes = errorLottieRes
        return this
    }

    fun loadingMessage(loadingMessage: String): StateOptions {
        this.loadingMessage = loadingMessage
        return this
    }

    fun loadingLottieRes(loadingLottieRes: Int): StateOptions {
        this.loadingLottieRes = loadingLottieRes
        return this
    }

    enum class StateType {
        LOADING, ERROR
    }
}