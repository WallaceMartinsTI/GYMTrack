package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.atoms.BackIconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.ClearIconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.OutlinedTextFieldMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.PrimaryButtonType
import br.com.wcsm.gymtrack.presentation.components.dialogs.ConfirmActionDialog
import br.com.wcsm.gymtrack.presentation.model.TextFieldParams
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnPrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun WorkoutFormTemplate(
    isWorkoutToUpdate: Boolean,
    pageTitle: String,
    pageMessage:  String,
    titleParams: TextFieldParams,
    descriptionParams: TextFieldParams,
    errorMessage: String?,
    buttonText: String,
    onSubmit: UnitCallback,
    onDelete: UnitCallback,
    onBack: UnitCallback
) {
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    var showConfirmDeleteDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                BackIconAtom(
                    onBack = onBack,
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp)
                )

                TextAtom(
                    text = pageTitle.uppercase(),
                    color = PrimaryColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center).padding(vertical = 16.dp)
                )
            }

            TextAtom(
                text = pageMessage,
                color = OnPrimaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(280.dp).padding(bottom = 16.dp)
            )

            with(titleParams) {
                OutlinedTextFieldMolecule(
                    modifier = Modifier.focusRequester(titleFocusRequester),
                    value = value,
                    onValueChange = onValueChange,
                    label = label,
                    placeholder = placeholder,
                    isError = isError,
                    supportingText = supportingText,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        IconAtom(
                            imageVector = Icons.Default.SportsGymnastics,
                            iconDescription = stringResource(R.string.sports_gymnastics_icon_description)
                        )
                    },
                    trailingIcon = {
                        if(value.isNotEmpty()) {
                            ClearIconAtom(
                                onClear = {
                                    onClearField?.let { it() }
                                    titleFocusRequester.requestFocus()
                                }
                            )
                        }
                    }
                )
            }

            with(descriptionParams) {
                OutlinedTextFieldMolecule(
                    modifier = Modifier.focusRequester(descriptionFocusRequester),
                    value = value,
                    onValueChange = onValueChange,
                    label = label,
                    placeholder = placeholder,
                    isError = isError,
                    supportingText = supportingText,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    leadingIcon = {
                        IconAtom(
                            imageVector = Icons.AutoMirrored.Filled.Notes,
                            iconDescription = stringResource(R.string.notes_icon_description)
                        )
                    },
                    trailingIcon = {
                        if(value.isNotEmpty()) {
                            ClearIconAtom(
                                onClear = {
                                    onClearField?.let { it() }
                                    descriptionFocusRequester.requestFocus()
                                }
                            )
                        }
                    }
                )
            }

            // image (por ultimo)

            if(errorMessage.isNullOrBlank().not()) {
                TextAtom(
                    text = errorMessage!!,
                    color = ErrorColor,
                    modifier = Modifier
                        .width(280.dp)
                        .padding(top = 8.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            FilledPrimaryButtonMolecule(
                onClick = onSubmit
            ) {
                IconAtom(
                    imageVector = if(isWorkoutToUpdate) Icons.Default.Edit else Icons.Default.Save,
                    iconDescription = if(isWorkoutToUpdate) stringResource(R.string.edit_icon_description)
                    else stringResource(R.string.save_icon_description),
                    tint = Color.White
                )

                TextAtom(
                    text = buttonText.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            if(isWorkoutToUpdate) {
                Spacer(Modifier.height(8.dp))

                FilledPrimaryButtonMolecule(
                    onClick = { showConfirmDeleteDialog = true },
                    buttonType = PrimaryButtonType.NEGATIVE
                ) {
                    IconAtom(
                        imageVector = Icons.Default.Delete,
                        iconDescription = stringResource(R.string.delete_icon_description),
                        tint = Color.White
                    )

                    TextAtom(
                        text = stringResource(R.string.workout_form_delete_button_text).uppercase(),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        if(showConfirmDeleteDialog) {
            ConfirmActionDialog(
                dialogTitle = stringResource(R.string.workout_form_delete_button_text).uppercase(),
                dialogMessage = stringResource(
                    R.string.workout_form_delete_dialog_message,
                    titleParams.value
                ),
                firstButtonText = stringResource(R.string.delete_text).uppercase(),
                firstButtonType = PrimaryButtonType.WARNING,
                onFirstButtonClick = onDelete,
                secondButtonText = stringResource(R.string.cancel_text).uppercase(),
                secondButtonType = PrimaryButtonType.NEUTRAL,
                onSecondButtonClick = { showConfirmDeleteDialog = false },
                onDismiss = { showConfirmDeleteDialog = false }
            )
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun WorkoutFormTemplatePreview() {
    val isWorkoutToUpdate = false

    WorkoutFormTemplate(
        isWorkoutToUpdate = isWorkoutToUpdate,
        pageTitle = if(isWorkoutToUpdate) stringResource(R.string.workout_form_update_workout_page_title)
        else stringResource(R.string.workout_form_add_workout_page_title),
        pageMessage = if(isWorkoutToUpdate) stringResource(R.string.workout_form_update_workout_page_message)
        else stringResource(R.string.workout_form_add_workout_page_message),
        titleParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Título"
        ),
        descriptionParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Descrição"
        ),
        errorMessage = null,
        buttonText = if(isWorkoutToUpdate) "ATUALIZAR TREINO"
        else "SALVAR TREINO",
        onSubmit = {},
        onDelete = {},
        onBack = {}
    )
}