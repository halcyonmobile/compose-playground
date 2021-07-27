package com.halcyonmobile.viewinterop.example.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.halcyonmobile.viewinterop.R
import com.halcyonmobile.viewinterop.databinding.FragmentPageBinding

const val ARG_INDEX = "index"

class PageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val index = arguments?.getInt(ARG_INDEX)
        val binding = FragmentPageBinding.bind(view)

        binding.composeView.setContent {
            Column(Modifier.padding(8.dp)) {
                Text(text = "Fragment Pager: ViewModel is scoped to the Fragment")
                Text(text = "Fragment $index")
                ComposeWithViewModels()
            }
        }
    }
}