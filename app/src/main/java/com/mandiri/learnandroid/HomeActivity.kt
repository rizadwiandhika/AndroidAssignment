package com.mandiri.learnandroid

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_ADDRESS
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_AGE
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_GENDER
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_NAME
import com.mandiri.learnandroid.adapter.EWalletAdapter
import com.mandiri.learnandroid.databinding.ActivityHomeBinding
import com.mandiri.learnandroid.databinding.ActivityRegisterBinding
import com.mandiri.learnandroid.model.EWalletModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var eWalletAdapter = EWalletAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dummyData: MutableList<EWalletModel> = createEWalletDummyList()

        binding.componentHomeEWallet.rvEWallet.adapter = eWalletAdapter
        eWalletAdapter.setDataEWallet(dummyData)
        eWalletAdapter.setOnButtonClick { selectedWallet ->
            Log.d("DEBUGGING", "Button clicked for ${selectedWallet.name}")
            dummyData.forEach { w ->
                if (w.name == selectedWallet.name) {
                    w.isConnected = true
                    w.image = R.drawable.money_bag
                    w.balance = 100000.0
                }
            }
//            eWalletAdapter.setDataEWallet(dummyData)
        }
    }

    private fun createEWalletDummyList(): MutableList<EWalletModel> {
        return mutableListOf(
            EWalletModel(
                name = "Gopay",
                image = R.drawable.money_bag,
                balance = 100000.0,
                isConnected = true
            ),
            EWalletModel(
                name = "LinkAja",
                image = R.drawable.money_bag,
                balance = 200000.0,
                isConnected = true
            ),
            EWalletModel(
                name = "Dana",
                image = R.drawable.baseline_visibility_24,
                balance = 0.0,
                isConnected = false
            ),
            EWalletModel(
                name = "SPay",
                image = R.drawable.baseline_email_24,
                balance = 0.0,
                isConnected = false
            ),
        )
    }

}