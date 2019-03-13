package com.madisoft.raj.downloadtimecalculator

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode


class FragmentNetSpeedViewModel : ViewModel() {

    val speed = ObservableField<String>("")
    val filesize = ObservableField<String>("")

    val transTime = ObservableField<String>("")

    fun onTextChanged(type: Int, text: String) {


        if (type == 1)
            speed.set(text)
        else if (type == 2)
            filesize.set(text)


        if (!speed.get().isNullOrBlank() && !filesize.get().isNullOrBlank()) {
            if(speed.get()!!.toDouble()>0)
            transTime.set("Min transfer time =" +
                    BigDecimal.valueOf(8.388608 * filesize.get()!!.toDouble() / speed.get()!!.toDouble())
                            .setScale(1, RoundingMode.HALF_UP)
                            .toDouble() + " sec")


        }
        else
            transTime.set("")






        Log.w("fragempty", "eee")
        Log.w("frag", text + type + "hi")

    }

}
