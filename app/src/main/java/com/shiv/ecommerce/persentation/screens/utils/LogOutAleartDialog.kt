package com.shiv.ecommerce.persentation.screens.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun LogOutAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    Dialog(onDismissRequest = onDismiss){
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ){
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "Profile Image",
                    modifier = Modifier.size(80.dp)

                    )
            }
        }
    }
}