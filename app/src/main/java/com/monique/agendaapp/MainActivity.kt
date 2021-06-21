package com.monique.agendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var searchEdt: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var testTextView: TextView
    private lateinit var newContactBtn: Button
    private var contactsList: MutableList<Contact>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindviews()
    }

    private fun bindviews(){
        searchEdt = findViewById(R.id.searchEdt)
        searchBtn = findViewById(R.id.searchBtn)
        testTextView = findViewById(R.id.testTextView)
        newContactBtn = findViewById(R.id.newContactBtn)

        contactsList = intent.getParcelableArrayListExtra<Contact>("CONTACT_KEY") as? ArrayList<Contact>
        testTextView.text = printContacts()

        searchBtn.setOnClickListener{
            val searchForContact = searchEdt.text.toString()
            if(searchForContact.isNotEmpty()){
                contactSearch(searchForContact)
            }else{
                searchEdt.error = "Nome do contato para pesquisar precisa ser inserido"
            }
        }

        newContactBtn.setOnClickListener{
            val intent = Intent(this, CadastrarContato::class.java)
            startActivity(intent)
        }

    }

    private fun printContacts(): String{
        var message = ""
        if(contactsList?.isNotEmpty() == true){
            for(contact in contactsList!!){
                if(contact.getContactKind() == AddInfo.COWORKER){
                    message += contact.print(1)
                }
                else if(contact.getContactKind() == AddInfo.PERSONAL){
                    message += contact.print(2)
                }
            }
        }
        return message
    }


    private fun contactSearch(name: String){
    }
}