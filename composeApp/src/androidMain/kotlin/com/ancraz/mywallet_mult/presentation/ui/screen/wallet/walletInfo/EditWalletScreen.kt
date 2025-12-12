package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletInfo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.components.WalletConfigContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditWalletScreen(
    walletId: Long,
    paddingValues: PaddingValues,
    onBack: () -> Unit,
    viewModel: WalletInfoViewModel = koinViewModel(),
){
    LaunchedEffect(walletId) {
        viewModel.getWalletById(walletId)
    }


    WalletConfigContainer(
        uiState = viewModel.walletUiState.collectAsStateWithLifecycle().value,
        modifier = Modifier.padding(paddingValues),
        onActionButtonClicked = { wallet ->
            viewModel.updateWallet(wallet)
            onBack()
        },
        onBack = {
            onBack()
        }
    )
}