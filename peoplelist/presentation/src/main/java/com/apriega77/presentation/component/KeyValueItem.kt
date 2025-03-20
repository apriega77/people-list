package com.apriega77.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apriega77.model.KeyValue

@Composable
fun KeyValueItem(modifier: Modifier = Modifier, keyValue: KeyValue) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = keyValue.key, modifier = Modifier
                .fillMaxWidth()
                .weight(0.4F)
        )

        Text(
            text = keyValue.value, modifier = Modifier
                .fillMaxWidth()
                .weight(0.6F)
        )
    }
}

@Composable
@Preview
private fun PreviewKeyValueItem() {
    KeyValueItem(keyValue = KeyValue("First Name", "John Doe"))
}