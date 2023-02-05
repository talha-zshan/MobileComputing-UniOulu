package com.example.talhamobilecomputing.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.talhamobilecomputing.R
import com.example.talhamobilecomputing.data.entity.Category
import com.example.talhamobilecomputing.ui.home.categoryReminder.CategoryReminder

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
){
    val viewState by viewModel.state.collectAsState()
    val selectedCategory = viewState.selectedCategory

    if(viewState.categories.isNotEmpty() && selectedCategory != null){
        Surface(modifier = Modifier.fillMaxSize()){
            HomeContent(
                selectedCategory = selectedCategory,
                categories = viewState.categories,
                onCategorySelected = viewModel::onCategorySelected,
                navController = navController
            )
        }
    }
}

@Composable
fun HomeContent(
    selectedCategory: Category,
    categories: List<Category>,
    onCategorySelected: (Category) -> Unit,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 24.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                contentColor = Color.Blue,
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
        ) {
            val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)

            HomeAppBar(
                backgroundColor = appBarColor
            )

            CategoryTabs(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected
            )

            CategoryReminder()

        }
    }
}


@Composable
private fun HomeAppBar(
    backgroundColor: Color
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Search , contentDescription = stringResource(R.string.search) )
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.AccountCircle , contentDescription = stringResource(R.string.account) )
            }
        }
    )
}

@Composable
private fun CategoryTabs(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit
){
    val selectedIndex = categories.indexOfFirst {  it == selectedCategory }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 24.dp,
        indicator = emptyTabIndicator,
        modifier = Modifier.fillMaxWidth()
    ) {
        categories.forEachIndexed{ index, category ->
            Tab(
                selected = index == selectedIndex ,
                onClick = { onCategorySelected(category) }) {
                    ChoiceChipFormat(
                        text = category.name,
                        selected = index == selectedIndex,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
                    )
            }
            
        }
    }
}

@Composable
private fun ChoiceChipFormat(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface (
        color = when {
            selected -> MaterialTheme.colors.secondary.copy(alpha = 0.08f)
            else -> MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        },
        contentColor = when {
            selected -> Color.Black
            else -> MaterialTheme.colors.onSurface
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

    }
}

private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}