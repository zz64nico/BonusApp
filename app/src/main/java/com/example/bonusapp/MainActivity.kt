package com.example.bonusapp

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bonusapp.adapter.BonusAdapter
import com.example.bonusapp.adapter.IBonusListener
import com.example.bonusapp.base.BaseActivity
import com.example.bonusapp.dao.BonusDao
import com.example.bonusapp.database.BonusDadaBase
import com.example.bonusapp.databinding.ActivityMainBinding
import com.example.bonusapp.entity.Bonus
import com.example.bonusapp.widget.MyItemDecoration
import java.sql.Date
import java.text.SimpleDateFormat
import kotlin.concurrent.thread


class MainActivity : BaseActivity<ActivityMainBinding>(),IBonusListener{

    lateinit var  bonusAdapter : BonusAdapter

    var bonusDao :BonusDao ? =null;
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initData() {
        bonusAdapter = BonusAdapter(this)
        mBinding?.rlBonus?.layoutManager = LinearLayoutManager(this)
        mBinding?.rlBonus?.addItemDecoration(MyItemDecoration(10))
        mBinding?.rlBonus?.adapter = bonusAdapter;
        mBinding?.add?.setOnClickListener {
            startActivity(Intent(this@MainActivity,RemiderFragment::class.java))
        }

        var have  = true
        thread {
           var list =  BonusDadaBase.getDatabase(this).getBonusDao().queryAllBonus()
            bonusAdapter.submitList(list)
            bonusAdapter.notifyDataSetChanged()
            if (list!=null && list.size > 0){
                for (l in list){
                    val time = getCurrentTime();
                    if (l.time.contains(time) && have){
                        have = false
                        Toast.makeText(applicationContext,"You have news",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        runOnUiThread(Runnable {
            var list =  BonusDadaBase.getDatabase(this).getBonusDao().queryAllBonus()
            bonusAdapter.submitList(list)
            bonusAdapter.notifyDataSetChanged()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about->{
                startActivity(Intent(this@MainActivity,WebActivity::class.java))
            }
            R.id.feedback->{
                openGmail()
            }
        }
        return true
    }

    fun openGmail() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("mailto:") // 使用mailto协议打开邮箱
        intent.setClassName(
            "com.google.android.gm",
            "com.google.android.gm.ComposeActivityGmail"
        ) // 设置Gmail应用程序的包名和类名
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com")) // 设置收件人邮箱地址
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello") // 设置邮件主题
        intent.putExtra(Intent.EXTRA_TEXT, "This is the body of the email") // 设置邮件正文
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // 处理Gmail应用程序未安装的情况
            Toast.makeText(this, "Gmail app is not installed", Toast.LENGTH_SHORT).show()
        }
    }

    // 定义一个名为getCurrentTime的函数，返回类型为String
    fun getCurrentTime(): String {
        // 创建SimpleDateFormat对象，指定日期时间格式为"年/月/日 时:分"
        val formatter = SimpleDateFormat("yyyy/MM/DD")
        // 创建Date对象，表示当前时间
        val curDate = Date(System.currentTimeMillis())
        // 使用formatter格式化curDate，并赋值给str变量
        val str: String = formatter.format(curDate)
        // 返回格式化后的时间字符串
        return str;
    }
    override fun onDeleteBonus(bonus: Bonus) {
        AlertDialog.Builder(this).apply {
            setTitle("tips")
            setMessage("delete it ?")
            setPositiveButton("confirm") { dialog, _ ->
                runOnUiThread(Runnable {
                    BonusDadaBase.getDatabase(applicationContext).getBonusDao().deleteWords(bonus)
                    var list =  BonusDadaBase.getDatabase(applicationContext).getBonusDao().queryAllBonus()
                    bonusAdapter.submitList(list)
                    bonusAdapter.notifyDataSetChanged()
                })
                //点击了确认按钮
                dialog.dismiss()
            }
            setNegativeButton("cancel") { dialog, _ ->
                //点击了取消按钮
                dialog.dismiss()
            }
            create()
            show()
        }
    }

}