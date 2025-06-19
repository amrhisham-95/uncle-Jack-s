package com.amrhishammahmoud.uncleJacks.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.MyCartActivity
import com.amrhishammahmoud.uncleJacks.databinding.FragmentIcecreamTabLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class IcecreamTabLayoutFragment : Fragment() {

    private lateinit var binding: FragmentIcecreamTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_icecream_tab_layout,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinnerNoOfPieces()

        binding.apply {


            addToCartIcecreamBtn.setOnClickListener {

                if (binding.nameIcecreamText.text.toString()
                        .isNotEmpty() && binding.numOfPiecesIcecreamText.text.toString()
                        .isNotEmpty()
                ) {
                    val totalPieces = priceItemIcecream.text.toString()

                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", nameIcecreamText.text.toString())
                    intent.putExtra("priceItem", totalPieces)
                    intent.putExtra(
                        "picItem",
                        "https://th.bing.com/th/id/R.5ee62f1c835b081b3152969f3ba3701e?rik=J3GzEvoW3I%2fgSA&pid=ImgRaw&r=0"
                    )

                    intent.putExtra("typeItem", "icecream")

                    intent.putExtra(
                        "quantityItem",
                        binding.numTextIcecream.text.toString() + "  آيس كريم"
                    )
                    startActivity(intent)
                }
            }


        }
    }


    private fun initSpinnerName1() {
        val items = listOf(
            "اختر النكهة الأولى",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )

        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType1.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName2() {

        val items = listOf(
            "اختر النكهة الثانية",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType2.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"


                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }


    private fun initSpinnerName1Biscuit() {
        val items = listOf(
            "اختر النكهة الأولى",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )

        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType1.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]} (بسكويت)"
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName2Biscuit() {

        val items = listOf(
            "اختر النكهة الثانية",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType2.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]} (بسكويت)"


                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName3() {
        val items = listOf(
            "اختر النكهة الثالثة",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType3.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"


                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName4() {

        val items = listOf(
            "اختر النكهة الرابعة",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType4.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"


                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName5() {

        val items = listOf(
            "اختر النكهة الخامسة",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType5.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"


                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName6() {
        val items = listOf(
            "اختر النكهة السادسة",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType6.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName7() {

        val items = listOf(
            "اختر النكهة السابعة",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
        )

        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType7.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }

    private fun initSpinnerName8() {

        val items = listOf(
            "اختر النكهة الثامنة",
            "شوكليت",
            "مانجو",
            "أوريو",
            "توت بنفسجي",
            "حليب سادة",
            "فراولة",
            "زبادي فراولة",
            "نيسكافيه",
            "توت بري",
            "جوافة",
            "كراميل",
            "حليب فواكه",
            "يوسفي",
            "موز",
            "زبادي توت",
            "حليب زبيب",
            "ليمون نعناع",
            "بسبوسة",
            "بندق",
            "سنيكرز",
            "لوتس",
            "بلح",
            "تفاح",
            "فحم",
            "باونتي",
            "أناناس",
            "خوخ",
            "آيس كيك ش",
            "آيس كيك ف",
            "شانكي مانكي",
            "كرانشي"
            )

        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerIcecreamType8.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.nameIcecreamText.text = ""
                    } else {
                        binding.apply {
                            nameIcecreamText.text =
                                "${nameIcecreamText.text}-${items[p2]}"
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }


    private fun initSpinnerNoOfPieces() {

        val items = listOf(
            "اختر نوع طبق الآيس كريم",
            "نوعان آيس كريم -علبة-",
            "نوعان آيس كريم -بسكويت-",
            "خمس أنواع آيس كريم",
            "ثماني أنواع آيس كريم"
        )
        val myAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerNoOfPiecesIcecreamText.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (items[p2] == items[0]) {
                        binding.numOfPiecesIcecreamText.text = ""
                    } else {
                        binding.apply {
                            numTextIcecream.text = "1"
                            numOfPiecesIcecreamText.text = items[p2]


                            when (items[p2]) {
                                items[1] -> {
                                    priceItemIcecream.text = "15"

                                    if (binding.numTextIcecream.text.toString() == "1") {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.GONE
                                        priceItemIcecream.text = "15"
                                    }

                                    floatingActionButtonPlusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt + 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()

                                        val priceTotal = 15 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()
                                    }

                                    floatingActionButtonMinusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt - 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()


                                        val priceTotal = 15 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()


                                        if (binding.numTextIcecream.text.toString() == "1") {
                                            binding.floatingActionButtonMinusIcecream.visibility =
                                                View.GONE
                                            priceItemIcecream.text = "15"
                                        }
                                    }


                                    spinnerIcecreamType1.visibility = View.VISIBLE
                                    spinnerIcecreamType2.visibility = View.VISIBLE
                                    spinnerIcecreamType3.visibility = View.GONE
                                    spinnerIcecreamType4.visibility = View.GONE
                                    spinnerIcecreamType5.visibility = View.GONE
                                    spinnerIcecreamType6.visibility = View.GONE
                                    spinnerIcecreamType7.visibility = View.GONE
                                    spinnerIcecreamType8.visibility = View.GONE

                                    initSpinnerName1()
                                    initSpinnerName2()


                                }

                                items[2] -> {
                                    priceItemIcecream.text = "20"

                                    if (binding.numTextIcecream.text.toString() == "1") {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.GONE
                                        priceItemIcecream.text = "20"
                                    }

                                    floatingActionButtonPlusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt + 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()

                                        val priceTotal = 20 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()
                                    }

                                    floatingActionButtonMinusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt - 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()


                                        val priceTotal = 20 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()


                                        if (binding.numTextIcecream.text.toString() == "1") {
                                            binding.floatingActionButtonMinusIcecream.visibility =
                                                View.GONE
                                            priceItemIcecream.text = "20"
                                        }
                                    }


                                    spinnerIcecreamType1.visibility = View.VISIBLE
                                    spinnerIcecreamType2.visibility = View.VISIBLE
                                    spinnerIcecreamType3.visibility = View.GONE
                                    spinnerIcecreamType4.visibility = View.GONE
                                    spinnerIcecreamType5.visibility = View.GONE
                                    spinnerIcecreamType6.visibility = View.GONE
                                    spinnerIcecreamType7.visibility = View.GONE
                                    spinnerIcecreamType8.visibility = View.GONE

                                    initSpinnerName1Biscuit()
                                    initSpinnerName2Biscuit()


                                }

                                items[3] -> {
                                    priceItemIcecream.text = "30"

                                    if (binding.numTextIcecream.text.toString() == "1") {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.GONE
                                        priceItemIcecream.text = "30"
                                    }

                                    floatingActionButtonPlusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt + 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()

                                        val priceTotal = 30 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()
                                    }

                                    floatingActionButtonMinusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt - 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()


                                        val priceTotal = 30 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()


                                        if (binding.numTextIcecream.text.toString() == "1") {
                                            binding.floatingActionButtonMinusIcecream.visibility =
                                                View.GONE
                                            priceItemIcecream.text = "30"
                                        }
                                    }


                                    spinnerIcecreamType1.visibility = View.VISIBLE
                                    spinnerIcecreamType2.visibility = View.VISIBLE
                                    spinnerIcecreamType3.visibility = View.VISIBLE
                                    spinnerIcecreamType4.visibility = View.VISIBLE
                                    spinnerIcecreamType5.visibility = View.VISIBLE
                                    spinnerIcecreamType6.visibility = View.GONE
                                    spinnerIcecreamType7.visibility = View.GONE
                                    spinnerIcecreamType8.visibility = View.GONE

                                    initSpinnerName1()
                                    initSpinnerName2()
                                    initSpinnerName3()
                                    initSpinnerName4()
                                    initSpinnerName5()
                                }

                                items[4] -> {
                                    priceItemIcecream.text = "50"

                                    if (binding.numTextIcecream.text.toString() == "1") {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.GONE
                                        priceItemIcecream.text = "50"
                                    }

                                    floatingActionButtonPlusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt + 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()

                                        val priceTotal = 50 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()
                                    }

                                    floatingActionButtonMinusIcecream.setOnClickListener {
                                        binding.floatingActionButtonMinusIcecream.visibility =
                                            View.VISIBLE

                                        val noOfPiecesString = numTextIcecream.text.toString()
                                        val noOfPiecesInt = noOfPiecesString.toInt()
                                        val noOfPiecesIntFinal = noOfPiecesInt - 1

                                        numTextIcecream.text = noOfPiecesIntFinal.toString()


                                        val priceTotal = 50 * (noOfPiecesIntFinal)

                                        priceItemIcecream.text = priceTotal.toString()


                                        if (binding.numTextIcecream.text.toString() == "1") {
                                            binding.floatingActionButtonMinusIcecream.visibility =
                                                View.GONE
                                            priceItemIcecream.text = "50"
                                        }
                                    }



                                    spinnerIcecreamType1.visibility = View.VISIBLE
                                    spinnerIcecreamType2.visibility = View.VISIBLE
                                    spinnerIcecreamType3.visibility = View.VISIBLE
                                    spinnerIcecreamType4.visibility = View.VISIBLE
                                    spinnerIcecreamType5.visibility = View.VISIBLE
                                    spinnerIcecreamType6.visibility = View.VISIBLE
                                    spinnerIcecreamType7.visibility = View.VISIBLE
                                    spinnerIcecreamType8.visibility = View.VISIBLE

                                    initSpinnerName1()
                                    initSpinnerName2()
                                    initSpinnerName3()
                                    initSpinnerName4()
                                    initSpinnerName5()
                                    initSpinnerName6()
                                    initSpinnerName7()
                                    initSpinnerName8()
                                }

                            }

                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }
}