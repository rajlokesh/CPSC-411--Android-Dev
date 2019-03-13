package com.madisoft.raj.downloadtimecalculator



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private val FRAG_NET_SPEED = "fragCreated"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            addFragment(FragmentNetSpeed.newInstance(), R.id.fragContainer)
        }

        //speedEditText.onTextChange()
        //fileSizeEditText.onTextChange()
    }

    inline fun FragmentManager.transaction(now: Boolean = false, allowStateLoss: Boolean = false,func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.transaction { add(frameId, fragment,FRAG_NET_SPEED) }
    }





/*



*/

}




