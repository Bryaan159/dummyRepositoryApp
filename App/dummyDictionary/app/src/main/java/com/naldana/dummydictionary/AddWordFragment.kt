package com.naldana.dummydictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.databinding.FragmentAddWordBinding
import com.naldana.dummydictionary.viewmodel.WordViewModel
import com.naldana.dummydictionary.viewmodel.ViewModelFactory


class AddWordFragment : Fragment() {
    private val viewModelFactory by lazy {
        val application = requireActivity().application as DummyDictionaryApplication
        ViewModelFactory(application.getDictionartyRepository())
    }
    private val viewModel: WordViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentAddWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false)
        binding.apply {
            viewmodel=viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ButtonList.setOnClickListener{
            findNavController().navigate(R.id.wordListFragment)
        }


    }

}