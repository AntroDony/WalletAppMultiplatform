package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.createWallet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.components.WalletConfigContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateWalletScreen(
    paddingValues: PaddingValues,
    onBack: () -> Unit,
    viewModel: CreateWalletViewModel = koinViewModel(),
){

    WalletConfigContainer(
        uiState = viewModel.walletUiState.collectAsStateWithLifecycle().value,
        modifier = Modifier.padding(paddingValues),
        onActionButtonClicked = { wallet ->
            viewModel.addWallet(wallet)
            onBack()
        },
        onBack = {
            onBack()
        }
    )
}