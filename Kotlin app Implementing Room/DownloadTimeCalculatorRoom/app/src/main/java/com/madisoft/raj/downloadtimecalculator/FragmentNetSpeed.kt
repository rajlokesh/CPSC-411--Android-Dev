package com.madisoft.raj.downloadtimecalculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.text.DateFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.madisoft.raj.downloadtimecalculator.databinding.FragmentNetSpeedBinding
import kotlinx.android.synthetic.main.fragment_net_speed.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


class FragmentNetSpeed : Fragment() {

    var prev =""

    companion object {

        fun newInstance(): FragmentNetSpeed {
            return FragmentNetSpeed()
        }
    }

    private lateinit var historyViewModel: HistoryViewModel

    private lateinit var vModel: FragmentNetSpeedViewModel


    @SuppressLint("NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        vModel = ViewModelProviders.of(this).get(FragmentNetSpeedViewModel::class.java)


        val holder: FragmentNetSpeedBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_net_speed, container, false)
        holder.viewModel = vModel
        val recyclerView = holder.recyclerView
        val adapter = HistoryListAdapter(inflater.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(inflater.context)

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        historyViewModel.allHistorys.observe(this, Observer { history ->

            history?.let { adapter.setHistorys(it) }
        })

        holder.button.setOnClickListener {
            if (!holder.result.text.isNullOrBlank()) {
                val string= DateFormat.getDateTimeInstance().format(Date()) + "\t\t" + "Downloaded " + holder.fileSizeEditText.text + " MiB @ " + holder.speedEditText.text + " Mbps in " + holder.result.text.substring(19)
                val history = History(string)
                if(!string.equals(prev)) {
                    prev=string
                    historyViewModel.insert(history)
                }

            }

        }
        holder.buttonClear.setOnClickListener {

            historyViewModel.deleteAll()
        }
        //val holder= inflater?.inflate(R.layout.fragment_net_speed, container, false)
        //holder.speedEditText.onTextChange(holder.root)
        //holder.fileSizeEditText.onTextChange(holder.root)


        return holder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vModel = ViewModelProviders.of(this).get(FragmentNetSpeedViewModel::class.java)
        // TODO: Use the ViewModel
    }


    /*private fun EditText.onTextChange(view: View) {

        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (view.speedEditText.text.isNotBlank() && view.fileSizeEditText.text.isNotBlank()) {
                    if (Integer.parseInt(view.speedEditText.text.toString()) < 0 && Integer.parseInt(view.fileSizeEditText.text.toString()) < 0) {
                        //view.result.setTextColor(Color.RED)
                        view.result.text = null
                    } else {
                        val calculatedTime = BigDecimal.valueOf(8.388608 * java.lang.Double.parseDouble(view.fileSizeEditText.text.toString()) / java.lang.Double.parseDouble(view.speedEditText.text.toString())
                        )
                                .setScale(1, RoundingMode.HALF_UP)
                                .toDouble()
                        view.result.setTextColor(Color.BLACK)
                        view.result.text = "Min transfer time = $calculatedTime sec"
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }*/
}