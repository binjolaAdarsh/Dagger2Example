package com.app.daggerauth.ui.auth

enum class AuthStatus{
    AUTHENTICATED,ERROR,LOADING,NOT_AUTHENTICATED
}
class AuthResource <T>(var status:AuthStatus,var data :  T? , var msg : String?){
    companion object{

        fun <T> authenticated(data:T) = AuthResource(AuthStatus.AUTHENTICATED,data,"")
        fun <T> error(msg:String,data:T?=null) = AuthResource(AuthStatus.ERROR,data,msg)
        fun <T> loading(data:T?=null) = AuthResource(AuthStatus.LOADING,data,"")
        fun  <T> logout(data:T?=null) = AuthResource(AuthStatus.NOT_AUTHENTICATED,data,msg = "")
    }
}