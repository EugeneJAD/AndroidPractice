package com.eugene.androidpractice.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.IdRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment

interface Navigator {

    fun finishActivity()
    fun startActivity(activityClass: Class<out Activity>)
    fun startActivity(activityClass: Class<out Activity>, args: Bundle)
    fun startActivityForResult(intent: Intent, requestCode: Int)
    fun startActivityForResult(activityClass: Class<out Activity>, requestCode: Int)
    fun startActivityForResult(activityClass: Class<out Activity>, requestCode: Int, args: Bundle)
    fun startActivity(activityClass: Class<out Activity>, args: Bundle?, newTask: Boolean)

    fun replaceFragment(@IdRes containerId: Int, fragment: Fragment, args: Bundle?)
    fun replaceFragment(containerId: Int, fragment: Fragment, fragmentTag: String, args: Bundle?)
    fun replaceFragmentBackStack(@IdRes containerId: Int, fragment: Fragment, fragmentTag: String?, args: Bundle?)
    fun replaceFragmentBackStack(@IdRes containerId: Int, fragment: Fragment, fragmentTag: String, args: Bundle?, backstackTag: String?)
    fun replaceFragmentBackStack(@IdRes containerId: Int, fragment: Fragment, fragmentTag: String, args: Bundle?, backstackTag: String?,
                                 @AnimRes enterAnimation: Int, @AnimRes exitAnimation: Int, @AnimRes popEnterAnimation: Int, @AnimRes popExitAnimation: Int)

    fun showDialog(dialogFragment: DialogFragment)
    fun clearBackStack()
    fun popupBackStack()
    fun popupBackStackInclusive(fragmentTag: String)
}
