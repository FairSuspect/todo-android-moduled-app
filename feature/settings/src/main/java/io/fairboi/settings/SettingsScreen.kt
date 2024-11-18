package io.fairboi.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.fairboi.domain.model.Settings
import io.fairboi.domain.model.ThemeSettings


@Composable
fun SettingsScreen(viewModel: SettingsViewModel, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    SettingScreenContent(
        uiState = uiState,
        onThemeChange = viewModel::onThemeChanged,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingScreenContent(
    uiState: SettingsUiState,
    onThemeChange: (ThemeSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val openDialog = remember { mutableStateOf(false) }

    val theme =
        uiState.appSettingsState.let { state -> (state as? SettingsUiState.AppSettingsState.Loaded)?.settings?.theme }

    val values = ThemeSettings.entries
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),

                ) {
                Column(
                    modifier = Modifier.padding(all = 16.dp),
                ) {

                    Text(text = "Theme")

                    values.forEach {
                        SwitchListTile(
                            onClick = {
                                onThemeChange(it)
                                openDialog.value = false
                            },

                            text = stringResource(it.titleResId),
                            selected = theme == it
                        )
                    }
                }

            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") }, navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(contentPadding)
        )
        {
            ListItem(
                headlineContent = { Text(stringResource(R.string.theme)) },
                supportingContent = {
                    Text(
                        stringResource(
                            theme?.titleResId ?: ThemeSettings.System.titleResId
                        )
                    )
                },
                modifier = modifier.clickable {
                    openDialog.value = true
                }
            )
        }
    }
}

@Composable
fun SwitchListTile(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    selected: Boolean,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            modifier = Modifier.padding(8.dp)
        )
        Text(text)
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    val mockUiState = SettingsUiState(
        appSettingsState = SettingsUiState.AppSettingsState.Loaded(
            Settings(
                theme = ThemeSettings.Dark
            )
        )
    )
    MaterialTheme {
        SettingScreenContent(
            uiState = mockUiState,
            onThemeChange = {},
            onBack = {}
        )
    }
}