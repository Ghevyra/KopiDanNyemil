package com.example.kopidannyemil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf(0) }
            // State untuk menyimpan nama user secara global di aplikasi
            var userName by remember { mutableStateOf("Ghevyra") }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFFFFDF9)
            ) {
                when (currentScreen) {
                    0 -> SplashScreen(onStartClick = { currentScreen = 1 })
                    1 -> SignUpScreen(
                        onRegisterClick = { currentScreen = 3 },
                        onLoginClick = { currentScreen = 2 }
                    )
                    2 -> LoginScreen(
                        onLoginSuccess = { currentScreen = 4 },
                        onBack = { currentScreen = 1 }
                    )
                    3 -> RegisterScreen(
                        onRegisterSuccess = { currentScreen = 2 },
                        onBack = { currentScreen = 1 }
                    )
                    4 -> HomeScreen(
                        userName = userName,
                        onNavigate = { route ->
                            if (route == "profile") currentScreen = 5
                            if (route == "order") currentScreen = 6
                        }
                    )
                    5 -> ProfileScreen(
                        currentName = userName,
                        onSaveName = { newName ->
                            userName = newName
                            currentScreen = 4
                        }
                    )
                    6 -> AddressScreen(onOrderSubmit = { currentScreen = 7 })
                    7 -> SuccessScreen(
                        userName = userName,
                        onFinish = { currentScreen = 4 }
                    )
                }
            }
        }
    }
}

// ================= 1. SPLASH SCREEN =================
@Composable
fun SplashScreen(onStartClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFFFFD166), RoundedCornerShape(12.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "☕", fontSize = 48.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Kopi dan Nyemil", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Get a taste of your wish ✨", fontSize = 14.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = onStartClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(54.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Start now", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// ================= 2. SIGN UP SCREEN =================
@Composable
fun SignUpScreen(onRegisterClick: () -> Unit, onLoginClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = "✨ Sign up ✨", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Join as a member & experience more fun from various meal", fontSize = 13.sp, color = Color.DarkGray, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = onRegisterClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Register", fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(14.dp))
            OutlinedButton(
                onClick = onLoginClick,
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFFFD166)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Login", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// ================= 3. LOGIN SCREEN =================
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onBack: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "⭐ Login User", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("username") },
                modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
            )
            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onLoginSuccess,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Login", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// ================= 4. REGISTER SCREEN =================
@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, onBack: () -> Unit) {
    var nama by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "✨ Register", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = nama, onValueChange = { nama = it }, placeholder = { Text("nama lengkap") }, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = username, onValueChange = { username = it }, placeholder = { Text("username") }, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, placeholder = { Text("password") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRegisterSuccess,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Register", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// ================= 5. HOME SCREEN (5 MENU) =================
@Composable
fun HomeScreen(userName: String, onNavigate: (String) -> Unit) {
    val menuList = listOf(
        Triple("Kopi Susu Gula Aren", "Espresso, susu, gula aren", "Rp 22.000"),
        Triple("Donat Cokelat", "Donat empuk, glaze cokelat", "Rp 12.000"),
        Triple("Sourdough Loaf", "Roti fermentasi alami", "Rp 35.000"),
        Triple("Cappuccino", "Espresso, foam susu lembut", "Rp 25.000"),
        Triple("Matcha Latte", "Matcha jepang, susu segar", "Rp 27.000")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        // Sapaan dinamis sesuai nama user
        Text(text = "Halo $userName,", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Makanan & Minuman", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(10.dp))

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            menuList.forEach { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(45.dp).background(Color(0xFFFFC0CB), RoundedCornerShape(6.dp)).border(1.5.dp, Color.Black, RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Foto", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.first, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = item.second, fontSize = 11.sp, color = Color.Gray)
                            Text(text = item.third, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF2A52BE))
                        }
                        Button(
                            onClick = { onNavigate("order") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD166)),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            modifier = Modifier.border(1.5.dp, Color.Black, RoundedCornerShape(6.dp))
                        ) {
                            Text("Pesan", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White, RoundedCornerShape(8.dp)).border(2.dp, Color.Black, RoundedCornerShape(8.dp)).padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1))) { Text("Home", fontSize = 12.sp, color = Color.Black) }
            Button(onClick = { onNavigate("order") }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD166))) { Text("Order", fontSize = 12.sp, color = Color.Black) }
            Button(onClick = { onNavigate("profile") }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC0CB))) { Text("Profile", fontSize = 12.sp, color = Color.Black) }
        }
    }
}

// ================= 6. PROFILE SCREEN =================
@Composable
fun ProfileScreen(currentName: String, onSaveName: (String) -> Unit) {
    var editableName by remember { mutableStateOf(currentName) }

    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Halo $editableName,", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Profile Saya", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Profile Setting", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = editableName,
                onValueChange = { editableName = it },
                modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onSaveName(editableName) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Save", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// ================= 7. ALAMAT PENGIRIMAN =================
@Composable
fun AddressScreen(onOrderSubmit: () -> Unit) {
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var patokan by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Alamat Pengiriman", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = nama, onValueChange = { nama = it }, placeholder = { Text("Nama Lengkap") }, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = alamat, onValueChange = { alamat = it }, placeholder = { Text("Alamat Tujuan") }, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = patokan, onValueChange = { patokan = it }, placeholder = { Text("Patokan atau detail lengkap") }, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(8.dp)), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onOrderSubmit,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD166)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Order dan Kirim", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// ================= 8. KONFIRMASI PESANAN =================
@Composable
fun SuccessScreen(userName: String, onFinish: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Halo $userName,", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier.size(90.dp).background(Color(0xFFD4EDDA), RoundedCornerShape(45.dp)).border(3.dp, Color.Black, RoundedCornerShape(45.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✓", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(0xFF155724))
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Terima kasih sudah memesan, tunggu aja sebentar kurir kami segera mengantarkan pesananmu!",
                fontSize = 13.sp,
                color = Color.DarkGray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = onFinish,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7096D1)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp).border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Text(text = "Oke", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}