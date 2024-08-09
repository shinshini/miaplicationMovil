package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Productos

class MainActivity : AppCompatActivity() {
    lateinit var btnCalcular: Button
    lateinit var txtPrecio: EditText
    lateinit var tvResul: TextView
    lateinit var spLista: Spinner
    lateinit var listpro: ListView

    lateinit var productoList : MutableList <String>
    lateinit var adapterlistView : ArrayAdapter <String>
    lateinit var  textNombre : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //codigo
        cargarR()
        estadoOnclick()
        cargarListaproducto()

        //cargar listad e datos en spinner
        val listaPaises = arrayOf("USA", "BOL", "ESP")
        val adaptador1 =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPaises)
        spLista.adapter = adaptador1


    }

    fun cargarR() {
        btnCalcular = findViewById(R.id.btnCalcularIVA)
        txtPrecio = findViewById(R.id.txtProducto)
        tvResul = findViewById(R.id.tvResultado)
        spLista = findViewById(R.id.spPaises)
        listpro = findViewById(R.id.listaProducto)
        textNombre= findViewById(R.id.textName)
    }

    fun estadoOnclick() {
        btnCalcular.setOnClickListener() {

            val laptop = Productos(textNombre.text.toString(), txtPrecio.text.toString().toDouble())
            //val datosRe : Double = laptop.calIVA()

            when (spLista.selectedItem.toString()) {
                "USA" -> productoList.add(laptop.nombre + ", "+ laptop.precio+ ", " + "IVA: "+ laptop.calIVA(0.03).toString())
                "BOL" -> productoList.add(laptop.nombre + ", "+ laptop.precio+ ", " + "IVA: "+ laptop.calIVA(0.13).toString())
                "ESP" -> productoList.add(laptop.nombre + ", "+ laptop.precio+ ", " + "IVA: "+ laptop.calIVA(0.05).toString())
            }
            listpro.adapter = adapterlistView
        }
    }
    fun cargarListaproducto(){

       // val productos= arrayOf("LAPTOP", "MOUSE")
        productoList = mutableListOf("3500")
        adapterlistView= ArrayAdapter(this, android.R.layout.simple_list_item_1,productoList)
        listpro.adapter = adapterlistView
    }
}
