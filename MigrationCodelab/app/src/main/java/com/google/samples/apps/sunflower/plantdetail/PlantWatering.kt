package com.google.samples.apps.sunflower.plantdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.R

@Composable
fun PlantWatering(wateringInterval: Int) {
    val normalMargin = dimensionResource(id = R.dimen.margin_normal)
    Column(Modifier.fillMaxWidth()) {
        val centerWithPaddingModifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.margin_small))
                .align(Alignment.CenterHorizontally)
        Text(
                text = stringResource(R.string.watering_needs_prefix),
                modifier = centerWithPaddingModifier
                        .padding(top = normalMargin),
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold
        )
        val wateringIntervalText = LocalContext.current.resources.getQuantityString(
                R.plurals.watering_needs_suffix, wateringInterval, wateringInterval
        )
        Text(
                text = wateringIntervalText,
                modifier = centerWithPaddingModifier
                        .padding(bottom = normalMargin)

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlantWateringPreview() {
    MaterialTheme {
        PlantWatering(7)
    }
}
