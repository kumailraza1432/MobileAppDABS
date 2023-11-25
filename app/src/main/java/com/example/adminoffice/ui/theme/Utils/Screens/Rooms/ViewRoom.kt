package com.example.adminoffice.ui.theme.Utils.Screens.Rooms


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.rememberAsyncImagePainter
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.adminoffice.R
import com.example.adminoffice.ui.theme.Utils.CustomTopAppBar
import com.example.adminoffice.ui.theme.Utils.DataClasses.Rooms.Hotel
import com.example.adminoffice.ui.theme.Utils.DataClasses.Rooms.Inventory
import com.example.adminoffice.ui.theme.Utils.DataClasses.Rooms.Room
import com.example.adminoffice.ui.theme.Utils.DataClasses.Service
import com.example.adminoffice.ui.theme.Utils.DataClasses.ServiceCategory
import com.example.adminoffice.ui.theme.Utils.GlobalStrings
import com.example.adminoffice.ui.theme.Utils.Header
import com.example.adminoffice.ui.theme.Utils.Logo
import com.example.adminoffice.ui.theme.Utils.Menu
import com.example.adminoffice.ui.theme.Utils.Screens.Bookings.AddBooking
import com.example.adminoffice.ui.theme.Utils.Screens.Bookings.AddReview
import com.example.adminoffice.ui.theme.Utils.Screens.Bookings.ViewBookings
import com.example.adminoffice.ui.theme.Utils.Screens.Chat.Chat
import com.example.adminoffice.ui.theme.Utils.Screens.Coupons.AddCoupon
import com.example.adminoffice.ui.theme.Utils.Screens.Coupons.ViewCoupons
import com.example.adminoffice.ui.theme.Utils.Screens.Expense.AddExpense
import com.example.adminoffice.ui.theme.Utils.Screens.Expense.ViewExpense
import com.example.adminoffice.ui.theme.Utils.Screens.Expense.ViewProfit
import com.example.adminoffice.ui.theme.Utils.Screens.Hotels.AddHotel
import com.example.adminoffice.ui.theme.Utils.Screens.Hotels.ViewHotel
import com.example.adminoffice.ui.theme.Utils.Screens.Inventory.AddInventory
import com.example.adminoffice.ui.theme.Utils.Screens.Inventory.AddInventoryCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Inventory.ViewInventory
import com.example.adminoffice.ui.theme.Utils.Screens.Inventory.ViewInventoryCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Menus.AddDish
import com.example.adminoffice.ui.theme.Utils.Screens.Menus.AddDishCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Menus.AddMenu
import com.example.adminoffice.ui.theme.Utils.Screens.Menus.ViewDish
import com.example.adminoffice.ui.theme.Utils.Screens.Menus.ViewDishCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Menus.ViewMenu
import com.example.adminoffice.ui.theme.Utils.Screens.Payments.ViewPayments
import com.example.adminoffice.ui.theme.Utils.Screens.Payments.ViewRefunds
import com.example.adminoffice.ui.theme.Utils.Screens.Revenue.AddRevenue
import com.example.adminoffice.ui.theme.Utils.Screens.Revenue.ViewRevenue
import com.example.adminoffice.ui.theme.Utils.Screens.Reviews.ViewReview
import com.example.adminoffice.ui.theme.Utils.Screens.Services.AddService
import com.example.adminoffice.ui.theme.Utils.Screens.Services.AddServiceCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Services.EditServiceCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Services.ViewService
import com.example.adminoffice.ui.theme.Utils.Screens.Services.ViewServiceCategory
import com.example.adminoffice.ui.theme.Utils.Screens.Settings.AboutUs
import com.example.adminoffice.ui.theme.Utils.Screens.Settings.FAQ
import com.example.adminoffice.ui.theme.Utils.Screens.Settings.Policy
import com.example.adminoffice.ui.theme.Utils.Screens.Users.Home
import com.example.adminoffice.ui.theme.Utils.Screens.Users.ViewUsers
import com.example.adminoffice.ui.theme.Utils.SubHeader
import com.example.adminoffice.ui.theme.Utils.getTokenFromLocalStorage
import com.example.adminoffice.ui.theme.Utils.isInternetAvailable
import kotlinx.coroutines.launch
import org.json.JSONObject

