package com.monique.agendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class CadastrarContato : AppCompatActivity() {

    private lateinit var nameEdt: EditText
    private lateinit var numberEdt: EditText
    private lateinit var optionsRdGroup: RadioGroup
    private lateinit var refEmailEdt: EditText
    private lateinit var submitBtn: Button
    private var addInfo: AddInfo? = null
    //private val contacts = mutableListOf<Contact>()
    //private var arrayContacts: Array<Any>? = null
    private var contacts: MutableList<Contact>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_contato)

        bindviews()
    }

    private fun bindviews(){
        nameEdt = findViewById(R.id.name)
        numberEdt = findViewById(R.id.phone_number)
        optionsRdGroup = findViewById(R.id.optionsRdGroup)
        refEmailEdt = findViewById(R.id.reference_or_email)
        submitBtn = findViewById(R.id.submit)

        submitBtn.setOnClickListener{
            val name = nameEdt.text.toString()
            val number = numberEdt.text.toString()
            val refOrEmail = refEmailEdt.text.toString()
            val additionalInfo: Int

            if(name.isNotEmpty()){
                if(number.isNotEmpty()){
                    if(refOrEmail.isNotEmpty()){
                        //arrayContacts = createContact(name, number, refOrEmail)
                        createContact(name, number, refOrEmail)
                        Toast.makeText(this, "Contato criado com sucesso", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("CONTACTS_KEY", ArrayList(contacts))
                        startActivity(intent)

                    }else{
                        refEmailEdt.error = "Referência ou email precisa ser inserido"
                    }
                }else{
                    numberEdt.error = "Número do contato precisa ser inserido"
                }
            }else{
                nameEdt.error = "Nome do contato precisa ser inserido"
            }
        }
    }

    fun onRadioButtonClicked(view: View){
        if (view is RadioButton) {
            val isChecked = view.isChecked

            when(view.id){
                R.id.workRdButton ->
                    if(isChecked){
                        addInfo = AddInfo.COWORKER
                    }

                R.id.personalRdButton ->
                    if(isChecked){
                        addInfo = AddInfo.PERSONAL
                    }
            }
        }
    }

    private fun createContact(name: String, number: String, refOrEmail: String){
        if(addInfo == AddInfo.COWORKER){
            contacts?.add(
                Contact(name, number, refOrEmail, AddInfo.COWORKER)
            )
        }
        else if(addInfo == AddInfo.PERSONAL){
            contacts?.add(
                Contact(name, number, refOrEmail, AddInfo.PERSONAL)
            )
        }
    }
}