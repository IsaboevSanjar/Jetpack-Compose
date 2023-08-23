package sanjar.uz.jetpack

import android.content.res.Configuration
import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sanjar.uz.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                // A surface container using the 'background' color from the theme
                App(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
private fun App(
    modifier: Modifier = Modifier
) {

    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    Surface(modifier) {
        if (shouldShowOnBoarding) {
            OnBoardScreen(onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }

}

@Composable
fun OnBoardScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to neighbors")
        Button(
            onClick = onContinueClicked,
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            Text(text = "Continue")
        }
    }

}

@Preview
@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(2000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }

    }

}

@Composable
fun Greeting(
    name: String,
) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Show less" else "Show more")
            }
        }

    }
    /*Column(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape),
            contentDescription = "Photo"
        )
        Text(text = "Sanjar $name developer", color = Color.Blue)
        Text(text = "Sanjar is not front-end developer")
    }*/
}

/*@Preview(name = "Light mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun GreetingPreview() {
    Greeting("Android")
}*/
