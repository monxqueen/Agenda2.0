package com.monique.agendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var searchEdt: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var newContactBtn: Button
    private lateinit var fullListBtn: Button
    private lateinit var rvContacts: RecyclerView
    private lateinit var contactsAdapter: ContactsAdapter
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
        newContactBtn = findViewById(R.id.newContactBtn)
        fullListBtn = findViewById(R.id.fullListBtn)

        /*val extras = intent.extras
        if(extras != null) {
            contactsList = intent.getParcelableArrayListExtra<Contact>("CONTACTS_KEY") as ArrayList<Contact>
            testTextView.text = printContacts()
        }*/
        contactsList.addAll(CadastrarContato.contatos)
        contactsList.sortBy { it.getName() }
        contactsAdapter = ContactsAdapter(contactsList)
        rvContacts.adapter = contactsAdapter
        rvContacts.layoutManager = LinearLayoutManager(this)

        searchBtn.setOnClickListener{
            val contactSearched = searchEdt.text.toString()
            val foundIt: Boolean
            if(contactSearched .isNotEmpty()){
                foundIt = contactSearch(contactSearched )
                if(foundIt){
                    newContactBtn.visibility = View.INVISIBLE
                    fullListBtn.visibility = View.VISIBLE
                    fullListBtn.setOnClickListener{
                        it.visibility = View.INVISIBLE
                        newContactBtn.visibility = View.VISIBLE
                        contactsAdapter.dataSetChanger(contactsList)
                    }
                }else{
                    Toast.makeText(this, "Nenhum contato com esse nome encontrado", Toast.LENGTH_LONG).show()
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

    /*private fun printContacts(): String{
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
    }*/

    private fun contactSearch(name: String): Boolean{
        var foundIt: Boolean
        val newList: MutableList<Contact> = mutableListOf()
        for(contact in contactsList) {
            foundIt = contact.searchForContact(name)
            if(foundIt){
                newList.add(contact)
            }
        }
        return if(newList.isNullOrEmpty()){
            false
        } else{
            contactsAdapter.dataSetChanger(newList)
            true
        }
    }
}