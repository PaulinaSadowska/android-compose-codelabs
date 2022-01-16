package com.example.compose.rally

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.compose.rally.data.UserData
import com.example.compose.rally.ui.accounts.AccountsBody
import com.example.compose.rally.ui.accounts.SingleAccountBody
import com.example.compose.rally.ui.bills.BillsBody
import com.example.compose.rally.ui.overview.OverviewBody

@Composable
fun RallyNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier
) {
    NavHost(
            navController = navController,
            startDestination = RallyScreen.Overview.name,
            modifier = modifier) {
        composable(RallyScreen.Overview.name) {
            OverviewBody(
                    onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
                    onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
                    onAccountClick = { accountName ->
                        navController.navigateToSingleAccount(accountName)
                    }
            )
        }
        composable(RallyScreen.Accounts.name) {
            AccountsBody(
                    accounts = UserData.accounts,
                    onAccountClick = { accountName ->
                        navController.navigateToSingleAccount(accountName)
                    })
        }
        composable(RallyScreen.Bills.name) {
            BillsBody(bills = UserData.bills)
        }
        val accountsName = RallyScreen.Accounts.name
        composable(
                route = "$accountsName/{name}",
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                }),
                deepLinks = listOf(navDeepLink { uriPattern = "rally://$accountsName/{name}" })
        ) { entry ->
            val accountName = entry.arguments?.getString("name")
            val account = UserData.getAccount(accountName)
            SingleAccountBody(account = account)
        }
    }
}

private fun NavHostController.navigateToSingleAccount(accountName: String) {
    navigate("${RallyScreen.Accounts.name}/$accountName")
}