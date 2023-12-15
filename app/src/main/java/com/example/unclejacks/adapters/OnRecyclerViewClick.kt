package com.example.unclejacks.adapters

import com.example.unclejacks.R

interface OnRecyclerViewClick {
    fun onClickedRecyclerView (position :Int)
    fun onClickedAddCartBtn (position :Int)
    fun onClickedFavoriteBtn(position :Int)
}