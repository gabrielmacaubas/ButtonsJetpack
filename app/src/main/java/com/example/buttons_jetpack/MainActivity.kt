package com.example.buttons_jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeaTheme {
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = { LargeTopAppBarExample(scrollBehavior) },
                    bottomBar = { BottomAppBarExample() }
                ) { innerPadding ->
                    ScrollContent(innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBarExample(scrollBehavior: TopAppBarScrollBehavior) {
    val context = LocalContext.current
    LargeTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF388E3C),
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                "Receita de Chá",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { Toast.makeText(context, "Voltando", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Abrindo menu", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun BottomAppBarExample() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    BottomAppBar(
        containerColor = Color(0xFF388E3C),
        contentColor = Color.White,
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Informações sobre o chá", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Filled.Info, contentDescription = "Informações")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        Toast.makeText(context, "Adicionando nova receita", Toast.LENGTH_SHORT).show()
                    }
                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar")
            }
        }
    )
}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    val ingredientes = listOf(
        "500ml de água",
        "1 colher de sopa de chá verde",
        "1 colher de mel",
        "Suco de meio limão"
    )

    val instrucoes = listOf(
        "Ferva a água e desligue o fogo.",
        "Adicione as folhas de chá verde e deixe em infusão por 3 minutos.",
        "Coe o chá e adicione o mel e o suco de limão.",
        "Mexa bem e sirva quente ou gelado."
    )

    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        item { Text("Ingredientes", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }
        items(ingredientes) { ingrediente ->
            Text("• $ingrediente", modifier = Modifier.padding(start = 16.dp, bottom = 8.dp))
        }
        item { Text("Modo de Preparo", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }
        items(instrucoes) { instrucao ->
            Text("- $instrucao", modifier = Modifier.padding(start = 16.dp, bottom = 8.dp))
        }
    }
}

@Composable
fun TeaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF388E3C),
            secondary = Color(0xFF66BB6A),
            background = Color(0xFFE8F5E9),
            surface = Color.White,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = Color.Black,
            onSurface = Color.Black
        ),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewApp() {
    TeaTheme {
        Scaffold(
            topBar = { LargeTopAppBarExample(scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()) },
            bottomBar = { BottomAppBarExample() }
        ) { innerPadding ->
            ScrollContent(innerPadding)
        }
    }
}
