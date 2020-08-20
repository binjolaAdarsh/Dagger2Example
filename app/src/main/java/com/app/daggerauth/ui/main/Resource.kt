package com.app.daggerauth.ui.main

enum class Status {
    SUCCESS, ERROR, LOADING
}

data class Resource<T>(var status: Status, var data: T?=null, var message: String="") {
    companion object{
        fun <T> success(data: T) :Resource<T>{
            return Resource(Status.SUCCESS,data)
        }
        fun <T> error(msg:String) :Resource<T>{
            return Resource(Status.ERROR,message = msg)
        }
        fun <T> loading() :Resource<T>{
            return Resource(Status.LOADING)
        }
    }
}