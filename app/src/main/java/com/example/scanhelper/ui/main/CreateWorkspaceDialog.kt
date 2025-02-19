// File: [app/src/main/java/com/example/scanhelper/ui/main/CreateWorkspaceDialog.kt]
package com.example.scanhelper.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateWorkspaceDialog(
    onDismissRequest: () -> Unit,
    onWorkspaceCreated: (String, String) -> Unit
) {
    var workspaceName by remember { mutableStateOf("") }
    var workspaceFile by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Create New Workspace") },
        text = {
            Column {
                OutlinedTextField(
                    value = workspaceName,
                    onValueChange = { workspaceName = it },
                    label = { Text("Workspace Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = workspaceFile,
                    onValueChange = { workspaceFile = it },
                    label = { Text("Workspace File") },
                    enabled = false // File chosen via button click
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    // TODO: Integrate a file picker. For now, we simulate file selection.
                    workspaceFile = "/storage/emulated/0/WorkspaceFiles/default.txt"
                }) {
                    Text("Choose File for Workspace")
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (workspaceName.isNotBlank() && workspaceFile.isNotBlank()) {
                        onWorkspaceCreated(workspaceName, workspaceFile)
                    }
                }
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}