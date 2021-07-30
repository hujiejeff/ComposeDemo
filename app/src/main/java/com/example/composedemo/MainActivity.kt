package com.example.composedemo

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    private var mName = "Android";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(mName, click = {
                        mName = "hujie"
                    })
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, click: () -> Unit) {
    Column() {
        Text(text = "Compose")
        Text(text = "Hello $name!", Modifier.clickable { })
        val count = remember { mutableStateOf(0) }
        Text(
            text = count.value.toString(),
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clickable { count.value += 1 },
            textAlign = TextAlign.Center
        )
        ListNumber()
    }
}

@Composable
fun ListNumber() {
    val text by remember { mutableStateOf("") }
    val list = List(50) { "hello${it}" }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(state = ScrollState(200))
            .absolutePadding(top = 10.dp)
    ) {
        list.forEach {
            Text(
                text = it.toString(),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .clickable { },
                textAlign = TextAlign.Center,
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = { },
            label = { Text("Label") }
        )

        Image(

            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "fdeee",
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Greeting("Android", click = {})
    }
}

@Preview(showBackground = true)
@Composable()
fun Title(title: String = "hello") {
    Text(text = title + ", 你好")
}

//@Composable
//fun ArtistCard() {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Image(modifier = Modifier.size(width = 50.dp, height = 50.dp), )
//        Column {
//            Text(text = "hujie")
//            Text(text = "")
//        }
//    }
//}