package com.lablabla.blablawatering.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.domain.model.Device

@Composable
fun DeviceItem(
    device: Device,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(8.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${stringResource(id = R.string.device)}: ")
                }
                append(device.name)
            },
            fontSize = 16.sp,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${stringResource(id = R.string.address)}: ")
                }
                append(device.address)
            },
            fontSize = 12.sp,
            color = MaterialTheme.colors.onPrimary
        )
    }
}