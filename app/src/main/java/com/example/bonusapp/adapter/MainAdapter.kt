package com.example.bonusapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

// 定义一个名为MainAdapter的类，继承自FragmentPagerAdapter类
// 构造函数接受一个FragmentManager对象和一个Fragment列表作为参数
class MainAdapter(fm: FragmentManager?, private val fragments: List<Fragment>) :
    FragmentPagerAdapter(
        fm!!
    ) {

    // 重写getItem方法，根据位置返回对应的Fragment对象
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    // 重写getCount方法，返回Fragment列表的大小
    override fun getCount(): Int {
        return fragments.size
    }
}