package com.eugene.androidpractice.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.IdRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

import javax.inject.Inject

class NavigationController @Inject
constructor(val activity: FragmentActivity) : Navigator {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    override fun finishActivity() {
        activity.finish()
    }

    override fun startActivity(activityClass: Class<out Activity>) {
        startActivityNavigation(activityClass, null, null, false)
    }

    override fun startActivity(activityClass: Class<out Activity>, args: Bundle?, newTask: Boolean) {
        startActivityNavigation(activityClass, args, null, newTask)
    }

    override fun startActivity(activityClass: Class<out Activity>, args: Bundle) {
        startActivityNavigation(activityClass, args, null, false)
    }

    override fun startActivityForResult(activityClass: Class<out Activity>, requestCode: Int) {
        startActivityNavigation(activityClass, null, requestCode, false)
    }

    override fun startActivityForResult(activityClass: Class<out Activity>, requestCode: Int, args: Bundle) {
        startActivityNavigation(activityClass, args, requestCode, false)
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        activity.startActivityForResult(intent, requestCode)
    }

    override fun replaceFragment(containerId: Int, fragment: Fragment, args: Bundle?) {
        replaceFragmentNavigation(containerId, fragment, null, args, false, null, null, null, null, null)
    }

    override fun replaceFragment(containerId: Int, fragment: Fragment, fragmentTag: String, args: Bundle?) {
        replaceFragmentNavigation(containerId, fragment, fragmentTag, args, false, null, null, null, null, null)
    }

    override fun replaceFragmentBackStack(containerId: Int, fragment: Fragment, fragmentTag: String?, args: Bundle?) {
        replaceFragmentNavigation(containerId, fragment, fragmentTag, args, true, null, null, null, null, null)
    }

    override fun replaceFragmentBackStack(containerId: Int, fragment: Fragment, fragmentTag: String, args: Bundle?, backstackTag: String?) {
        replaceFragmentNavigation(containerId, fragment, fragmentTag, args, true, backstackTag, null, null, null, null)
    }

    override fun replaceFragmentBackStack(containerId: Int, fragment: Fragment, fragmentTag: String, args: Bundle?, backstackTag: String?,
                                          enterAnimation: Int, exitAnimation: Int, popEnterAnimation: Int, popExitAnimation: Int) {
        replaceFragmentNavigation(containerId, fragment, fragmentTag, args, true, backstackTag,
                enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
    }

    override fun showDialog(dialogFragment: DialogFragment) {
        dialogFragment.show(activity.supportFragmentManager, dialogFragment.tag)
    }

    override fun clearBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun popupBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }

    override fun popupBackStackInclusive(fragmentTag: String) {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun startActivityNavigation(activityClass: Class<out Activity>, args: Bundle?, requestCode: Int?, newTask: Boolean) {
        val intent = Intent(activity, activityClass)

        if (args != null)
            intent.putExtras(args)

        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        if (requestCode != null)
            activity.startActivityForResult(intent, requestCode)
        else
            activity.startActivity(intent)
    }

    private fun replaceFragmentNavigation(@IdRes containerId: Int, fragment: Fragment, fragmentTag: String?, args: Bundle?, addToBackstack: Boolean, backstackTag: String?,
                                          @AnimRes enterAnimation: Int?, @AnimRes exitAnimation: Int?, @AnimRes popEnterAnimation: Int?, @AnimRes popExitAnimation: Int?) {
        var frag = fragment
        var fragTag = fragmentTag
        var _addToBackStack = addToBackstack

        if (fragmentTag != null) {
            if (isFragmentTheSame(getCurrentFragment(containerId), fragment))
                return
            if (fragmentManager.findFragmentByTag(fragmentTag) != null) {
                frag = fragmentManager.findFragmentByTag(fragmentTag)
                fragTag = frag.tag
                _addToBackStack = false
            }
        }

        if (args != null) {
            frag.arguments = args
        }

        val ft = fragmentManager.beginTransaction()
        if (enterAnimation != null && exitAnimation != null && popEnterAnimation != null && popExitAnimation != null)
            ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        else if (enterAnimation != null && exitAnimation != null)
            ft.setCustomAnimations(enterAnimation, exitAnimation)
        ft.replace(containerId, frag, fragTag)
        if (_addToBackStack) {
            ft.addToBackStack(backstackTag).commit()
            fragmentManager.executePendingTransactions()
        } else {
            ft.commitAllowingStateLoss()
        }
    }

    private fun isFragmentTheSame(current: Fragment?, newFragment: Fragment): Boolean {
        return current != null && newFragment.javaClass == current.javaClass
    }

    private fun getCurrentFragment(@IdRes containerId: Int): Fragment? {
        return fragmentManager.findFragmentById(containerId)
    }
}
