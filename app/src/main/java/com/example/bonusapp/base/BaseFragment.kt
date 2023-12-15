package com.example.bonusapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Fragment 基类
 * @param <VB> VB extends ViewDataBinding 泛型集成，意思为：VB 可以是ViewBindings的任何子类，此处为使用viewBinding
</VB> */
abstract class BaseFragment<VB : ViewDataBinding?> : Fragment() {
    protected var mBinding: VB? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        state: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding!!.lifecycleOwner = this
        initData()
        return mBinding!!.root
    }

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
     * @param msg
     */
    protected fun showToast(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 展示toast 引用string.xml下定义字符
     * @param msg
     */
    protected fun showToast(@StringRes msg: Int) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}