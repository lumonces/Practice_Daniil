package com.example.workwithxml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.FileInputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val xmlFilePath = "parser.xml"
    val assetManager = LocalContext.current.assets
    try{
        val xmlObject : XMLObject
        assetManager.open(xmlFilePath).use {xmlText ->
            xmlObject = XMLParser.start(xmlText)
        }
        val obj = xmlObject.find("SNM")
        println("SNM: " + obj.value + "\nOBJ: " + obj.toString())
    }catch (e : Exception) {
        e.printStackTrace()
    }
}