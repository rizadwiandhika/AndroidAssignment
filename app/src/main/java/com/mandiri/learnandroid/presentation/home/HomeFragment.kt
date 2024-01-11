package com.mandiri.learnandroid.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mandiri.learnandroid.adapter.EWalletAdapter
import com.mandiri.learnandroid.adapter.MenuHomeAdapter
import com.mandiri.learnandroid.adapter.SavingDepositAdapter
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.constant.enums.UIStateStatus
import com.mandiri.learnandroid.databinding.FragmentHomeBinding
import com.mandiri.learnandroid.model.EWalletModel
import com.mandiri.learnandroid.model.MenuModel
import com.mandiri.learnandroid.model.SavingDepositModel
import com.mandiri.learnandroid.presentation.viewmodel.HomeViewModel
import com.mandiri.learnandroid.utils.SharedPreferenceHelper
import com.mandiri.learnandroid.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    // `by viewModels()` means that it will be initialized when `this.homeViewModel` is used.
    // It can only be initialized when this fragment is already attached to an Activity.
    // Therefore, using `this.homeViewModel` before `onViewCreated` will result an error!
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

    private lateinit var menuHomeAdapter: MenuHomeAdapter
    private lateinit var eWalletAdapter: EWalletAdapter
    private lateinit var savingDepositAdapter: SavingDepositAdapter

    @Inject
    lateinit var preferences: SharedPreferenceHelper

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        menuHomeAdapter = MenuHomeAdapter()
        eWalletAdapter = EWalletAdapter()
        savingDepositAdapter = SavingDepositAdapter(
            homeViewModel.isShowBalanceLiveData.value ?: false,
            homeViewModel.isShowAllSavingLiveData.value ?: false
        )
        renderEWallet()
        renderSavingDeposit()
        renderMenuHome()
    }

    private fun renderMenuHome() {
        binding.componentMenuHome.rvMenuHome.adapter = menuHomeAdapter

        homeViewModel.fetchMenuHome()
        homeViewModel.menuHomeLiveData.observe(viewLifecycleOwner, ::displayMenu)

        menuHomeAdapter.setOnMenuClickHandler { binding ->
            Toast.makeText(
                context,
                "Clicking ${binding.tvMenuTitle.text}!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun renderSavingDeposit() {
        binding.componentSavingDeposit.rvSavingDeposit.adapter = savingDepositAdapter

        homeViewModel.apply {
            fetchSavingDeposit()

            savingDepositLiveData.observe(viewLifecycleOwner, ::displaySavingAccount)
            isShowAllSavingLiveData.observe(
                viewLifecycleOwner,
                ::handleDisplayAllSavingAccount
            )
            isShowBalanceLiveData.observe(viewLifecycleOwner, ::handleDisplayBalance)

            binding.componentSavingDeposit.tvToggleShowBalance.setOnClickListener {
                toggleShowBalance()
            }
        }
    }

    private fun renderEWallet() {
        eWalletAdapter.setOnButtonClick(homeViewModel::updateEWallet)

        homeViewModel.fetchEWallet()
        homeViewModel.eWalletLiveData.observe(viewLifecycleOwner, ::displayEWallet)
        binding.componentHomeEWallet.rvEWallet.adapter = eWalletAdapter
    }

    private fun displaySavingAccount(uiState: UIState<List<SavingDepositModel>>) {
        when (uiState.status) {
            UIStateStatus.LOADING -> {}
            UIStateStatus.SUCCESS -> {
                savingDepositAdapter.setListSavingDeposit(
                    uiState.data?.toMutableList() ?: mutableListOf()
                )

                binding.componentSavingDeposit.apply {
                    if ((uiState.data?.size ?: 0) <= SavingDepositAdapter.MAX_ITEM) {
                        llShowMore.visibility = View.GONE
                        llShowLess.visibility = View.GONE
                        return@apply
                    }
                    llShowMore.setOnClickListener {
                        handleDisplayAllSavingAccount(true)
                    }
                    llShowLess.setOnClickListener {
                        handleDisplayAllSavingAccount(true)
                    }
                }
            }

            else -> {
                Toast.makeText(
                    requireContext(),
                    "Network fetch failed for saving accounts",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun displayMenu(uiState: UIState<List<MenuModel>>) {
        when (uiState.status) {
            UIStateStatus.LOADING -> {}
            UIStateStatus.SUCCESS -> {
                menuHomeAdapter.setListMenu(uiState.data?.toMutableList() ?: mutableListOf())
            }

            else -> {
                Toast.makeText(
                    requireContext(),
                    "Network fetch failed for menu",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

    }

    private fun displayEWallet(uiState: UIState<List<EWalletModel>>) {
        when (uiState.status) {
            UIStateStatus.LOADING -> {}
            UIStateStatus.SUCCESS -> {
                eWalletAdapter.setDataEWallet(uiState.data?.toMutableList() ?: mutableListOf())
            }

            else -> {
                Toast.makeText(
                    requireContext(),
                    "Network fetch failed for ewallet",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

    }


    private fun handleDisplayAllSavingAccount(isVisible: Boolean) {
        savingDepositAdapter.setIsShowAll(isVisible)

        binding.componentSavingDeposit.apply {
            if (isVisible) {
                llShowMore.visibility = View.GONE
                llShowLess.visibility = View.VISIBLE
            } else {
                llShowLess.visibility = View.GONE
                llShowMore.visibility = View.VISIBLE
            }
        }
    }

    private fun handleDisplayBalance(isVisible: Boolean) {
        savingDepositAdapter.setBalanceVisibility(isVisible)

        val text = if (isVisible) "Hide balance" else "Show balance"
        binding.componentSavingDeposit.tvToggleShowBalance.text = text
    }

}
