package com.example.adminoffice.ui.theme.Customer.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.adminoffice.R
import com.example.adminoffice.ui.theme.Customer.Bookings.ViewBookingsCustomer
import com.example.adminoffice.ui.theme.Customer.Coupons.MapsCoupon
import com.example.adminoffice.ui.theme.Customer.LandingPage
import com.example.adminoffice.ui.theme.Customer.Profile.ViewProfile
import com.example.adminoffice.ui.theme.Utils.GlobalStrings
import com.example.adminoffice.ui.theme.Utils.MaskVisualTransformation
import com.example.adminoffice.ui.theme.Utils.Screens.Dashboard
import com.example.adminoffice.ui.theme.Utils.Screens.Profiling.Login
import com.example.adminoffice.ui.theme.Utils.Screens.Users.Home
import com.example.adminoffice.ui.theme.Utils.saveTokenToLocalStorage

object ContactUs  : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    val keyboardController = LocalSoftwareKeyboardController

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        var isErrorFirstName by remember { mutableStateOf(false) }
        var isErrorPhoneNumber by remember { mutableStateOf(false) }
        var isErrorEmail by remember { mutableStateOf(false) }
        var isErrorMessage by remember { mutableStateOf(false) }
        val firstName = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }
        val phone = remember {
            mutableStateOf("")
        }
        val message = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column (modifier = Modifier.padding(10.dp)){
                Text(text = "Contact Us", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Leave your email and we will get back to you within 24 hours", fontSize = 14.sp, fontWeight = FontWeight.Light)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Box(modifier = Modifier
                            .padding(end = 10.dp)
                            .background(
                                GlobalStrings.CustomerColorMain,
                                RoundedCornerShape(10.dp)
                            )) {
                            Icon(
                                painterResource(id = R.drawable.email),
                                contentDescription = "asdasd",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp),
                                tint = Color.White
                            )
                        }
                        Column {
                            Text(text = "Email", fontSize = 10.sp)
                            Text(
                                text = "dabs@gmail.com",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Box(modifier = Modifier
                            .padding(end = 10.dp)
                            .background(
                                GlobalStrings.CustomerColorMain,
                                RoundedCornerShape(10.dp)
                            )) {
                            Icon(
                                painterResource(id = R.drawable.phone),
                                contentDescription = "asdasd",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp),
                                tint = Color.White
                            )
                        }
                        Column {
                            Text(text = "Phone", fontSize = 10.sp)
                            Text(
                                text = "+92 (349) 68 99 560",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Box(modifier = Modifier
                            .padding(end = 10.dp)
                            .background(
                                GlobalStrings.CustomerColorMain,
                                RoundedCornerShape(10.dp)
                            )) {
                            Icon(
                                painterResource(id = R.drawable.location),
                                contentDescription = "asdasd",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp),
                                tint = Color.White
                            )
                        }
                        Column {
                            Text(text = "Address", fontSize = 10.sp)
                            Text(
                                text = "Zaki Centre, Office #17, 2nd Floor, I-8 Markaz Islamabad, 46000",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Box(modifier = Modifier
                            .padding(end = 10.dp)
                            .background(
                                GlobalStrings.CustomerColorMain,
                                RoundedCornerShape(10.dp)
                            )) {
                            Icon(
                                painterResource(id = R.drawable.baseline_web_24),
                                contentDescription = "asdasd",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp),
                                tint = Color.White
                            )
                        }
                        Column {
                            Text(text = "Open Hours", fontSize = 10.sp)
                            Text(
                                text = "10 AM – 6 PM (Monday – Friday)",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
                OutlinedTextField(

                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        placeholderColor = Color.Gray,
                        disabledPlaceholderColor = Color.Gray
                    ),
                    value = firstName.value,
                    onValueChange = {
                        if (it.length <= 10)
                            firstName.value=it
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "add", tint = GlobalStrings.CustomerColorMain)
                    },
                    label = {
                        Text(text = "Name",color = Color.Gray)
                    },
                    placeholder = {
                        Text(text = "Enter Name")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true
                )
                if (isErrorFirstName) {
                    Text(
                        text = "Please provide a valid Name",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 11.sp
                    )
                }
                OutlinedTextField(
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        placeholderColor = Color.Gray,
                        disabledPlaceholderColor = Color.Gray
                    ),
                    value = email.value,
                    onValueChange = {
                        if (it.length <= 30)  email.value=it
//                    isErrorEmail = isValidEmail(email.value)
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Email, contentDescription = "person", tint = GlobalStrings.CustomerColorMain)
                    },
                    label = {
                        Text(text = "Email", color = Color.Gray)
                    },
                    placeholder = {
                        Text(text = "Enter Email")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    singleLine = true
                )
                if (isErrorEmail) {
                    Text(
                        text = "Please provide a valid Email",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 11.sp
                    )
                }
                OutlinedTextField(
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        placeholderColor = Color.Gray,
                        disabledPlaceholderColor = Color.Gray
                    ),
                    value = phone.value,
                    onValueChange = {
                        if (it.length <= 11) phone.value=it
//                    isErrorPhoneNumber = isValidPhoneNumber(PhoneNumber.value)
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, contentDescription = "person", tint = GlobalStrings.CustomerColorMain)
                    },
                    label = {
                        Text(text = "Phone Number", color = Color.Gray)
                    },
                    placeholder = {
                        Text(text = "Enter 11 digit Phone Number")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    singleLine = true,
                    visualTransformation = MaskVisualTransformation("####-#######")
                )
                if (isErrorPhoneNumber) {
                    Text(
                        text = "Please provide a valid Phone Number",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 11.sp
                    )
                }
                OutlinedTextField(
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        placeholderColor = Color.Gray,
                        disabledPlaceholderColor = Color.Gray
                    ),
                    value = message.value,
                    onValueChange = {
                        message.value=it
                    },
                    leadingIcon = {
                        Icon(painterResource(id = R.drawable.chat), contentDescription = "person", tint = GlobalStrings.CustomerColorMain)
                    },
                    label = {
                        Text(text = "Message", color = Color.Gray)
                    },
                    placeholder = {
                        Text(text = "Enter your Message Here")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    singleLine = false,
                )
                if (isErrorMessage) {
                    Text(
                        text = "Please provide a valid Message",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 11.sp
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Box(modifier = Modifier
                    .background(GlobalStrings.CustomerColorMain, RoundedCornerShape(15.dp))
                    .clickable {
                        isErrorEmail = !Home.isValidEmail(email.value)
                        isErrorPhoneNumber = !Home.isValidPhoneNumber(phone.value)
                        isErrorFirstName = !Home.isValidFirstName(firstName.value)
                        isErrorMessage = message.value.isEmpty()
                    }
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .size(400.dp, 30.dp), contentAlignment = Alignment.Center){
                    Text(text = "Send Message", color = Color.White, letterSpacing = 0.sp, fontWeight = FontWeight.Black)
                }

            }
        }

    }
}