package com.example.unclejacks.fragments


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.unclejacks.R
import com.example.unclejacks.activities.ContentActivity
import com.example.unclejacks.databinding.FragmentAccountBottomNavBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragmentBottomNav : Fragment() {

    private lateinit var binding : FragmentAccountBottomNavBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_account_bottom_nav,container,false)
        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = "Profile"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //to make shared preferences to restore data
        restoreDataBySharedPreferences()


        binding.apply {
            callingBtn.setOnClickListener {
                callUsLayout.visibility=View.VISIBLE
                imageView2.visibility=View.GONE
                emailAddress.visibility=View.GONE
                phoneNumber.visibility=View.GONE
                textView4.visibility=View.GONE
                textView5.visibility=View.GONE
                textView6.visibility=View.GONE
                textView7.visibility=View.GONE
                textView8.visibility=View.GONE
                termsConditionsBtn.visibility=View.GONE
                aboutUsBtn.visibility=View.GONE
                callingBtn.visibility=View.GONE

                //to make dial
             binding.callCustomerBtn.setOnClickListener {
                 val numberPhone = "01020523601"
                 val intent = Intent()
                 intent.action = Intent.ACTION_DIAL
                 val uri = Uri.parse("tel:$numberPhone")
                 intent.data = uri
                 startActivity(intent)
             }

                //to open whatsApp
                binding.whatsAppBtn.setOnClickListener {
                    val numberPhone = "01020523601"
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    val url = "https://api.whatsapp.com/send?phone=$numberPhone"
                    val uri = Uri.parse(url)
                    intent.data = uri
                    startActivity(intent)
                }

                //to open facebook
                binding.facebookBtn.setOnClickListener {

                    //Go to https://graph.facebook.com/<user_name_here>
                    val id = "A_94lyRt4nSCwWQBL_EO99f"
                    val userName = "mangoismailawy"
                    try {
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/$id"))
                        startActivity(intent)
                    } catch (e: Exception) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.facebook.com/$userName")
                            )
                        )
                    }
                    

                }
            }

            termsConditionsBtn.setOnClickListener {
                termsAndConditionsLayout.visibility=View.VISIBLE
                imageView2.visibility=View.GONE
                emailAddress.visibility=View.GONE
                phoneNumber.visibility=View.GONE
                textView4.visibility=View.GONE
                textView5.visibility=View.GONE
                textView6.visibility=View.GONE
                textView7.visibility=View.GONE
                textView8.visibility=View.GONE
                callingBtn.visibility=View.GONE
                aboutUsBtn.visibility=View.GONE
                termsConditionsBtn.visibility=View.GONE

            }

            aboutUsBtn.setOnClickListener {
                aboutUsLayout.visibility=View.VISIBLE
                imageView2.visibility=View.GONE
                emailAddress.visibility=View.GONE
                phoneNumber.visibility=View.GONE
                textView4.visibility=View.GONE
                textView5.visibility=View.GONE
                textView6.visibility=View.GONE
                textView7.visibility=View.GONE
                textView8.visibility=View.GONE
                termsConditionsBtn.visibility=View.GONE
                callingBtn.visibility=View.GONE
                aboutUsBtn.visibility=View.GONE

            }


            backBtnAccountCallUS.setOnClickListener {
                callUsLayout.visibility=View.GONE
                imageView2.visibility=View.VISIBLE
                emailAddress.visibility=View.VISIBLE
                phoneNumber.visibility=View.VISIBLE
                textView4.visibility=View.VISIBLE
                textView5.visibility=View.VISIBLE
                textView6.visibility=View.VISIBLE
                textView7.visibility=View.VISIBLE
                textView8.visibility=View.VISIBLE
                termsConditionsBtn.visibility=View.VISIBLE
                aboutUsBtn.visibility=View.VISIBLE
                callingBtn.visibility=View.VISIBLE

            }

            backBtnAccountAboutUs.setOnClickListener {
                aboutUsLayout.visibility=View.GONE
                imageView2.visibility=View.VISIBLE
                emailAddress.visibility=View.VISIBLE
                phoneNumber.visibility=View.VISIBLE
                textView4.visibility=View.VISIBLE
                textView5.visibility=View.VISIBLE
                textView6.visibility=View.VISIBLE
                textView7.visibility=View.VISIBLE
                textView8.visibility=View.VISIBLE
                termsConditionsBtn.visibility=View.VISIBLE
                callingBtn.visibility=View.VISIBLE
                aboutUsBtn.visibility=View.VISIBLE

            }

            backBtnAccountTerms.setOnClickListener {
                termsAndConditionsLayout.visibility=View.GONE
                imageView2.visibility=View.VISIBLE
                emailAddress.visibility=View.VISIBLE
                phoneNumber.visibility=View.VISIBLE
                textView4.visibility=View.VISIBLE
                textView5.visibility=View.VISIBLE
                textView6.visibility=View.VISIBLE
                textView7.visibility=View.VISIBLE
                textView8.visibility=View.VISIBLE
                callingBtn.visibility=View.VISIBLE
                aboutUsBtn.visibility=View.VISIBLE
                termsConditionsBtn.visibility=View.VISIBLE

            }


        }


    }


private fun restoreDataBySharedPreferences(){
    val sharedPreferences : SharedPreferences = context!!.getSharedPreferences("myData",
        Context.MODE_PRIVATE)
    val phoneNum = sharedPreferences.getString("phone","")
    val emailAdd = sharedPreferences.getString("email","")
    binding.phoneNumber.text = phoneNum
    binding.emailAddress.text = emailAdd

}

}