object ViewRoom  : Screen {
    data class TableRow(
        val _id: String,
        val id: Int,
        val serviceImage: String,
        val serviceName: String,)
    var deletemodalopen = mutableStateOf(false)
    val params = JSONObject()
    var usersJsonArrayError = mutableStateOf("")
    var modalopen = mutableStateOf(false)
    lateinit var current: Room
    var rooms = mutableStateListOf<Room>()
    var floorsss = mutableStateListOf<String>()
    var servicess = mutableStateListOf<com.example.adminoffice.ui.theme.Utils.DataClasses.Rooms.Service>()
    var inventoriess = mutableStateListOf<Inventory>()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        var sortOrder by remember { mutableStateOf(SortOrder.Ascending) }
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItem by remember{ mutableStateOf(-1) }
        var selectedSubItem by remember { mutableStateOf(-1) }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Logo(scope = scope, drawerState = drawerState)
                    Menu().forEachIndexed{
                            index, data ->
                        NavigationDrawerItem(
                            modifier = Modifier.height(45.dp),
                            label = { Header(first = data.first, second = data.second) },
                            selected = selectedItem==index,
                            onClick = {
                                selectedItem=index
                                selectedSubItem = -1
                                if(selectedItem==0){
                                    scope.launch {
                                        drawerState.close()
                                        navigator.pop()
                                    }

                                }
                                else if(selectedItem==10){
                                    scope.launch {
                                        drawerState.close()
                                        navigator.replace(Chat)
                                    }

                                }
                            })
                        if (selectedItem == index) {
                            val subMenuItems = data.third
                            Column {
                                subMenuItems.forEachIndexed { index, subItem ->
                                    NavigationDrawerItem(
                                        modifier = Modifier.height(45.dp),
                                        label = {
                                            SubHeader(subItem=subItem)
                                        },
                                        selected = selectedSubItem == index,
                                        onClick = {
                                            //onSubItemClick()
                                            scope.launch {
                                                drawerState.close()
                                                if (selectedItem == 1) {
                                                    if (index == 1) {
                                                        navigator.replace(ViewUsers)
                                                    }
                                                    if (index == 0) {
                                                        navigator.replace(Home)
                                                    }
                                                } else if (selectedItem == 2) {
                                                    if (index == 1) {
                                                        navigator.replace(ViewServiceCategory)
                                                    }
                                                    if (index == 0) {
                                                        navigator.replace(AddServiceCategory)
                                                    }
                                                    if (index == 2) {
                                                        navigator.replace(AddService)
                                                    }
                                                    if (index == 3) {
                                                        navigator.replace(ViewService)
                                                    }
                                                } else if (selectedItem == 3) {
                                                    if (index == 0) {
                                                        navigator.replace(AddHotel)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewHotel)
                                                    }
                                                    if (index == 2) {
                                                        navigator.replace(AddRoom)
                                                    }
                                                    if (index == 3) {
                                                        navigator.replace(ViewRoom)
                                                    }
                                                } else if (selectedItem == 4) {
                                                    if (index == 0) {
                                                        navigator.replace(AddInventoryCategory)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewInventoryCategory)
                                                    }
                                                    if (index == 2) {
                                                        navigator.replace(AddInventory)
                                                    }
                                                    if (index == 3) {
                                                        navigator.replace(ViewInventory)
                                                    }
                                                }
                                                else if (selectedItem == 5) {
                                                    if (index == 0) {
                                                        navigator.replace(AddCoupon)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewCoupons)
                                                    }
                                                }else if (selectedItem == 6) {
                                                    if (index == 0) {
                                                        navigator.replace(AddBooking)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewBookings)
                                                    }
                                                } else if (selectedItem == 7) {
                                                    if (index == 0) {
                                                        navigator.replace(ViewPayments)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewRefunds)
                                                    }
                                                } else if (selectedItem == 8) {
                                                    if (index == 0) {
                                                        navigator.replace(AddDishCategory)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewDishCategory)
                                                    }
                                                    if (index == 2) {
                                                        navigator.replace(AddDish)
                                                    }
                                                    if (index == 3) {
                                                        navigator.replace(ViewDish)
                                                    }
                                                    if (index == 4) {
                                                        navigator.replace(AddMenu)
                                                    }
                                                    if (index == 5) {
                                                        navigator.replace(ViewMenu)
                                                    }
                                                } else if (selectedItem == 9) {
                                                    if (index == 0) {
                                                        navigator.replace(AddReview)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewReview)
                                                    }
                                                } else if (selectedItem == 11) {
                                                    if (index == 0) {
                                                        navigator.replace(AddRevenue)
                                                    }
                                                    if (index == 1) {
                                                        navigator.replace(ViewRevenue)
                                                    }
                                                    if (index == 2) {
                                                        navigator.replace(AddExpense)
                                                    }
                                                    if (index == 3) {
                                                        navigator.replace(ViewExpense)
                                                    }
                                                    if (index == 4) {
                                                        navigator.replace(ViewProfit)
                                                    }
                                                }
                                                else if (selectedItem == 12) {
                                                    if (index == 1) {
                                                        navigator.replace(FAQ)
                                                    }
                                                    if (index == 0) {
                                                        navigator.push(AboutUs)
                                                    }
                                                    if (index == 2) {
                                                        navigator.push(Policy)
                                                    }
                                                }
                                            }


                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            },
            drawerState= drawerState,
        ) {
            Scaffold(
                topBar = {
                    CustomTopAppBar {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                }
            )
            {
                SortableTable(context = context, rows = rooms, sortOrder = sortOrder, onSortOrderChange = { newSortOrder ->
                    sortOrder = newSortOrder
                } )
                if(modalopen.value){

                    CustomProgressDialog(current)
                }
                if (deletemodalopen.value) {

                    DeleteBox(current,context)
                }


            }
        }

        getCategories(context = context)
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SortableTable(
        context: Context,
        rows: List<Room>,
        sortOrder: SortOrder,
        onSortOrderChange: (SortOrder) -> Unit
    ) {
        var currentPage by remember { mutableStateOf(0) }
        val itemsPerPage = 5
        val startIndex = currentPage * itemsPerPage
        val endIndex = minOf((currentPage + 1) * itemsPerPage, rows.size)
        var sortHeader by remember {
            mutableStateOf("")
        }
        var searchFilter by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 60.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()){
                OutlinedTextField(
                    value = searchFilter,
                    onValueChange = { filter ->
                        searchFilter = filter

                    },
                    modifier = Modifier
                        .padding(5.dp),
                    placeholder = {
                        Text(text = "Search")
                    },
                )

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(
                    rememberScrollState()
                )) {

                // Table header
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                ){
                    Box(modifier = Modifier.width(50.dp), contentAlignment = Alignment.Center){
                        Text(text = "ID", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier.width(60.dp), contentAlignment = Alignment.Center){
                        Text(text = "Image", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(vertical = 10.dp))
                    }
                    Box(modifier = Modifier
                        .width(150.dp)
                        .clickable {
                            sortHeader = "name"
                            onSortOrderChange(sortOrder.toggle())
                        }, contentAlignment = Alignment.Center){
                        Text(text = "Hotel", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier
                        .width(150.dp)
                        .clickable {
                            sortHeader = "floor"
                            onSortOrderChange(sortOrder.toggle())
                        }, contentAlignment = Alignment.Center){
                        Text(text = "Floor", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier
                        .width(150.dp), contentAlignment = Alignment.Center){
                        Text(text = "Room No", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier
                        .width(150.dp)
                        .clickable {
                            sortHeader = "type"
                            onSortOrderChange(sortOrder.toggle())
                        }, contentAlignment = Alignment.Center){
                        Text(text = "Type", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier
                        .width(150.dp)
                        .clickable {
                            sortHeader = "description"
                            onSortOrderChange(sortOrder.toggle())
                        }, contentAlignment = Alignment.Center){
                        Text(text = "Description", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                        Text(text = "Adults", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                        Text(text = "Children", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }
                    Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                        Text(text = "Action", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                    }

                }

                if(rows.isEmpty()){
                    Text(text = "NO Records Found.", fontSize = 12.sp, fontWeight = FontWeight.W800, modifier = Modifier.padding(20.dp))
                }
                else{
                    if(sortHeader=="name"){
                        val sortedRows = when (sortOrder) {
                            SortOrder.Ascending -> rows.sortedBy { it.hotelName }
                            SortOrder.Descending -> rows.sortedByDescending { it.hotelName }
                        }
                        sortedRows
                            .subList(startIndex, endIndex)
                            .filter {
                                it.hotelName.contains(searchFilter, ignoreCase = true)
                            }
                            .filter {
                                it.hotelName.contains(searchFilter, ignoreCase = true)
                                it.type.contains(searchFilter, ignoreCase = true) ||
                                        it.description.contains(searchFilter, ignoreCase = true) ||
                                        it.floor.contains(searchFilter, ignoreCase = true)
                            }
                            .forEach { row ->
                                MyRow(row = row, context = context)
                            }
                    }
                    if(sortHeader=="floor"){
                        val sortedRows = when (sortOrder) {
                            SortOrder.Ascending -> rows.sortedBy { it.floor }
                            SortOrder.Descending -> rows.sortedByDescending { it.floor }
                        }
                        sortedRows
                            .subList(startIndex, endIndex)
                            .filter {
                                it.floor.contains(searchFilter, ignoreCase = true)
                            }
                            .filter {
                                it.floor.contains(searchFilter, ignoreCase = true)
                                it.type.contains(searchFilter, ignoreCase = true) ||
                                        it.description.contains(searchFilter, ignoreCase = true) ||
                                        it.hotelName.contains(searchFilter, ignoreCase = true)
                            }
                            .forEach { row ->
                                MyRow(row = row, context = context)
                            }
                    }
                    if(sortHeader=="type"){
                        val sortedRows = when (sortOrder) {
                            SortOrder.Ascending -> rows.sortedBy { it.type }
                            SortOrder.Descending -> rows.sortedByDescending { it.type }
                        }
                        sortedRows
                            .subList(startIndex, endIndex)
                            .filter {
                                it.type.contains(searchFilter, ignoreCase = true)
                            }
                            .filter {
                                it.type.contains(searchFilter, ignoreCase = true)
                                it.hotelName.contains(searchFilter, ignoreCase = true) ||
                                        it.description.contains(searchFilter, ignoreCase = true) ||
                                        it.floor.contains(searchFilter, ignoreCase = true)
                            }
                            .forEach { row ->
                                MyRow(row = row, context = context)
                            }
                    }
                    if(sortHeader=="description"){
                        val sortedRows = when (sortOrder) {
                            SortOrder.Ascending -> rows.sortedBy { it.description }
                            SortOrder.Descending -> rows.sortedByDescending { it.description }
                        }
                        sortedRows
                            .subList(startIndex, endIndex)
                            .filter {
                                it.description.contains(searchFilter, ignoreCase = true)
                            }
                            .filter {
                                it.description.contains(searchFilter, ignoreCase = true)
                                it.type.contains(searchFilter, ignoreCase = true) ||
                                        it.hotelName.contains(searchFilter, ignoreCase = true) ||
                                        it.floor.contains(searchFilter, ignoreCase = true)
                            }
                            .forEach { row ->
                                MyRow(row = row, context = context)
                            }
                    }
                    else{
                        val sortedRows = when (sortOrder) {

                            SortOrder.Ascending -> rows.sortedBy { it.id }
                            SortOrder.Descending -> rows.sortedByDescending { it.id }
                        }
                        sortedRows
                            .subList(startIndex, endIndex)
                            .filter {
                                it.hotelName.contains(searchFilter, ignoreCase = true)
                            }
                            .forEach { row ->
                                MyRow(row = row, context = context)
                            }
                    }

                }
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Box(
                    Modifier
                        .size(30.dp)
                        .border(0.4.dp, Color.Gray, RoundedCornerShape(5.dp))){
                    IconButton(
                        onClick = {
                            if (currentPage>0) {
                                currentPage--
                            }
                        }, modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Icon(painterResource(id = R.drawable.arrowleft), contentDescription = "Arrow Right")
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    Modifier
                        .size(30.dp)
                        .border(0.4.dp, Color.Gray, RoundedCornerShape(5.dp))){
                    IconButton(
                        onClick = {
                            if ((currentPage + 1) * itemsPerPage < rows.size) {
                                currentPage++
                            }
                        }, modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Icon(painterResource(id = R.drawable.arrowright), contentDescription = "Arrow Right")
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }

    enum class SortOrder {
        Ascending,
        Descending;

        fun toggle(): SortOrder {
            return if (this == Ascending) Descending else Ascending
        }
    }
    @Composable
    fun MyRow(row: Room, context: Context){
        val navigatorOut = LocalNavigator.currentOrThrow
        Box(modifier = Modifier
            .padding(vertical = 10.dp)
            .border(0.3.dp, Color.Gray, RoundedCornerShape(5.dp))){
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,

                ){
                Box(modifier = Modifier.width(50.dp), contentAlignment = Alignment.Center){
                    Text(text = row.id.toString())
                }

                Box(modifier = Modifier.width(60.dp), contentAlignment = Alignment.Center){
                    if(row.images[0]==""){
                        Image(
                            painter = rememberAsyncImagePainter("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-squares-01/3/30-512.png"),
                            contentDescription = "image",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    RoundedCornerShape(
                                        (CornerSize(
                                            10.dp
                                        ))
                                    )
                                ),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    else{
                        Log.d("GGGGG",row.images[0])
                        Image(
                            painter = rememberAsyncImagePainter(row.images[0]),
                            contentDescription = "image",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    RoundedCornerShape(
                                        (CornerSize(
                                            10.dp
                                        ))
                                    )
                                ),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }

                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.hotelName)
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.floor)
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.roomNumber)
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.type)
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.description)
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.adults.toString())
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Text(text = row.children.toString())
                }
                Box(modifier = Modifier.width(150.dp), contentAlignment = Alignment.Center){
                    Row{
                        IconButton(onClick = {
                            current = row
                            modalopen.value = true
                        }, modifier = Modifier.width(30.dp)) {
                            Icon(painterResource(id = R.drawable.eye),contentDescription = "View")
                        }
                        IconButton(onClick = {
                            navigatorOut.push(EditRoom(row))
                        }, modifier = Modifier.width(30.dp)) {
                            Icon(painterResource(id = R.drawable.edit),contentDescription = "edit")
                        }
                        IconButton(onClick = {
                            deletemodalopen.value =true
                            current = row


                        }, modifier = Modifier.width(30.dp)) {
                            Icon(painterResource(id = R.drawable.delete),contentDescription = "delete")
                        }
                    }
                }
            }
        }
    }
    // GET Categories Function
    fun getCategories(context: Context) {
        val url = "${GlobalStrings.baseURL}admin/rooms/getRooms"
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading Rooms...")
        progressDialog.show()
        if(!isInternetAvailable(context)){
            Toast
                .makeText(
                    context,
                    "Internet not available.",
                    Toast.LENGTH_SHORT
                )
                .show()
        }
        else{
            val request = object : JsonObjectRequest(
                Request.Method.GET, url, params,
                { response ->
                    Log.d("HASHDASDAS",response.toString())
                    var categories  = response.getJSONArray("rooms")
                    rooms.clear()
                    inventoriess.clear()
                    for(i in 0 until categories.length()){
                        var imagess = mutableStateListOf<String>()
                        var category = categories.getJSONObject(i)

                        var id = category.getInt("id")
                        var _id = category.getString("_id")
                        var __v = category.getInt("__v")
                        var adults = category.getInt("adults")
                        var children = category.getInt("children")
                        var price = category.getInt("price")
                        var size = category.getString("size")
                        var createdAt = category.getString("createdAt")
                        var updatedAt = category.getString("updatedAt")
                        var type = category.getString("type")
                        var roomNumber = category.getString("roomNumber")
                        var deletedAt = category.get("deletedAt")
                        var isDeleted = category.getBoolean("isDeleted")
                        var description = category.getString("description")
                        var floor = category.getString("floor")
                        var hotelName = category.getString("hotelName")
                        var hotel = category.getJSONObject("hotel")
                        var floors = hotel.getJSONArray("floors")
                        var images = category.getJSONArray("images")
                        floorsss.clear()
                        for(i in 0 until  floors.length()){
                            var item = floors.getString(i)
                            floorsss.add(item)
                        }

                        for(i in 0 until  images.length()){
                            var item = images.getString(i)
                            imagess.add(item)
                        }
                        Log.d("Happp", imagess[0].toString())
                        var name = hotel.getString("name")
                        var hotelOBJ =Hotel(_id=_id, floors =floorsss, name =  name)
                        var serviice = category.getJSONArray("services")
                        for(i in 0 until serviice.length()){
                            var serv = serviice.getJSONObject(i)

                            var _id = serv.getString("_id")
                            var serviceImage = serv.getString("image")
                            var serviceName = serv.getString("name")
                            var servicedescription = serv.getString("description")
                            var serviceprice = serv.getInt("price")
                            var servicepriceRate = serv.getString("priceRate")
                            var serviceaddedByRole = serv.getString("addedByRole")
                            var servicevisible = serv.getBoolean("visible")
                            var servicedeletedAt = serv.getString("deletedAt")
                            var servicecreatedAt = serv.getString("createdAt")
                            var serviceupdatedAt = serv.getString("updatedAt")
                            var service__v = serv.getInt("__v")
                            var serviceType = serv.getString("type")
                            var serviceCa = serv.getJSONObject("serviceCategory")
                            var serviceCategoryID = serviceCa.getString("_id")
                            var serviceCategoryTitle = serviceCa.getString("title")
                            var serviceCategoryImage = serviceCa.getString("image")
                            var serviceCategoryDeletedAt = serviceCa.getString("deletedAt")
                            var serviceCategoryIsDeleted = serviceCa.getBoolean("isDeleted")
                            var serviceCategoryCreatedAt = serviceCa.getString("createdAt")
                            var serviceCategoryUpdatedAt = serviceCa.getString("updatedAt")
                            var serviceCategory__V = serviceCa.getInt("__v")
                            var serviceCategoryObject = com.example.adminoffice.ui.theme.Utils.DataClasses.Rooms.ServiceCategory(__v = serviceCategory__V, _id = serviceCategoryID, createdAt = serviceCategoryCreatedAt,
                                deletedAt = serviceCategoryDeletedAt, image = serviceCategoryImage, isDeleted = serviceCategoryIsDeleted, title = serviceCategoryTitle, updatedAt = serviceCategoryUpdatedAt)
                            servicess.add(
                                com.example.adminoffice.ui.theme.Utils.DataClasses.Rooms.Service(__v = service__v,_id=_id, addedByRole = serviceaddedByRole, createdAt = servicecreatedAt,
                                    deletedAt = servicedeletedAt, description = servicedescription, image = serviceImage, isDeleted = serviceCategoryIsDeleted,name=serviceName,
                                    price = serviceprice, priceRate = servicepriceRate, serviceCategory = serviceCategoryObject, type = serviceType, updatedAt = serviceupdatedAt, visible = servicevisible)
                            )
                        }
                        var inventory = category.getJSONArray("inventories")
                        for(i in 0 until inventory.length()){
                            var serv = inventory.getJSONObject(i)

                            var _id = serv.getString("_id")
                            var serviceImage = serv.getString("image")
                            var serviceName = serv.getString("name")
                            var servicedescription = serv.getString("description")
                            var serviceprice = serv.getString("color")
                            var serviceaddedByRole = serv.getString("addedByRole")
                            var servicedeletedAt = serv.getString("deletedAt")
                            var serviceisDeleted = serv.getBoolean("isDeleted")
                            var servicecreatedAt = serv.getString("createdAt")
                            var serviceupdatedAt = serv.getString("updatedAt")
                            var service__v = serv.getInt("__v")
                            var serviceCa = serv.getString("inventoryCategory")

                            inventoriess.add(
                                Inventory(__v = service__v,_id=_id, addedByRole = serviceaddedByRole,
                                    color = serviceprice, createdAt = servicecreatedAt, deletedAt = servicedeletedAt,
                                    description = servicedescription, image = serviceImage, inventoryCategory = serviceCa,
                                    isDeleted = serviceisDeleted,name=serviceName, updatedAt = serviceupdatedAt))
                        }
                        rooms.add(Room(__v = __v, _id = _id, adults = adults, children = children,
                            createdAt=createdAt, deletedAt = deletedAt, description = description, floor =floor,
                            hotel = hotelOBJ, hotelName =hotelName, id = id, images = imagess, inventories = inventoriess,
                            isDeleted = isDeleted, models = floorsss,price=price, roomNumber = roomNumber,
                            services = servicess,size=size, type =type, updatedAt = updatedAt, videos = imagess ))
                    }
                    progressDialog.dismiss()
                },
                { error ->
                    rooms.clear()
                    usersJsonArrayError = mutableStateOf(error.toString())
                    Log.d("HASHDASDAS",error.toString())
                    progressDialog.dismiss()
                }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["Authorization"] = "${getTokenFromLocalStorage(context)}"
                    return headers
                }
            }

            // Add the request to the RequestQueue.
            val requestQueue = Volley.newRequestQueue(context)
            requestQueue.add(request)
        }

    }

    // DELETE Category Function
    fun deleteCategory(context: Context,id:String, callback: (Boolean) -> Unit) {
        val url = "${GlobalStrings.baseURL}admin/rooms/deleteRoom/${id}"
        // Request parameters
        val params = JSONObject()
        if(!isInternetAvailable(context)){
            Toast
                .makeText(
                    context,
                    "Internet not available.",
                    Toast.LENGTH_SHORT
                )
                .show()
        }
        else{
            val progressDialog = ProgressDialog(context)
            progressDialog.setTitle("Deleting...")
            progressDialog.show()
            val request = object : JsonObjectRequest(
                Request.Method.DELETE, url, params,
                { response ->
                    // Handle successful login response
                    Log.d("HEHHEHEHE", response.toString())

                    val isCouponClaimed = response.optBoolean("valid", false)
                    callback(isCouponClaimed)
                    progressDialog.dismiss()
                },
                { error ->
                    // Handle error response
                    Log.d("HEHHEHEHE", error.toString())
                    Toast
                        .makeText(
                            context,
                            "An Error occurred please check your Internet.",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    callback(false)
                    progressDialog.dismiss()
                }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): MutableMap<String, String> {
                    val token = getTokenFromLocalStorage(context = context)
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["Authorization"] = "${token}"
                    return headers
                }
            }


            // Add the request to the RequestQueue.
            val requestQueue = Volley.newRequestQueue(context)
            requestQueue.add(request)
        }
    }

    @Composable
    fun CustomProgressDialog(current: Room) {
        Dialog(
            onDismissRequest = { modalopen.value =false}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = rememberAsyncImagePainter(current.images[0]),

                    contentDescription = "image",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Column {
                                    Text(text = "Room Number", fontSize = 10.sp, color = Color.Gray)
                                    Text(
                                        text = current.roomNumber,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Column {
                                    Text(text = "Room Description", fontSize = 10.sp, color = Color.Gray)
                                    Text(
                                        text = current.description,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Column {
                                    Text(text = "Hotel Name", fontSize = 10.sp, color = Color.Gray)
                                    Text(
                                        text = current.hotelName,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Column {
                                    Text(text = "Floor Number", fontSize = 10.sp, color = Color.Gray)
                                    Text(
                                        text = current.floor,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Column {
                                    Text(text = "Room Type", fontSize = 10.sp, color = Color.Gray)
                                    Text(
                                        text = current.type,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
    @Composable
    fun DeleteBox(current: Room,context:Context) {
        Dialog(
            onDismissRequest = { deletemodalopen.value = false }
        ) {
            Column(modifier = Modifier
                .padding(all = 16.dp)
                .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                .background(Color.White)) {

                Column(modifier = Modifier
                    .padding(all = 16.dp)
                    .background(Color.White)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 6.dp,
                            alignment = Alignment.Start
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "Delete Icon",
                            tint = Color.Black
                        )

                        Text(
                            text = "Delete Room?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 20.dp),
                        text = "Are you sure you want to delete this Room",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 10.dp,
                            alignment = Alignment.End
                        )
                    ) {

                        // Cancel button
                        Box(
                            modifier = Modifier
                                .clickable {
                                    deletemodalopen.value = false
                                }
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black
                            )
                        }

                        // Delete button
                        Box(
                            modifier = Modifier
                                .clickable {
                                    deleteCategory(
                                        context = context,
                                        id = current._id
                                    ) { Succes ->
                                        getCategories(context)
                                        deletemodalopen.value = false
                                    }
                                }
                                .background(
                                    color = Color.Red,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        ) {
                            Text(
                                text = "Delete",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        }

                    }
                }
            }
        }
    }

}