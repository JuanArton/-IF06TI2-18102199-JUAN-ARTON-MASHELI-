package com.juanArtonM_18102199.praktikum14.`interface`

import com.juanArtonM_18102199.praktikum14.model.Login
import com.juanArtonM_18102199.praktikum14.model.Quote

interface MainView {
    fun showMessage(messsage : String)
    fun resultQuote(data: ArrayList<Quote>)
    fun resultLogin(data: Login)
}