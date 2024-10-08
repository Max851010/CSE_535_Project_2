package com.example.cse_535_project_2_jet.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.viewModel.Setting


@Composable
fun SettingsScreen(navController: NavHostController) {

    val settingViewModel: Setting = viewModel()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text("This is the Settings Screen")


        Button(onClick = { settingViewModel.onButton1Click() }) {
            Text("Easy")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = { settingViewModel.onButton2Click() }) {
            Text("Medium")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = { settingViewModel.onButton3Click() }) {
            Text("Hard")
        }
    }

}
