package com.example.unclejacks.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unclejacks.R
import com.example.unclejacks.activities.ContentActivity
import com.example.unclejacks.activities.MainActivity
import com.example.unclejacks.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.signInBtn.setOnClickListener {
            if(binding.editTextTextEmailAddress.text.isEmpty() || binding.editTextTextPassword.text.isEmpty() || binding.editTextPhoneNumber.text.isEmpty()) {
                Toast.makeText(requireContext(),"Please Enter The Empty Square", Toast.LENGTH_LONG).show()
            }else{
                //to make shared preferences to save data
                saveLoginDataBySharedPreferences()



                auth.signInWithEmailAndPassword(
                    binding.editTextTextEmailAddress.text.toString().trim(),
                    binding.editTextTextPassword.text.toString().trim()
                )
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(
                                requireContext(),
                                "signInWithEmail:success",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            val user = auth.currentUser
                            val intent = Intent(activity, ContentActivity::class.java)
                            startActivity(intent)
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                requireContext(),
                                "signInWithEmail:failure",
                                Toast.LENGTH_LONG
                            )
                                .show()

                            updateUI(null)
                        }
                    }
            }
        }

        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpWithEmailAddressFragment)
            //to put title on toolbar
            (activity as MainActivity).supportActionBar?.title = getString(R.string.signUp_fragment)
        }

        /***********************************************************************/
        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        // [END config_signin]

        googleSignInClient.signOut()
        binding.googleBtn.setOnClickListener {
            binding.editTextTextPassword.visibility=View.GONE
            binding.editTextTextEmailAddress.visibility=View.GONE
            binding.signInBtn.visibility=View.GONE
            binding.textView2.visibility=View.GONE

            if (binding.editTextPhoneNumber.text.isNotEmpty()) {

                signIn()
            }else {
                Toast.makeText(
                    requireContext(),
                    "Please Enter your Phone Number and try again",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            saveLoginDataBySharedPreferencesWhenAlreadyExist(currentUser.email.toString())
            val intent = Intent(activity,ContentActivity::class.java)
            startActivity(intent)
        }
        updateUI(currentUser)
    }


    private fun updateUI(user: FirebaseUser?) {

    }

    /***********************************************************************/
    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        requireActivity(),
                        "signInWithCredential:success",
                        Toast.LENGTH_LONG
                    ).show()
                    val user = auth.currentUser
                    //to save data login
                    saveLoginDataBySharedPreferencesWhenAlreadyExist(user?.email.toString())
                    val intent = Intent(activity, ContentActivity::class.java)
                    startActivity(intent)
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        requireActivity(),
                        "signInWithCredential:failure",
                        Toast.LENGTH_LONG
                    ).show()
                    updateUI(null)
                }

    }
    }
    // [END auth_with_google]

    // [START signin]
    private fun signIn() {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

    }
    // [END signin]


    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

private fun saveLoginDataBySharedPreferences(){
    val sharedPreferences : SharedPreferences = context!!.getSharedPreferences("myData",Context.MODE_PRIVATE)
    val editor =sharedPreferences.edit()
    editor.putString("phone",binding.editTextPhoneNumber.text.toString().trim())
    editor.putString("email",binding.editTextTextEmailAddress.text.toString().trim())
    editor.apply()
}
    private fun saveLoginDataBySharedPreferencesWhenAlreadyExist(x : String){
        val sharedPreferences : SharedPreferences = context!!.getSharedPreferences("myData",Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString("phone",binding.editTextPhoneNumber.text.toString().trim())
        editor.putString("email",x)
        editor.apply()
    }

}