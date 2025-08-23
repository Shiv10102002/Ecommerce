package com.shiv.ecommerce.persentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shiv.ecommerce.R
import com.shiv.ecommerce.utils.CustomTextField

@Composable
fun LoginScreen(){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontSize = 24.sp,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp).align(Alignment.Start)

        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),

            leadingIcon = Icons.Default.Email,
            value = email,
            onValueChange = {email = it},
            label = "Email"
        )
        CustomTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            leadingIcon = Icons.Default.Lock,
            value = password,
            onValueChange = {password = it},
            label = "Password"
        )

        Text(
            modifier = Modifier.padding(vertical = 8.dp).align(Alignment.End).clickable {
                Toast.makeText(context, "Forgot Password", Toast.LENGTH_SHORT).show()
            },
            text = "Forgot Password?",
            color = colorResource(id = R.color.orange)
        )

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange)),
        ) {
            Text(text = "Login",
                color = Color.White
            )

        }

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        )
        {
            Text(text = "Don't have an account?")
            Text(
                modifier = Modifier.padding(start = 4.dp).clickable {
                     Toast.makeText(context,"Sign Up" ,Toast.LENGTH_SHORT).show()
                },
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.orange)
            )
        }

        Row (
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Divider(
                modifier =  Modifier.weight(1f)
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Or"
            )
            Divider(
                modifier =  Modifier.weight(1f)
            )
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            onClick = {
                Toast.makeText(context, "Sign Up with Google", Toast.LENGTH_SHORT).show()
            },
            shape = RoundedCornerShape(8.dp),

            ){
            Image(
                painter = painterResource(R.drawable.google_icon),
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp).size(24.dp)
            )
            Text(text = "Login with Google",
                color = Color.Black
            )
        }
    }
}