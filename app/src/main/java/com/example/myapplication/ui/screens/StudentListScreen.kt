package com.example.myapplication.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Mahasiswa
import com.example.myapplication.ui.theme.MyApplicationTheme

val dummyMahasiswaList = listOf(
    Mahasiswa("22001", "Ali Rahman", "Teknik Informatika", 3.85),
    Mahasiswa("22002", "Budi Santoso", "Teknik Informatika", 3.40),
    Mahasiswa("22003", "Cici Wulandari", "Sistem Informasi", 3.92),
    Mahasiswa("22004", "Dian Pratama", "Teknik Informatika", 2.95),
    Mahasiswa("22005", "Eka Fitriani", "Sistem Informasi", 3.75),
    Mahasiswa("22006", "Fajar Nugraha", "Teknik Komputer", 3.60),
    Mahasiswa("22007", "Gita Gutawa", "Teknik Informatika", 3.88),
    Mahasiswa("22008", "Hendra Wijaya", "Sistem Informasi", 3.10),
    Mahasiswa("22009", "Indah Permata", "Teknik Komputer", 3.55),
    Mahasiswa("22010", "Joko Widodo", "Teknik Informatika", 3.70)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Daftar Mahasiswa") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Task 4 (Tantangan): LazyRow Daftar Kategori (Chip Horizontal)
            CategoryChipsRow()

            // Task 2: LazyColumn dengan Header & Footer (min 10 item)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Header dalam LazyColumn
                item {
                    Text(
                        text = "Angkatan 2024 / 2025",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Listing Data Mahasiswa
                items(
                    items = dummyMahasiswaList,
                    key = { it.nim } // Key unik
                ) { mahasiswa ->
                    MahasiswaItemCard(mahasiswa = mahasiswa)
                }

                // Footer dalam LazyColumn
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Total Mahasiswa: ${dummyMahasiswaList.size}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}

// --- Task 4: Component LazyRow Chips ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChipsRow() {
    val categories = listOf("Semua", "Teknik Informatika", "Sistem Informasi", "Teknik Komputer")
    var selectedCategory by remember { mutableStateOf("Semua") }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = (category == selectedCategory),
                onClick = { selectedCategory = category },
                label = { Text(category) }
            )
        }
    }
}

@Composable
fun MahasiswaItemCard(mahasiswa: Mahasiswa) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mahasiswa.nama,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${mahasiswa.nim} • ${mahasiswa.prodi}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "IPK ${mahasiswa.ipk}",
                style = MaterialTheme.typography.labelLarge,
                color = if (mahasiswa.ipk >= 3.5) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// --- Task 3: Light & Dark Mode Preview ---
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StudentListScreenPreview() {
    MyApplicationTheme {
        Surface {
            StudentListScreen()
        }
    }
}
