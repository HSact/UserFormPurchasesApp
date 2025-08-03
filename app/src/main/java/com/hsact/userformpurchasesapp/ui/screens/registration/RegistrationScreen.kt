package com.hsact.userformpurchasesapp.ui.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hsact.userformpurchasesapp.R
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldInputMaxLength
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldInputType
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldState
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    onFinish: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    LaunchedEffect(uiState.isFinished) {
        if (uiState.isFinished) onFinish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegistrationScreenSpacer()
        InputSection(
            fieldType = FieldType.ParticipantNumber,
            fieldState = uiState.participantNumber,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.ParticipantNumberChanged(it)) },
            onFocusChange = {
                viewModel.handleIntent(
                    RegistrationIntent.ParticipantNumberFocusChanged(
                        it
                    )
                )
            },
            label = stringResource(R.string.participant_number),
            helperText = stringResource(R.string.number_that_you_recived),
        )
        RegistrationScreenSpacer()
        InputSection(
            fieldType = FieldType.Code,
            fieldState = uiState.code,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.CodeChanged(it)) },
            onFocusChange = { viewModel.handleIntent(RegistrationIntent.CodeFocusChanged(it)) },
            label = stringResource(R.string.code),
            helperText = stringResource(R.string.code_that_you_recived),
        )
        RegistrationScreenSpacer()
        InputSection(
            fieldType = FieldType.Name,
            fieldState = uiState.name,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.NameChanged(it)) },
            onFocusChange = { viewModel.handleIntent(RegistrationIntent.NameFocusChanged(it)) },
            label = stringResource(R.string.name),
            helperText = stringResource(R.string.name) + " " + stringResource(R.string.as_in_passort),
        )
        RegistrationScreenSpacer()
        InputSection(
            fieldType = FieldType.Surname,
            fieldState = uiState.surname,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.SurnameChanged(it)) },
            onFocusChange = { viewModel.handleIntent(RegistrationIntent.SurnameFocusChanged(it)) },
            label = stringResource(R.string.surname),
            helperText = stringResource(R.string.surname) + " " + stringResource(R.string.as_in_passort),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.by_clicking_continue_button_you_agree),
            style = TextStyle(fontSize = 16.sp, color = Color.Gray),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { viewModel.handleIntent(RegistrationIntent.SubmitClicked) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.isValid
        ) {
            Text(stringResource(R.string.button_continue))
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun RegistrationScreenSpacer() {
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun InputSection(
    modifier: Modifier = Modifier,
    fieldType: FieldType,
    fieldState: FieldState,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    label: String,
    helperText: String,
) {

    val filteredValue = filterInput(fieldState.text, fieldType.inputType)

    if (filteredValue != fieldState.text) {
        onValueChange(filteredValue)
        return
    }

    val isInvalid = fieldState.wasFocused && when (fieldType) {
        FieldType.ParticipantNumber -> fieldState.text.length != FieldInputMaxLength.ParticipantNumber.value
        else -> fieldState.text.isEmpty()
    }

    val keyboardOptions = when (fieldType.inputType) {
        FieldInputType.Number -> KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            capitalization = KeyboardCapitalization.None
        )

        FieldInputType.LatinLetters -> KeyboardOptions(
            keyboardType = KeyboardType.Ascii,
            capitalization = KeyboardCapitalization.Characters
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = if (fieldType.inputType == FieldInputType.LatinLetters) fieldState.text.uppercase() else fieldState.text,
            onValueChange = { newValue ->
                val filtered = filterInput(newValue, fieldType.inputType).take(fieldType.maxLength)
                onValueChange(filtered)
            },
            label = { Text(text = label) },
            isError = isInvalid,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it.isFocused) },
            keyboardOptions = keyboardOptions
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = helperText,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
        )
    }
}

private fun filterInput(value: String, inputType: FieldInputType): String = when (inputType) {
    FieldInputType.Number -> value.filter { it.isDigit() }
    FieldInputType.LatinLetters -> value.filter {
        (it.isLetter() && (it.lowercaseChar() in 'a'..'z')) || it == ' ' || it == '-' || it == '\''
    }.uppercase()
}