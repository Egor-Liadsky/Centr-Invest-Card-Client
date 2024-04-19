package com.turtleteam.core_view.view

fun String.hideCardNum():String {
    return "•••• •••• •••• "+this.takeLast(4)
}
fun hiddenDate():String  = "••/••"