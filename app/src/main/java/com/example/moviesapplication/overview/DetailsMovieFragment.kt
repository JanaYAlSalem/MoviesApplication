package com.example.moviesapplication.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.FragmentDetailsMovieBinding
import com.example.moviesapplication.databinding.FragmentOverviewBinding


class DetailsMovieFragment : Fragment() {

    /*
 * Binding object instance corresponding to the fragment_list.xml layout
 * This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
 * when the view hierarchy is attached to the fragment.
 */
    private var binding: FragmentDetailsMovieBinding? = null

    /*
     * create a reference object with ToDoViewModel type
     * the reference object from activityViewModels
     */
    private val viewModel: OverviewViewModel by activityViewModels()


    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            index = it!!.getInt("id")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            moviesViewModel = viewModel
            detailsMovieFragment = this@DetailsMovieFragment
            viewModel.informationll(index)


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}