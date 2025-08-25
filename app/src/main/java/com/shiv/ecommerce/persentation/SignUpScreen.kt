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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shiv.ecommerce.R
import com.shiv.ecommerce.persentation.screens.utils.CustomTextField

@Composable
fun SignUpScreen(){
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

   Column (
       modifier = Modifier
           .fillMaxSize()
           .padding(horizontal = 16.dp)
           .padding(top = 16.dp),
       horizontalAlignment = Alignment.CenterHorizontally
   ){
       Text(
           text = "Sign Up",
            fontSize = 24.sp, style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
           modifier = Modifier
               .padding(vertical = 16.dp)
               .align(Alignment.Start)
       )

       CustomTextField(
           value = firstName,
           onValueChange = {firstName = it},
           label = "First Name",
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp)
       )
       CustomTextField(
           value = lastName,
           onValueChange = {lastName = it},
           label = "Last Name",
           leadingIcon = Icons.Default.Person,
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp)
       )
       CustomTextField(
           value = email,
           onValueChange = {email = it},
           label = "Email",
           leadingIcon = Icons.Default.Email,
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp)
       )
       CustomTextField(
           value = phoneNumber,
           onValueChange = {phoneNumber = it},
           label = "Phone Number",
           leadingIcon = Icons.Default.Phone,
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp)
       )
       CustomTextField(
           value = password,
           onValueChange = {password = it},
           label = "Password",
           leadingIcon = Icons.Default.Lock,
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp),
           visualTransformation = PasswordVisualTransformation()
       )
       CustomTextField(
           value = confirmPassword,
           onValueChange = {confirmPassword = it},
           label = "Confirm Password",
           leadingIcon = Icons.Default.Lock,
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp),
           visualTransformation = PasswordVisualTransformation()
       )

       Button(
           onClick = {
               if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()){
                   Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
               }else{
                   if(password != confirmPassword){
                       Toast.makeText(context, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
                   }
                   Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
               }
           },
           shape = RoundedCornerShape(8.dp),
           colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange)),
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 16.dp)

       ) {
           Text(text = "Sign Up",
               color = Color.White,
               )
       }

       Row (
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center
       ){
           Text(text = "Already have an account?")
           Text(
               modifier = Modifier.padding(start = 4.dp).clickable {
                   Toast
                       .makeText(context, "Login", Toast.LENGTH_SHORT)
                       .show()
               },
               text = "Login",
               fontWeight = FontWeight.Bold,
               color = colorResource(R.color.orange),
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

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview(){
    SignUpScreen()
}