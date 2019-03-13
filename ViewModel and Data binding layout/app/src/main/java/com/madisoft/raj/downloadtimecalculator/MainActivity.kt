package com.madisoft.raj.downloadtimecalculator


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction(allowStateLoss = true) {
                replace(R.id.fragContainer, FragmentNetSpeed.newInstance(), "frag Created")
            }

        }


        //speedEditText.onTextChange()
        //fileSizeEditText.onTextChange()
    }


}




