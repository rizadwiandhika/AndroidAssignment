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
import com.mandiri.learnandroid.adapter.SavingDepositAdapter
import com.mandiri.learnandroid.databinding.ActivityHomeBinding
import com.mandiri.learnandroid.databinding.ActivityRegisterBinding
import com.mandiri.learnandroid.model.EWalletModel
import com.mandiri.learnandroid.model.SavingDepositModel

class HomeActivity : AppCompatActivity() {

    private val eWalletAdapter = EWalletAdapter()
    private val savingDepositAdapter = SavingDepositAdapter(false)

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        renderEWallet()
        renderSavingDeposit()
    }

    private fun renderSavingDeposit() {
        val data = createSavingDepositDummyList()
        savingDepositAdapter.setListSavingDeposit(data)

        binding.componentSavingDeposit.layoutToggleSavingList.setOnClickListener {
            savingDepositAdapter.toggleViewAllSaving()
        }
    }

    private fun renderEWallet() {
        val dummyData: MutableList<EWalletModel> = createEWalletDummyList()

        binding.componentHomeEWallet.rvEWallet.adapter = eWalletAdapter
        eWalletAdapter.setDataEWallet(dummyData)
        eWalletAdapter.setOnButtonClick { selectedWallet ->
            Log.d("MARKER", "Button clicked for ${selectedWallet.name}")
            dummyData.forEach { w ->
                if (w.name == selectedWallet.name) {
                    w.isConnected = true
                    w.image = R.drawable.money_bag
                    w.balance = 100000.0
                }
            }
            eWalletAdapter.setDataEWallet(dummyData)
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

    private fun createSavingDepositDummyList(): MutableList<SavingDepositModel> {
        return mutableListOf(
            SavingDepositModel(
                savingName = "Tabungan Now",
                accountNumber = "111111111",
                image = R.drawable.saving_card,
                balance = 100000
            ),
            SavingDepositModel(
                savingName = "Tabungan Now",
                accountNumber = "222222222",
                image = R.drawable.saving_card,
                balance = 200000
            ),
            SavingDepositModel(
                savingName = "Tabungan Now",
                accountNumber = "333333333",
                image = R.drawable.saving_card,
                balance = 300000
            ),
            SavingDepositModel(
                savingName = "Tabungan Now",
                accountNumber = "444444444",
                image = R.drawable.saving_card,
                balance = 400000
            ),
            SavingDepositModel(
                savingName = "Tabungan Now",
                accountNumber = "555555555",
                image = R.drawable.saving_card,
                balance = 500000
            ),
        )
    }

}