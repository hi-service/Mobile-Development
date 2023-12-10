package com.hiservice.mobile.util

class AlertMessages {
    companion object{
        fun RegisterAlertMessages(messages : String) : String{
            if(messages.contains("The email address is already in use by another account.")){
                return "Email telah digunakan."
            }else if(messages.contains("The password must be 6 characters long or more.")){
                return "Password harus lebih dari 8 digit"
            }else if(messages.contains("The email address is badly formatted.")){
                return "Email tidak sesuai"
            }else if(messages.contains("We have blocked all requests from this device due to unusual activity. Try again later.")){
                return "Akses anda di block sementara, coba beberapa saat lagi"
            }else{
                return "Error tidak ditemukan dengan kode " + messages
            }
        }
    }
}