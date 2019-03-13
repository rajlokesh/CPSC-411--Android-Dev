package com.madisoft.raj.downloadtimecalculator

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_net_speed.view.*
import java.math.BigDecimal
import java.math.RoundingMode

class FragmentNetSpeed : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val holder= inflater?.inflate(R.layout.fragment_net_speed, container, false)
        holder.speedEditText.onTextChange(holder)
        holder.fileSizeEditText.onTextChange(holder)

        return holder
    }


    companion object {

        fun newInstance(): FragmentNetSpeed {
            return FragmentNetSpeed()
        }
    }
    private fun EditText.onTextChange(view: View) {

        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (view.speedEditText.text.isNotBlank() && view.fileSizeEditText.text.isNotBlank()) {
                    if (Integer.parseInt(view.speedEditText.text.toString()) == 0) {
                        view.result.setTextColor(Color.RED)
                        view.result.text = "Did you pay your Internet bills?"
                    } else {
                        val calculatedTime = BigDecimal.valueOf(8.388608 * java.lang.Double.parseDouble(view.fileSizeEditText.text.toString()) / java.lang.Double.parseDouble(view.speedEditText.text.toString())
                        )
                                .setScale(1, RoundingMode.HALF_UP)
                                .toDouble()
                        view.result.setTextColor(Color.BLACK)
                        view.result.text = "Min transfer time = $calculatedTime seconds"
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}