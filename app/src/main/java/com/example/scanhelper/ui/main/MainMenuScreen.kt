// File: [app/src/main/java/com/example/scanhelper/ui/main/MainMenuScreen.kt]
package com.example.scanhelper.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scanhelper.ui.workspace.WorkspaceScreen
import android.net.Uri
import com.example.scanhelper.ui.workspace.CreateWorkspaceScreen
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

data class Workspace(val name: String, val uri: Uri)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen() {
    var isCreatingWorkspace by remember { mutableStateOf(false) }
    var workspaceCreated by remember { mutableStateOf(false) }
    var workspaceName by remember { mutableStateOf("") }
    var workspaceFile by remember { mutableStateOf("") }
    var workspaces by remember { mutableStateOf(listOf<Workspace>()) }
    
    when {
        isCreatingWorkspace -> {
            CreateWorkspaceScreen(
                onBack = { isCreatingWorkspace = false },
                onWorkspaceCreated = { name, folder ->
                    workspaceName = name
                    workspaceFile = folder
                    workspaceCreated = true
                    isCreatingWorkspace = false
                    // Add new workspace to the list
                    workspaces = workspaces + Workspace(name, Uri.parse(folder))
                }
            )
        }
        workspaceCreated -> {
            WorkspaceScreen(
                workspaceName = workspaceName,
                workspaceFile = workspaceFile,
                onBack = { workspaceCreated = false }
            )
        }
        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Scan Helper") },
                        navigationIcon = {
                            IconButton(onClick = { /* TODO: Open drawer menu */ }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { isCreatingWorkspace = true }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Create Workspace")
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    if (workspaces.isEmpty()) {
                        Text(
                            "No workspaces yet",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    } else {
                        LazyColumn {
                            items(workspaces) { workspace ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    onClick = {
                                        workspaceName = workspace.name
                                        workspaceFile = workspace.uri.toString()
                                        workspaceCreated = true
                                    }
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Text(
                                            workspace.name,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}