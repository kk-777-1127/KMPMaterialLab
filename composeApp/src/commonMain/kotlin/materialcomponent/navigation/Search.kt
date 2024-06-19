package materialcomponent.navigation

/*
TODO versionの対応を待ちたい
 */
//@Composable
//fun SearchBarSample() {
//    var text by rememberSaveable { mutableStateOf("") }
//    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    Box(Modifier.fillMaxSize().semantics { isTraversalGroup = true }) {
//        SearchBar(
//            modifier = Modifier.align(Alignment.TopCenter).semantics { traversalIndex = 0f },
//            inputField = {
//                SearchBarDefaults.InputField(
//                    query = text,
//                    onQueryChange = { text = it },
//                    onSearch = { expanded = false },
//                    expanded = expanded,
//                    onExpandedChange = { expanded = it },
//                    placeholder = { Text("Hinted search text") },
//                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//                    trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
//                )
//            },
//            expanded = expanded,
//            onExpandedChange = { expanded = it },
//        ) {
//            Column(Modifier.verticalScroll(rememberScrollState())) {
//                repeat(4) { idx ->
//                    val resultText = "Suggestion $idx"
//                    ListItem(
//                        headlineContent = { Text(resultText) },
//                        supportingContent = { Text("Additional info") },
//                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
//                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
//                        modifier =
//                        Modifier.clickable {
//                            text = resultText
//                            expanded = false
//                        }
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 4.dp)
//                    )
//                }
//            }
//        }
//
//        LazyColumn(
//            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.semantics { traversalIndex = 1f },
//        ) {
//            val list = List(100) { "Text $it" }
//            items(count = list.size) {
//                Text(
//                    text = list[it],
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun DockedSearchBarSample() {
//    var text by rememberSaveable { mutableStateOf("") }
//    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    Box(Modifier.fillMaxSize().semantics { isTraversalGroup = true }) {
//        DockedSearchBar(
//            modifier =
//            Modifier.align(Alignment.TopCenter).padding(top = 8.dp).semantics {
//                traversalIndex = 0f
//            },
//            inputField = {
//                SearchBarDefaults.InputField(
//                    query = text,
//                    onQueryChange = { text = it },
//                    onSearch = { expanded = false },
//                    expanded = expanded,
//                    onExpandedChange = { expanded = it },
//                    placeholder = { Text("Hinted search text") },
//                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//                    trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
//                )
//            },
//            expanded = expanded,
//            onExpandedChange = { expanded = it },
//        ) {
//            Column(Modifier.verticalScroll(rememberScrollState())) {
//                repeat(4) { idx ->
//                    val resultText = "Suggestion $idx"
//                    ListItem(
//                        headlineContent = { Text(resultText) },
//                        supportingContent = { Text("Additional info") },
//                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
//                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
//                        modifier =
//                        Modifier.clickable {
//                            text = resultText
//                            expanded = false
//                        }
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 4.dp)
//                    )
//                }
//            }
//        }
//
//        LazyColumn(
//            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.semantics { traversalIndex = 1f },
//        ) {
//            val list = List(100) { "Text $it" }
//            items(count = list.size) {
//                Text(
//                    text = list[it],
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
//                )
//            }
//        }
//    }
//}
