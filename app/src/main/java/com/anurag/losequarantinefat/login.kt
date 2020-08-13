package com.anurag.losequarantinefat

import android.R.id.message
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.anurag.losequarantinefat.localstorage.StorageSP
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class login : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN = 1
    val TAG = "============= login"

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        auth = Firebase.auth



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

        signOut()

        if (!auth.currentUser?.displayName.isNullOrEmpty()){
            Google_signIn_button.visibility = GONE
            progressBar.visibility = VISIBLE
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
            },500)
        }


        Google_signIn_button.setOnClickListener {
            Google_signIn_button.visibility = GONE
            progressBar.visibility = VISIBLE

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
                imageView2.setImageResource(R.drawable.batman)
                val colorValue = ContextCompat.getColor(this, R.color.batman_bg)
                whole_screen_loginact.setBackgroundColor(colorValue)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                updateUI(null)
                imageView2.setImageResource(R.drawable.spidermanfat)
                val colorValue = ContextCompat.getColor(this, R.color.spiderman_bg)
                whole_screen_loginact.setBackgroundColor(colorValue)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                    updateUI(null)
                }
            }
    }
    private fun signOut() {
        // Firebase sign out
        auth.signOut()

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            Log.d(TAG,"sign our sucks us")
        }
    }
    private fun updateUI(account: FirebaseUser?) {
        val sharedPreferences = StorageSP(this)
        Google_signIn_button.visibility =  VISIBLE
        progressBar.visibility = GONE
        var x =1
        Log.i(TAG, "UpdateUi-${x}")
       x+=1
        if (account != null) {
            Log.i(TAG, account.email.toString())
            Log.i(TAG, account.photoUrl.toString())
            Log.i(TAG, account.displayName.toString())
            sharedPreferences!!.saveLoginInfo( account.displayName.toString(), account.photoUrl.toString(), account.email.toString())
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("photoUrl", account.photoUrl.toString());
            intent.putExtra("email", account.email.toString());
            intent.putExtra("displayName", account.displayName.toString());
            startActivity(intent)
            finish()


        }else{
            Log.d(TAG,"accounr is null")
        }
    }
}
