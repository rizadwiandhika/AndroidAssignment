package com.mandiri.learnandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.R
import com.mandiri.learnandroid.adapter.EWalletAdapter
import com.mandiri.learnandroid.adapter.MenuHomeAdapter
import com.mandiri.learnandroid.adapter.SavingDepositAdapter
import com.mandiri.learnandroid.databinding.ActivityHomeBinding
import com.mandiri.learnandroid.helper.SharedPreferenceHelper
import com.mandiri.learnandroid.model.EWalletModel
import com.mandiri.learnandroid.model.MenuModel
import com.mandiri.learnandroid.model.SavingDepositModel

class HomeActivity constructor() : AppCompatActivity() {

    private var isShowAllSaving: Boolean = false
    private var isShowBalance: Boolean = false
    private val eWalletAdapter = EWalletAdapter()
    private val savingDepositAdapter = SavingDepositAdapter(isShowBalance, isShowAllSaving)
    private lateinit var menuHomeAdapter: MenuHomeAdapter
    private lateinit var binding: ActivityHomeBinding
    private lateinit var preferences: SharedPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (preferences.getToken().isEmpty()) {
            Toast.makeText(applicationContext, "Please log in!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        renderEWallet()
        renderSavingDeposit()
        renderMenuHome()

        binding.btnLogout.setOnClickListener {
            handleLogout()
        }
    }

    private fun renderMenuHome() {
        menuHomeAdapter = MenuHomeAdapter(createMenuDummyList())
        menuHomeAdapter.setOnMenuClickHandler { binding ->
            Toast.makeText(
                applicationContext,
                "Clicking ${binding.tvMenuTitle.text.toString()}!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.componentMenuHome.rvMenuHome.adapter = menuHomeAdapter
    }

    private fun renderSavingDeposit() {
        val accounts = createSavingDepositDummyList()
        binding.componentSavingDeposit.rvSavingDeposit.adapter = savingDepositAdapter

        savingDepositAdapter.setListSavingDeposit(accounts)
        savingDepositAdapter.setBalanceVisibility(isShowBalance)

        setDisplayAllSavingAccount(isShowAllSaving)

        binding.componentSavingDeposit.apply {
            if (accounts.size <= SavingDepositAdapter.MAX_ITEM) {
                llShowMore.visibility = View.GONE
                llShowLess.visibility = View.GONE
            } else {
                llShowMore.setOnClickListener {
                    setDisplayAllSavingAccount(true)
                }
                llShowLess.setOnClickListener {
                    setDisplayAllSavingAccount(false)
                }
            }

            tvToggleShowBalance.setOnClickListener {
                handleToggleShowBalance()
            }
        }

    }

    private fun handleLogout() {
        preferences.clearAll()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun renderEWallet() {
        val dummyData: MutableList<EWalletModel> = createEWalletDummyList()

        binding.componentHomeEWallet.rvEWallet.adapter = eWalletAdapter
        eWalletAdapter.setDataEWallet(dummyData)
        eWalletAdapter.setOnButtonClick { selectedWallet ->
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

    private fun setDisplayAllSavingAccount(value: Boolean) {
        isShowAllSaving = value
        savingDepositAdapter.setIsShowAll(isShowAllSaving)

        binding.componentSavingDeposit.apply {
            if (isShowAllSaving) {
                llShowMore.visibility = View.GONE
                llShowLess.visibility = View.VISIBLE
            } else {
                llShowLess.visibility = View.GONE
                llShowMore.visibility = View.VISIBLE
            }
        }
    }

    private fun handleToggleShowBalance() {
        isShowBalance = !isShowBalance
        savingDepositAdapter.setBalanceVisibility(isShowBalance)

        binding.componentSavingDeposit.tvToggleShowBalance.text =
            if (isShowBalance) "Hide balance" else "Show balance"
    }

    private fun createEWalletDummyList(): MutableList<EWalletModel> {
        return mutableListOf(
            EWalletModel(
                name = "Gopay", image = R.drawable.money_bag, balance = 100000.0, isConnected = true
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

    private fun createMenuDummyList(): MutableList<MenuModel> {
        return mutableListOf(
            MenuModel("Transfer", R.drawable.ic_transfer),
            MenuModel("Top Up", R.drawable.ic_transfer),
            MenuModel("Payment", R.drawable.ic_transfer),
            MenuModel("Investment", R.drawable.ic_transfer),
            MenuModel("Accounts", R.drawable.ic_transfer),
            MenuModel("Withdrawal", R.drawable.ic_transfer),
            MenuModel("Credit", R.drawable.ic_transfer),
            MenuModel("Donate", R.drawable.ic_transfer),
        );
    }

}
