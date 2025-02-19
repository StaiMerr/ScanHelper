// File: [app/src/main/java/com/example/scanhelper/ui/workspace/WorkspaceScreen.kt]
package com.example.scanhelper.ui.workspace

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkspaceScreen(
    workspaceName: String,
    workspaceFile: String,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Workspace: $workspaceName", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { 
                // TODO: Implement camera opening logic
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Camera")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                // TODO: Implement view photos logic
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Photos")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                // TODO: Implement export logic
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Export")
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Main Menu")
        }
    }
}