package com.shiv.ecommerce.persentation.screens.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label:String,
    modifier: Modifier = Modifier,
    singleLine:Boolean = true,
    leadingIcon:ImageVector? = null,
    keyboardOptions: KeyboardOptions  = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier,
        singleLine = singleLine,
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        } else null,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
    )
}