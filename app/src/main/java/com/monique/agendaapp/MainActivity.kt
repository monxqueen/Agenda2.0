package com.monique.agendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var searchEdt: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var testTextView: TextView
    private lateinit var newContactBtn: Button
    private lateinit var fullListBtn: Button
    private lateinit var rvContacts: RecyclerView
    private var contactsList: MutableList<Contact> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindviews()
    }

    private fun bindviews(){
        searchEdt = findViewById(R.id.searchEdt)
        searchBtn = findViewById(R.id.searchBtn)
        rvContacts = findViewById(R.id.rvContacts)
        testTextView = findViewById(R.id.testTextView)
        newContactBtn = findViewById(R.id.newContactBtn)
        fullListBtn = findViewById(R.id.fullListBtn)

        /*val extras = intent.extras
        if(extras != null) {
            contactsList = intent.getParcelableArrayListExtra<Contact>("CONTACTS_KEY") as ArrayList<Contact>
            testTextView.text = printContacts()
        }*/
        contactsList.addAll(CadastrarContato.contatos)
        testTextView.text = printContacts()

        searchBtn.setOnClickListener{
            val searchForContact = searchEdt.text.toString()
            var message: String
            if(searchForContact.isNotEmpty()){
                message = contactSearch(searchForContact)
                if(message == ""){
                    Toast.makeText(this, "Nenhum contato com esse nome encontrado", Toast.LENGTH_LONG).show()
                }else{
                    testTextView.text = message
                    fullListBtn.visibility = View.VISIBLE
                    fullListBtn.setOnClickListener{
                        it.visibility = View.INVISIBLE
                        testTextView.text = printContacts()
                    }
                }
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
        contactsList.sortBy { it.getName() }
        var message = ""
            for(contact in contactsList){
                if(contact.getContactKind() == AddInfo.COWORKER){
                    message += contact.print(1)
                }
                else if(contact.getContactKind() == AddInfo.PERSONAL){
                    message += contact.print(2)
                }
            }
        return message
    }


    private fun contactSearch(name: String): String{
        var foundIt: Boolean = false
        var message = ""
        for(contact in contactsList) {
            foundIt = contact.searchForContact(name)
            if(foundIt == true){
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
}