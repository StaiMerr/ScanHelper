package com.example.scanhelper.ui.workspace

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.documentfile.provider.DocumentFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkspaceScreen(
    onBack: () -> Unit,
    onWorkspaceCreated: (String, String) -> Unit
) {
    var workspaceName by remember { mutableStateOf("") }
    var selectedFolder by remember { mutableStateOf("") }
    val context = LocalContext.current

    val folderPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uri ->
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION or
                        android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
            selectedFolder = DocumentFile.fromTreeUri(context, it)?.uri?.toString() ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create New Workspace") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = workspaceName,
                onValueChange = { workspaceName = it },
                label = { Text("Workspace Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = if (selectedFolder.isEmpty()) "No folder selected" else selectedFolder,
                onValueChange = { },
                label = { Text("Select Folder") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { folderPicker.launch(null) }) {
                        Icon(Icons.Default.Folder, "Select folder")
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (workspaceName.isNotBlank() && selectedFolder.isNotBlank()) {
                        onWorkspaceCreated(workspaceName, selectedFolder)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = workspaceName.isNotBlank() && selectedFolder.isNotBlank()
            ) {
                Text("Create Workspace")
            }
        }
    }
}