package com.example.quotesapp.presention.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.quotesapp.data.models.Quote

@Composable
fun QuoteList(data: Array<Quote>, onClick: (quote: Quote)-> Unit) {
    LazyColumn(content = {
        items(data){
            QuotesListItem(quote = it, onClick)
        }
    })
}