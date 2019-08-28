package com.example.dell.mvvm_bus_arrival.utils

class Utils {

    val regex = Regex(pattern = """\d+|-|N""")  // 숫자, - , N(야간)만 포함

    fun getRegex(regexResult: Sequence<MatchResult>): String{
        val result = StringBuilder()
        for (text in regexResult) {
            result.append(text.value)
        }
        return result.toString()
    }
}