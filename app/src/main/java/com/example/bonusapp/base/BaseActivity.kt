package com.example.bonusapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * activity 基类
 *
 * @param <VB> VB extends ViewDataBinding 泛型集成，意思为：VB 可以是ViewBindings的任何子类，此处为使用viewBinding
</VB> */
abstract class BaseActivity<VB : ViewDataBinding?> : AppCompatActivity() {
    protected var mBinding: VB? = null

    //loading 框
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding!!.lifecycleOwner = this
        initData()
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * 执行业务逻辑
     */
    protected abstract fun initData()
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: String?) {
        //你的逻辑
    }

    /**
     * 展示toast 输入文字字符
     *
     * @param msg
     */
    protected fun showToast(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 展示toast 引用string.xml下定义字符
     *
     * @param msg
     */
    protected fun showToast(@StringRes msg: Int) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        //销毁DataBinding
        if (mBinding != null) {
            mBinding!!.unbind()
        }
    }
}