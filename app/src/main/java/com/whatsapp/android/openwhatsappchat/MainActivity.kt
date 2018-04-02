package com.example.android.openwhatsappchat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    var number: String? = null
    var numberText: EditText? = null
    var spinResult: String? = null
    val storeURL: String = "https://play.google.com/store/apps/details?id=com.google.android.apps.plus"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val option = findViewById<Spinner>(R.id.code_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.code_lable, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        option.adapter = adapter

        option.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinResult = option.getSelectedItem().toString()
                spinResult = spinResult!!.replace("\\D", "")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        numberText = findViewById<EditText>(R.id.number)
        var wUrl: String
        val clickButton = findViewById<ImageButton>(R.id.button)
        val messageDev = findViewById<Button>(R.id.message_dev)
        val shareBtn = findViewById<Button>(R.id.share)

        clickButton.setOnClickListener {
            number = numberText!!.text.toString()
            if (isValidPhoneNumber(number!!)) {
                number = spinResult + number
                number = getOnlyNumerics(number!!)
                var abcD: String = "https://api.whatsapp.com/send?phone="
                wUrl = abcD + number
                web_page_open(wUrl, this)
                toast(getString(R.string.opening_chat_for) + " " + number)
            } else {
                toast(getString(R.string.err_tell))
            }
        }

        messageDev.setOnClickListener {
            web_page_open("https://api.whatsapp.com/send?phone=918898231726", this)
        }

        shareBtn.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    getString(R.string.share_msg) + storeURL)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

    }

    fun web_page_open(urls: String, mContext: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        mContext.startActivity(intents)
    }

    /** Number Format Validation method   */
    fun isValidPhoneNumber(phone: String): Boolean {
        var check: Boolean
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            check = !(phone.length < 6 || phone.length > 13)
        } else {
            check = false
        }
        return check
    }

    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }

    fun getOnlyNumerics(str: String): String {

        val strBuff = StringBuffer()
        // val _c: Char
        for (i in 0 until str.length) {
            var c = str.get(i)
            if (Character.isDigit(c)) {
                strBuff.append(c)
            }
        }
        return strBuff.toString()
    }
}