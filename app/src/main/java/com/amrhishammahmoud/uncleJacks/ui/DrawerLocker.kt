package com.amrhishammahmoud.uncleJacks.ui

//to hide navigation drawer from fragment you want to hide
interface DrawerLocker {
    fun setDrawerLocked(shouldLock: Boolean)
}