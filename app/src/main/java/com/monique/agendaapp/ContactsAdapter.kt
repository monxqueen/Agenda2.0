package com.monique.agendaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(var dataSet: MutableList<Contact>): RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(){

    class ContactsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.itemName)
        val number = view.findViewById<TextView>(R.id.itemNumber)
        val reference = view.findViewById<TextView>(R.id.itemRef)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val viewInstance = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contato, parent, false)
        return (ContactsViewHolder(viewInstance))
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.name.text = dataSet[position].getName()
        holder.number.text = dataSet[position].getNumber()
        holder.reference.text = dataSet[position].getAddInfo()
    }

    override fun getItemCount(): Int = dataSet.size

    fun dataSetChanger(newDataSet: MutableList<Contact>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

}