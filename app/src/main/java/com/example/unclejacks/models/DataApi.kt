package com.example.unclejacks.models

import com.example.unclejacks.R

val fruitsName = arrayOf(
    "Red Apple",
    "Avocado",
    "Green Apple",
    "Coconut",
    "Yellow Apple",
    "Pineapple",
    "Banana",
    "Harankash",
    "Guava",
    "Kiwi",
    "Peach",
    "Pear",
    "Plum",
    "Pomegranate"
)


val fruitsPrice = arrayOf(
    "75",
    "80",
    "85",
    "40",
    "40",
    "60",
    "25",
    "35",
    "22",
    "45",
    "68",
    "35",
    "38",
    "70"
)

val fruitsPic = arrayOf(
    R.drawable.redapple,
    R.drawable.avocadopic,
    R.drawable.greenapple,
    R.drawable.coconut,
    R.drawable.yellowapple,
    R.drawable.pineapple,
    R.drawable.banana,
    R.drawable.harankash,
    R.drawable.guava,
    R.drawable.kiwi,
    R.drawable.peach,
    R.drawable.pear,
    R.drawable.plum,
    R.drawable.pomegranatepic
)


/*************************************************************************************/


val JuicesName = arrayOf(
 "Guava",
    "Apple",
    "Avocado",
    "Banana",
    "Coconut",
    "Fahm","Cantaloupe",
    "Kiwi","Lemon","Mango",
    "Orange","Pineapple","Watermelon"
)


val JuicesPrice = arrayOf(
    "30",
    "40",
    "90",
    "40",
    "80",
    "60",
    "30",
    "55",
    "40",
    "40",
    "30",
    "100",
    "40"
)

val JuicesPic = arrayOf(
    R.drawable.juavajuice,
    R.drawable.applejuice,
    R.drawable.avocadojuice,
    R.drawable.bananajuice,
    R.drawable.coconutjuice,
    R.drawable.fahmjuice,
    R.drawable.kantlopjuice,
    R.drawable.kiwijuice,
    R.drawable.lemonjuice,
    R.drawable.mangojuice,
    R.drawable.orangejuice,
    R.drawable.pineapplejuice,
    R.drawable.watermelonejuice
)
/******************************************************************/


object DataFruitsApiObject{

    suspend fun getFruitName() : Array<String>{
        return  fruitsName
    }
    suspend fun getFruitPrice() : Array<String>{
        return  fruitsPrice
    }
    suspend fun getFruitPic() : Array<Int>{
        return  fruitsPic
    }


    suspend fun getJuiceName() : Array<String>{
        return  JuicesName
    }
    suspend fun getJuicePrice() : Array<String>{
        return  JuicesPrice
    }
    suspend fun getJuicePic() : Array<Int>{
        return  JuicesPic
    }

}