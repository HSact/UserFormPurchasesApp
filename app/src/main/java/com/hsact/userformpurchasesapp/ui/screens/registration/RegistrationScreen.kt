package com.hsact.userformpurchasesapp.ui.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsact.userformpurchasesapp.R

@Composable
fun RegistrationScreen(
    onFinish: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isFinished) {
        onFinish()
        return
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        InputSection(
            inputType = FieldInputType.Number,
            value = uiState.participantNumber,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.ParticipantNumberChanged(it)) },
            label = stringResource(R.string.participant_number),
            helperText = stringResource(R.string.number_that_you_recived),
            maxLength = FieldInputMaxLength.ParticipantNumber.value
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputSection(
            inputType = FieldInputType.Number,
            value = uiState.code,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.CodeChanged(it)) },
            label = stringResource(R.string.code),
            helperText = stringResource(R.string.code_that_you_recived),
            maxLength = FieldInputMaxLength.Code.value
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputSection(
            inputType = FieldInputType.LatinLetters,
            value = uiState.name,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.NameChanged(it)) },
            label = stringResource(R.string.name),
            helperText = stringResource(R.string.name) + " " + stringResource(R.string.as_in_passort),
            maxLength = FieldInputMaxLength.Name.value
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputSection(
            inputType = FieldInputType.LatinLetters,
            value = uiState.surname,
            onValueChange = { viewModel.handleIntent(RegistrationIntent.SurnameChanged(it)) },
            label = stringResource(R.string.surname),
            helperText = stringResource(R.string.surname) + " " + stringResource(R.string.as_in_passort),
            maxLength = FieldInputMaxLength.Surname.value
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
private fun InputSection(
    modifier: Modifier = Modifier,
    inputType: FieldInputType,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    helperText: String,
    maxLength: Int,
) {

    val filteredValue = filterInput(value, inputType)

    if (filteredValue != value) {
        onValueChange(filteredValue)
        return
    }

    val keyboardOptions = when (inputType) {
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
            value = if (inputType == FieldInputType.LatinLetters) value.uppercase() else value,
            onValueChange = { newValue ->
                val filtered = filterInput(newValue, inputType).take(maxLength)
                onValueChange(filtered)
            },
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
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
        (it.isLetter() && (it.lowercaseChar() in 'a'..'z')) || it == ' ' || it == '-'
    }.uppercase()
}