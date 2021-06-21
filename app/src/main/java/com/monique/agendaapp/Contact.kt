package com.monique.agendaapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(private val name: String, private val number: String, private val additionalInfo: String, private val contactKind: AddInfo): Parcelable{
    fun getName(): String = name
    fun getNumber(): String = number
    fun getAddInfo(): String = additionalInfo
    fun getContactKind(): AddInfo = contactKind
    fun searchForContact(searchedName: String): Boolean{
        return searchedName == name //true or false
    }
    fun print(personalOrCoworker: Int): String {
        if(personalOrCoworker == 1){
            return ("Nome: $name\n Número: $number\n E-mail: $additionalInfo")
        }
        else{
            return ("Nome: $name\n Número: $number\n Referência: $additionalInfo")
        }
    }
}