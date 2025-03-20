package com.apriega77.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.apriega77.model.People
import com.apriega77.model.PeopleDetail

@Composable
fun PeopleItem(
    modifier: Modifier = Modifier,
    people: People,
    onItemClicked: (PeopleDetail) -> Unit
) {

    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .clickable { onItemClicked(people.peopleDetail) }
    ) {
        val (imageRef, nameRef, dateRef) = createRefs()

        AsyncImage(
            model = people.image,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .aspectRatio(1f)
                .constrainAs(imageRef) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                }
        )

        Text(
            text = people.name, modifier = Modifier.constrainAs(nameRef) {
                top.linkTo(imageRef.top, 8.dp)
                start.linkTo(imageRef.end, 16.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.fillToConstraints
            },
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = people.date, modifier = Modifier.constrainAs(dateRef) {
                start.linkTo(imageRef.end, 16.dp)
                end.linkTo(parent.end, 16.dp)
                bottom.linkTo(imageRef.bottom, 8.dp)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPeopleItem() {
    PeopleItem(
        people = People(
            id = "1",
            name = "John Doe",
            date = "11 August 2022",
            image = "https://loremflickr.com/640/480/people",
            peopleDetail = PeopleDetail("", emptyList())
        )
    ) {

    }
}
