package com.example.composedemo

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import org.intellij.lang.annotations.PrintFormat

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
    var text by remember { mutableStateOf("") }
    val list = List(50) { "hello${it}" }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(state = ScrollState(0))
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
            onValueChange = { text = it },
            label = { Text("Label") }
        )

        Image(

            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "fdeee",
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        MojiTest()

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

@Preview(showBackground = true)
@Composable
fun ArtistCard() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxLayout() {
    Box {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumnTest() {
    LazyColumn {
        item {
            Text(text = "你好")
        }
        items(5) { index ->
            Text(text = "$index")
        }
        item {
            Text(text = "最后一个")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MojiTest() {
    var name by remember {
        mutableStateOf("je")
    }
    Column() {
        BasicText(text = "你好")
        BasicTextField(value = name, onValueChange = {
            it
            name = it
        })
        FontFamily.Cursive
    }
}

@Preview(showBackground = true)
@Composable
fun MultipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AnnotatedClickableText() {
    val annotatedText = buildAnnotatedString {
        append("Click ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(
            tag = "URL",
            annotation = "https://developer.android.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline
            )
        ) {
            append("here")
        }

        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(
                tag = "URL", start = offset,
                end = offset
            )
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                    Log.d("Clicked URL", annotation.item)
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CanvasTest() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            start = Offset(x = 0f, y = 20f),
            end = Offset(x = canvasWidth, y = 20f),
            color = Color.Blue,
            strokeWidth = 5F
        )
        drawOval(
            color = Color.Green,
            topLeft = Offset(0f,0f),
            size = Size(100f, 100f)
        )
    }
}