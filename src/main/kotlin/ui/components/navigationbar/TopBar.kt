package ui.components.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowState
import ui.components.containers.RowContainer
import ui.components.spacers.Spacer4
import ui.components.spacers.Spacer8

@Composable
fun TopBar(windowState: WindowState) {


    LaunchedEffect(windowState.position) {
        println(windowState.position)
    }

    RowContainer(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
//            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(color = Color(0xFF121212)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        /*
        -----------------------------------
        Title and Logo Container
         */
        RowContainer(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer8()
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Filled.Business,
                contentDescription = "logo",
                tint = Color.White
            )
            Spacer4()
            Text(
                text = "Binance",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                color = Color.White
            )
        }

        /*
        ---------------------------------------------------
        Window Minimize, Close and Maximize Icon
         */
        RowContainer(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Filled.Minimize,
                contentDescription = "logo",
                tint = Color.White
            )
            Icon(
                modifier = Modifier.size(10.dp),
                painter = painterResource("icons/maximize.svg"),
                contentDescription = "logo",
                tint = Color.White
            )

            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Filled.Close,
                contentDescription = "logo",
                tint = Color.White
            )
        }

    }
}