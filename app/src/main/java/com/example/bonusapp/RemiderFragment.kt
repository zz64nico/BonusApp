package com.example.bonusapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.TextUtils
import android.view.KeyEvent
import android.widget.DatePicker
import android.widget.Toast
import com.example.bonusapp.base.BaseActivity
import com.example.bonusapp.database.BonusDadaBase
import com.example.bonusapp.databinding.ActivityEditBinding
import com.example.bonusapp.entity.Bonus
import java.lang.System.exit
import java.util.Calendar


class RemiderFragment:BaseActivity<ActivityEditBinding>() , DatePickerDialog.OnDateSetListener{


    var timepiker:String? =null
    var datepiker:String? =null

    override val layoutId: Int
        get() = R.layout.activity_edit

    override fun initData() {
        mBinding?.btnTime?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, this, 2000, 1, 1)
            datePickerDialog.show()
        }

        mBinding?.btnDate?.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(this,
            { view, hourOfDay, minute -> // 处理选择的时间
                val selectedTime = "$hourOfDay:$minute"
                timepiker = selectedTime
            }, hour, minute, true
        )
        timePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var  m = view?.getMonth()!!+1;
        var d =  view?.getDayOfMonth()!!;
        var mm = ""
        var dd = "";
        if (m < 10){
            mm = "0"+m;
        }
        if (d < 10){
            dd = "0"+d;
        }
        datepiker = view?.getYear()!!.toString() + "/" + mm +"/"+dd;
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    fun exit(){
        AlertDialog.Builder(this).apply {
            setTitle("tips")
            setMessage("save?")
            setPositiveButton("confirm") { dialog, _ ->
                //点击了确认按钮
                val title = mBinding?.etTitle?.text.toString()
                val description = mBinding?.etDescription?.text.toString()
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
                    Toast.makeText(applicationContext,"please input",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }else if (TextUtils.isEmpty(timepiker) || TextUtils.isEmpty(datepiker)){
                    android.widget.Toast.makeText(applicationContext,"please choose time",
                        android.widget.Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }else{
                    val bonus = Bonus(title,description,datepiker+" "+timepiker)
                    BonusDadaBase.getDatabase(applicationContext).getBonusDao().insertBonus(bonus)
                    dialog.dismiss()
                    finish()
                }

            }
            setNegativeButton("cancel") { dialog, _ ->
                //点击了取消按钮
                dialog.dismiss()
                finish()
            }
            create()
            show()
        }
    }

}