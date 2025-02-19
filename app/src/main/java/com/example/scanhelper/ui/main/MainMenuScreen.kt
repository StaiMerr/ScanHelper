// File: [app/src/main/java/com/example/scanhelper/ui/main/MainMenuScreen.kt]
package com.example.scanhelper.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scanhelper.ui.workspace.WorkspaceScreen

data class Workspace(val name: String, val filePath: String)

@Composable
fun MainMenuScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var workspaceCreated by remember { mutableStateOf(false) }
    var workspaceName by remember { mutableStateOf("") }
    var workspaceFile by remember { mutableStateOf("") }
    
    if (workspaceCreated) {
        WorkspaceScreen(
            workspaceName = workspaceName,
            workspaceFile = workspaceFile,
            onBack = { 
                workspaceCreated = false
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Main Menu", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))
    
            Button(
                onClick = { 
                    // TODO: Navigate to workspace selection screen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Select Workspace")
            }
    
            Spacer(modifier = Modifier.height(16.dp))
    
            Button(
                onClick = { 
                    showDialog = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create New Workspace")
            }
        }
    
        if (showDialog) {
            CreateWorkspaceDialog(
                onDismissRequest = { showDialog = false },
                onWorkspaceCreated = { name, file ->
                    workspaceName = name
                    workspaceFile = file
                    workspaceCreated = true
                    showDialog = false
                }
            )
        }
    }
}