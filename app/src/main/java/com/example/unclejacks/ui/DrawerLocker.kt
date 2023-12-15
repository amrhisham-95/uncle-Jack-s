package com.example.unclejacks.ui

//to hide navigation drawer from fragment you want to hide
interface DrawerLocker {
    fun setDrawerLocked(shouldLock: Boolean)
}